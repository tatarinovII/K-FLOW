package my.tatarinov.kflow.presentation.profile

import my.tatarinov.kflow.presentation.profile.models.HistoryItem

sealed interface ProfileUiState {
    data class Default(
        val firstName: String,
        val lastName: String,
        val email: String,
        val historyList: List<HistoryItem>,
        val workoutRemain: Int
    ) : ProfileUiState

    data object Loading : ProfileUiState
}