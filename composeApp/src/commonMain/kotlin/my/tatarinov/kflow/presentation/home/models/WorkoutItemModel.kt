package my.tatarinov.kflow.presentation.home.models

data class WorkoutItemModel(
    val id: String,
    val dayOfWeek: String,
    val date: String,
    val startTime: String,
    val title: String,
    val bookedCount: Int,
    val capacity: Int,
    val canBook: Boolean,
    val isBooked: Boolean
)
