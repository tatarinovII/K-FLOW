package my.tatarinov.kflow.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDateTime
import my.tatarinov.kflow.domain.interactors.HomeInteractor
import my.tatarinov.kflow.domain.interactors.TokenInteractor
import my.tatarinov.kflow.presentation.home.models.WorkoutItemModel
import my.tatarinov.kflow.presentation.utils.DateFormatter

class HomeViewModel(
    private val interactor: HomeInteractor,
    private val dateFormatter: DateFormatter,
    private val tokenInteractor: TokenInteractor
) : ViewModel() {
    private val _state = MutableStateFlow(HomeUiState())
    val state: StateFlow<HomeUiState> = _state.asStateFlow()

    init {
        getUserData()
        getUpcomingWorkouts()
    }

    fun getUserData() {
        viewModelScope.launch {
            val userData = interactor.getUserData()
            userData.fold(onSuccess = { user ->
                _state.update {
                    it.copy(
                        firstName = user.firstName,
                        workoutsRemain = user.workoutsRemain,
                        date = dateFormatter.headerDate()
                    )
                }
            }, onFailure = {
                tokenInteractor.clearToken()
                _state.update { it.copy(backToAuth = true) }
            })
        }
    }

    fun getUpcomingWorkouts() {
        viewModelScope.launch {
            val workouts = interactor.getUpcomingWorkouts()
            workouts.fold(onSuccess = { workoutsList ->
                val list = workoutsList.map { workout ->
                    WorkoutItemModel(
                        id = workout.id,
                        startsAt = DateFormatter.workoutItemDate(LocalDateTime.parse(workout.startsAt)),
                        title = workout.title,
                        bookedCount = workout.bookedCount,
                        capacity = workout.capacity,
                        canBook = workout.canBook,
                        isBooked = workout.isBooked
                    )
                }
                _state.update { it.copy(upcomingWorkouts = list) }
            }, onFailure = {})
        }
    }

    fun onButtonBookClicked(id: String) {
        viewModelScope.launch {
            interactor.bookWorkout(id)
                .onSuccess {
                    getUpcomingWorkouts()
                    getUserData()
                }
                .onFailure { e ->
                    print(e.message)
                }
        }
    }

    fun onButtonCancelClicked(id: String) {
        viewModelScope.launch {
            interactor.cancelBooking(id)
                .onSuccess {
                    getUpcomingWorkouts()
                    getUserData()
                }
                .onFailure { e ->
                    print(e.message)
                }
        }
    }
}