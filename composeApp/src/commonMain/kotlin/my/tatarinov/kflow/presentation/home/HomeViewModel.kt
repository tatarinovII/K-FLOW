package my.tatarinov.kflow.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import my.tatarinov.kflow.domain.interactors.HomeInteractor
import my.tatarinov.kflow.presentation.utils.DateFormatter

class HomeViewModel(
    private val interactor: HomeInteractor,
    private val dateFormatter: DateFormatter
) : ViewModel() {
    private val _state = MutableStateFlow(HomeUiState())
    val state: StateFlow<HomeUiState> = _state.asStateFlow()

    init {
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
            }, onFailure = {})
        }
    }
}