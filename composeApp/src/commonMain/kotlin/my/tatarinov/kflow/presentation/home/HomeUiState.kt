package my.tatarinov.kflow.presentation.home

import my.tatarinov.kflow.presentation.home.models.WorkoutItemModel

data class HomeUiState(
    val firstName: String = "{username}",
    val workoutsRemain: Int = 0,
    val date: String = "",
    val upcomingWorkouts: List<WorkoutItemModel> = emptyList(),
    val backToAuth: Boolean = false
)
