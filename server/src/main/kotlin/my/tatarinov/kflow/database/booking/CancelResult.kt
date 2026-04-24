package my.tatarinov.kflow.database.booking

sealed class CancelResult {
    data object WorkoutNotFound: CancelResult()
    data object WorkoutAlreadyStarted: CancelResult()
    data object NotBooked: CancelResult()
    data object Success: CancelResult()
    data object UserNotFound: CancelResult()
}