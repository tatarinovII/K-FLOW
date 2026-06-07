package my.tatarinov.kflow.presentation.home.models

data class WorkoutItemModel(
    val id: String,
    val startsAt: String,
    val title: String,
    val bookedCount: Int,
    val capacity: Int,
    val canBook: Boolean,
    val isBooked: Boolean
)
