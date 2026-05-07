package my.tatarinov.kflow.data.dto.auth

import kotlinx.serialization.Serializable

@Serializable
data class AuthResponse(
    val token: String
)
