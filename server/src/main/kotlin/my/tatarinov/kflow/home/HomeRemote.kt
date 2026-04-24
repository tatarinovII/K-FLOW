package my.tatarinov.kflow.home

import kotlinx.serialization.Serializable


@Serializable
data class HomeReceiveRemote(
    val token: String? = null
)

@Serializable
data class HomeResponse(
    val currentDate: String,
    val workoutsRemain: Int,
    val firstName: String,
)