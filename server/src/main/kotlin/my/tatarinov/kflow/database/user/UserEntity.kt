package my.tatarinov.kflow.database.user

import java.util.UUID

data class UserEntity(
    val id: UUID,
    val email: String,
    val password: String,
    val firstName: String,
    val lastName: String,
    val sex: String,
    val workoutsRemain: Int = 0
)
