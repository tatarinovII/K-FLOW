package my.tatarinov.kflow.presentation.profile

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import my.tatarinov.kflow.presentation.profile.ProfileUiState

class ProfileViewModel : ViewModel(

) {
    private val _state = MutableStateFlow<ProfileUiState>(ProfileUiState.Loading)
    val state: StateFlow<ProfileUiState> = _state
}