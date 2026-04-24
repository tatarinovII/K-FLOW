package my.tatarinov.kflow.database.booking

import my.tatarinov.kflow.database.user.Users
import my.tatarinov.kflow.database.workout.WorkoutEntity
import my.tatarinov.kflow.database.workout.Workouts
import org.jetbrains.exposed.v1.core.SortOrder
import org.jetbrains.exposed.v1.core.and
import org.jetbrains.exposed.v1.core.count
import org.jetbrains.exposed.v1.core.dao.id.java.UUIDTable
import org.jetbrains.exposed.v1.core.eq
import org.jetbrains.exposed.v1.core.inList
import org.jetbrains.exposed.v1.core.less
import org.jetbrains.exposed.v1.core.minus
import org.jetbrains.exposed.v1.core.plus
import org.jetbrains.exposed.v1.jdbc.deleteWhere
import org.jetbrains.exposed.v1.jdbc.insert
import org.jetbrains.exposed.v1.jdbc.select
import org.jetbrains.exposed.v1.jdbc.selectAll
import org.jetbrains.exposed.v1.jdbc.transactions.suspendTransaction
import org.jetbrains.exposed.v1.jdbc.update
import java.time.LocalDateTime
import java.util.UUID

object Booking : UUIDTable("booking") {
    val workoutId = reference("workout_id", Workouts)
    val userId = reference("user_id", Users)

    init {
        uniqueIndex(workoutId, userId)
    }

    suspend fun create(workoutId: UUID, userId: UUID): BookingResult {
        return suspendTransaction {

            val user = Users.selectAll().where { Users.id eq userId }.forUpdate().singleOrNull()
                ?: return@suspendTransaction BookingResult.UserNotFound

            if (user[Users.workoutsRemain] <= 0) return@suspendTransaction BookingResult.NoWorkoutsRemain

            val workout =
                Workouts.selectAll().where { (Workouts.id eq workoutId) }.forUpdate().singleOrNull()
                    ?: return@suspendTransaction BookingResult.WorkoutNotFound

            val currentBooked = Booking.selectAll()
                .where { (Booking.workoutId eq workoutId) }.count()

            if (currentBooked >= workout[Workouts.capacity]) return@suspendTransaction BookingResult.WorkoutFull

            val existing = Booking.selectAll()
                .where { (Booking.workoutId eq workoutId) and (Booking.userId eq userId) }
                .singleOrNull()

            if (existing != null) return@suspendTransaction BookingResult.AlreadyBooked

            Booking.insert {
                it[Booking.workoutId] = workoutId
                it[Booking.userId] = userId
            }

            Users.update({ Users.id eq userId }) {
                it.update(Users.workoutsRemain, Users.workoutsRemain.minus(1))
            }

            BookingResult.Success
        }
    }

    suspend fun cancel(workoutId: UUID, userId: UUID): CancelResult {
        return suspendTransaction {
            val workout = Workouts.selectAll().where { Workouts.id eq workoutId }.singleOrNull()
                ?: return@suspendTransaction CancelResult.WorkoutNotFound

            Users.selectAll().where(Users.id eq userId).forUpdate().singleOrNull()
                ?: return@suspendTransaction CancelResult.UserNotFound

            if (workout[Workouts.startsAt].isBefore(LocalDateTime.now())) return@suspendTransaction CancelResult.WorkoutAlreadyStarted

            val delete = Booking.deleteWhere {
                (Booking.workoutId eq workoutId) and (Booking.userId eq userId)
            }

            if (delete == 0) return@suspendTransaction CancelResult.NotBooked

            Users.update({ Users.id eq userId }) {
                it.update(Users.workoutsRemain, Users.workoutsRemain.plus(1))
            }

            CancelResult.Success
        }
    }

    suspend fun countBookedByWorkouts(workoutIds: List<UUID>): Map<UUID, Int> {
        if (workoutIds.isEmpty()) return emptyMap()
        return suspendTransaction {
            val countExpr = Booking.id.count()
            Booking.select(workoutId, countExpr)
                .where { workoutId inList workoutIds }.groupBy(workoutId)
                .associate { it[workoutId].value to it[countExpr].toInt() }
        }
    }

    suspend fun findBookedWorkoutsForUser(userId: UUID, workoutIds: List<UUID>): Set<UUID> {
        if (workoutIds.isEmpty()) return emptySet()
        return suspendTransaction {
            Booking.select(workoutId).where {
                (Booking.userId eq userId) and (workoutId inList workoutIds)
            }.mapTo(hashSetOf()) { it[workoutId].value }
        }
    }

    suspend fun findPastWorkoutsForUser(
        userId: UUID,
        now: LocalDateTime,
        limit: Int = 10
    ): List<WorkoutEntity> {
        return suspendTransaction {
            (Booking innerJoin Workouts)
                .selectAll()
                .where { (Booking.userId eq userId) and (Workouts.startsAt less now) }
                .orderBy(Workouts.startsAt, SortOrder.DESC)
                .limit(limit)
                .map {
                    WorkoutEntity(
                        id = it[Workouts.id].value,
                        title = it[Workouts.title],
                        startsAt = it[Workouts.startsAt],
                        capacity = it[Workouts.capacity]
                    )
                }
        }
    }
}