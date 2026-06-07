package my.tatarinov.kflow.domain.models

data class UpcomingWorkout(
    val id: String,
    val title: String,
    val startsAt: String,
    val capacity: Int,
    val bookedCount: Int,
    val isBooked: Boolean,
    val canBook: Boolean
)