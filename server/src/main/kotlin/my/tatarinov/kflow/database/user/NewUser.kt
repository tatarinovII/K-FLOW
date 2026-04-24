package my.tatarinov.kflow.database.user

data class NewUser(
    val email: String,
    val password: String,
    val firstName: String,
    val lastName: String,
    val sex: String
)
