package my.tatarinov.kflow.domain.repository

import my.tatarinov.kflow.domain.models.UpcomingWorkout
import my.tatarinov.kflow.domain.models.User

interface HomeRepository {
    suspend fun getUserData(): Result<User>
    suspend fun getUpcomingWorkouts(): Result<List<UpcomingWorkout>>
    suspend fun bookWorkout(id: String): Result<Unit>
    suspend fun deleteBooking(id: String): Result<Unit>
}