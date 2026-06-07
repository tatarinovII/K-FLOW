package my.tatarinov.kflow.domain.interactors

import my.tatarinov.kflow.domain.models.UpcomingWorkout
import my.tatarinov.kflow.domain.models.User

interface HomeInteractor {
    suspend fun getUserData(): Result<User>
    suspend fun getUpcomingWorkouts(): Result<List<UpcomingWorkout>>
    suspend fun bookWorkout(id: String): Result<Unit>
    suspend fun cancelBooking(id: String): Result<Unit>
}