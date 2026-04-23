package my.tatarinov.kflow.user

import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    val email: String,
    val firstName: String,
    val lastName: String,
    val sex: String
)
