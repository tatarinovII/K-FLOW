package my.tatarinov.kflow.presentation.auth

data class AuthUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val isAuthenticated: Boolean = false,

    val loginEmail: String = "",
    val loginPassword: String = "",

    val regEmail: String = "",
    val regPassword: String = "",
    val regFirstName: String = "",
    val regLastName: String = "",
    val selectedSexIndex: Int = 0
)