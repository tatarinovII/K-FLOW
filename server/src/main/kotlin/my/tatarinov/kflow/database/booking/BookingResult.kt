package my.tatarinov.kflow.database.booking

sealed class BookingResult {
    data object Success: BookingResult()
    data object UserNotFound: BookingResult()
    data object NoWorkoutsRemain: BookingResult()
    data object WorkoutNotFound: BookingResult()
    data object WorkoutFull: BookingResult()
    data object AlreadyBooked: BookingResult()
}