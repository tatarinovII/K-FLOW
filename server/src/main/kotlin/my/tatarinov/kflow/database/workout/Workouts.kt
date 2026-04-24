package my.tatarinov.kflow.database.workout

import org.jetbrains.exposed.v1.core.and
import org.jetbrains.exposed.v1.core.dao.id.java.UUIDTable
import org.jetbrains.exposed.v1.core.eq
import org.jetbrains.exposed.v1.core.greater
import org.jetbrains.exposed.v1.core.less
import org.jetbrains.exposed.v1.javatime.datetime
import org.jetbrains.exposed.v1.jdbc.selectAll
import org.jetbrains.exposed.v1.jdbc.transactions.suspendTransaction
import org.jetbrains.exposed.v1.jdbc.transactions.transaction
import java.time.LocalDateTime

object Workouts : UUIDTable("workout") {
    val title = varchar("title", 25)
    val startsAt = datetime("starts_at")
    val capacity = integer("capacity")

    suspend fun fetchUpcoming(now: LocalDateTime, limit: Int = 10): List<WorkoutEntity> {
        return suspendTransaction {
            Workouts.selectAll().where { (startsAt greater now)}
                .orderBy(startsAt).limit(limit).map {
                    WorkoutEntity(
                        id = it[Workouts.id].value,
                        startsAt = it[startsAt],
                        capacity = it[capacity],
                        title = it[title]
                    )
                }

        }
    }
}