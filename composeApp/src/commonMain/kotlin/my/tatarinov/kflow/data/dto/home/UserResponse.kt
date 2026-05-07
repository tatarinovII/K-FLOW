package my.tatarinov.kflow.data.dto.home

import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    val email: String,
    val firstName: String,
    val lastName: String,
    val sex: String,
    val workoutsRemain: Int
)