package my.tatarinov.kflow.domain.models

data class User(
    val email: String,
    val firstName: String,
    val lastName: String,
    val sex: String,
    val workoutsRemain: Int
)
