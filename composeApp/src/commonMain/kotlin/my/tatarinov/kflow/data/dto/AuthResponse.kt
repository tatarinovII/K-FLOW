package my.tatarinov.kflow.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class AuthResponse(
    val token: String
)
