package my.tatarinov.kflow.presentation.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import my.tatarinov.kflow.domain.interactors.AuthInteractor

class AuthViewModel(
    private val interactor: AuthInteractor
) : ViewModel() {

    private val _state = MutableStateFlow(AuthUiState())
    val state: StateFlow<AuthUiState> = _state.asStateFlow()

    fun onLoginEmailChanged(email: String) {
        _state.update { it.copy(loginEmail = email) }
    }

    fun onLoginPasswordChanged(password: String) {
        _state.update { it.copy(loginPassword = password) }
    }

    fun onLoginButtonClicked() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            interactor.logIn(state.value.loginEmail, state.value.loginPassword)
                .onSuccess { _state.update { it.copy(isAuthenticated = true) } }
                .onFailure { println("Login failed! $it") }
            _state.update { it.copy(isLoading = false) }
        }
    }

    fun onRegisterEmailChanged(email: String) {
        _state.update { it.copy(regEmail = email) }
    }

    fun onRegisterPasswordChanged(password: String) {
        _state.update { it.copy(regPassword = password) }
    }

    fun onRegisterFirstNameChanged(firstName: String) {
        _state.update { it.copy(regFirstName = firstName) }
    }

    fun onRegisterLastNameChanged(lastName: String) {
        _state.update { it.copy(regLastName = lastName) }
    }

    fun onSexSelected(index: Int) {
        _state.update { it.copy(selectedSexIndex = index) }
    }

    fun onRegisterButtonClicked() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            interactor.register(
                state.value.regEmail,
                state.value.regPassword,
                state.value.regFirstName,
                state.value.regLastName,
                sex = if (state.value.selectedSexIndex == 1) "male" else "female"
            ).onSuccess { _state.update { it.copy(isAuthenticated = true) } }
                .onFailure { println("Register failed! $it") }
        }
    }
}