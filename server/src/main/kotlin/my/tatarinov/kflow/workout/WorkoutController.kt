package my.tatarinov.kflow.workout

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import my.tatarinov.kflow.database.booking.Booking
import my.tatarinov.kflow.database.booking.BookingResult
import my.tatarinov.kflow.database.booking.CancelResult
import my.tatarinov.kflow.database.workout.Workouts
import java.time.LocalDateTime
import java.util.UUID

class WorkoutController(
    private val call: ApplicationCall
) {


    suspend fun getUpcoming(userId: UUID) {
        val now = LocalDateTime.now()
        val limit = call.request.queryParameters["limit"]?.toIntOrNull() ?: 10

        val workouts = Workouts.fetchUpcoming(now, limit)
        val workoutIds = workouts.map { it.id }

        val bookedCounts = Booking.countBookedByWorkouts(workoutIds)
        val bookedByUser = Booking.findBookedWorkoutsForUser(userId, workoutIds)

        val response = workouts.map { workout ->
            val bookedCount = bookedCounts[workout.id] ?: 0
            val isBooked = workout.id in bookedByUser
            val isFull = bookedCount >= workout.capacity

            WorkoutCardResponse(
                id = workout.id.toString(),
                startsAt = workout.startsAt.toString(),
                capacity = workout.capacity,
                bookedCount = bookedCount,
                isBooked = isBooked,
                canBook = !isFull && !isBooked,
                title = workout.title
            )
        }
        call.respond(UpcomingWorkoutsResponse(response))
    }

    suspend fun getHistory(userId: UUID) {
        val now = LocalDateTime.now()
        val limit = call.request.queryParameters["limit"]?.toIntOrNull() ?: 10

        val past = Booking.findPastWorkoutsForUser(userId, now, limit)

        val response = past.map {
            PastWorkoutCardResponse(
                id = it.id.toString(),
                title = it.title,
                startsAt = it.startsAt.toString()
            )
        }

        call.respond(PastWorkoutsResponse(response))
    }

    suspend fun book(userId: UUID) {
        val workoutId = call.parameters["id"]?.let {
            runCatching { UUID.fromString(it) }.getOrNull()
        } ?: run {
            call.respond(HttpStatusCode.BadRequest, "Invalid workout id")
            return
        }

        when (Booking.create(workoutId, userId)) {
            BookingResult.AlreadyBooked -> call.respond(HttpStatusCode.Conflict, "Already booked")
            BookingResult.NoWorkoutsRemain -> call.respond(
                HttpStatusCode.PaymentRequired, "No remain workouts"
            )

            BookingResult.Success -> call.respond(HttpStatusCode.Created)
            BookingResult.UserNotFound -> call.respond(HttpStatusCode.NotFound, "User not found")
            BookingResult.WorkoutFull -> call.respond(HttpStatusCode.Conflict, "Workout is full")
            BookingResult.WorkoutNotFound -> call.respond(
                HttpStatusCode.NotFound, "Workout not found"
            )
        }
    }

    suspend fun cancel(userId: UUID) {
        val workoutId = call.parameters["id"]?.let {
            runCatching { UUID.fromString(it) }.getOrNull()
        } ?: run {
            call.respond(HttpStatusCode.BadRequest, "Invalid workout id")
            return
        }

        when (Booking.cancel(workoutId = workoutId, userId = userId)) {
            CancelResult.NotBooked -> call.respond(
                HttpStatusCode.NotFound, "Cant cancel booking that does not exists"
            )

            CancelResult.Success -> call.respond(HttpStatusCode.NoContent)
            CancelResult.WorkoutAlreadyStarted -> call.respond(
                HttpStatusCode.Conflict, "Workout already started"
            )

            CancelResult.WorkoutNotFound -> call.respond(
                HttpStatusCode.NotFound, "Workout not found"
            )

            CancelResult.UserNotFound -> call.respond(HttpStatusCode.NotFound, "User not found")
        }
    }


}