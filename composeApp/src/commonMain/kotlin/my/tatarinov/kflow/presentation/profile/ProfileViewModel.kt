package my.tatarinov.kflow.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDateTime
import my.tatarinov.kflow.domain.interactors.ProfileInteractor
import my.tatarinov.kflow.domain.models.PastWorkout
import my.tatarinov.kflow.domain.models.User
import my.tatarinov.kflow.presentation.profile.models.HistoryItem
import my.tatarinov.kflow.presentation.utils.DateFormatter

class ProfileViewModel(
    private val interactor: ProfileInteractor, private val dateTimeFormatter: DateFormatter
) : ViewModel() {
    private val _state = MutableStateFlow<ProfileUiState>(ProfileUiState.Loading)
    val state: StateFlow<ProfileUiState> = _state

    init {
        loadData()
    }

    fun loadData() {
        viewModelScope.launch {
            var userData: User? = null
            var list: List<PastWorkout>? = null

            interactor.getUserData().fold(onSuccess = {
                userData = it
            }, onFailure = {})
            interactor.getHistory().fold(onSuccess = {
                list = it
            }, onFailure = { })
            if (userData != null && list != null) {
                _state.value = ProfileUiState.Default(
                    firstName = userData.firstName,
                    lastName = userData.lastName,
                    email = userData.email,
                    historyList = list.map {
                        HistoryItem(
                            title = it.title,
                            date = dateTimeFormatter.workoutItemDate(LocalDateTime.parse(it.data))
                        )
                    }, workoutRemain = userData.workoutsRemain
                )
            }
        }
    }
}