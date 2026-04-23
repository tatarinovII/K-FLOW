package my.tatarinov.kflow.database

data class UserDTO(
    val email: String,
    val password: String,
    val firstName: String,
    val lastName: String,
    val sex: String
)
