package my.tatarinov.kflow.data.dto.home

import kotlinx.serialization.Serializable

@Serializable
data class WorkoutCardResponse(
    val id: String,
    val title: String,
    val startsAt: String,
    val capacity: Int,
    val bookedCount: Int,
    val isBooked: Boolean,
    val canBook: Boolean
)

@Serializable
data class UpcomingWorkoutsResponse(
    val workouts: List<WorkoutCardResponse>
)
@Serializable
data class PastWorkoutCardResponse(
    val id: String,
    val title: String,
    val startsAt: String
)
@Serializable
data class PastWorkoutsResponse(
    val workouts: List<PastWorkoutCardResponse>
)