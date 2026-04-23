package my.tatarinov.kflow.register

import kotlinx.serialization.Serializable

@Serializable
data class RegisterReceiveRemote(
    val email: String,
    val password: String?,
    val firstName: String,
    val lastName: String,
    val sex: String
)

@Serializable
data class RegisterResponseRemote(
    val token: String
)