package my.tatarinov.kflow.domain.repository

import my.tatarinov.kflow.domain.models.PastWorkout
import my.tatarinov.kflow.domain.models.User

interface ProfileRepository {

    suspend fun getUserData(): Result<User>
    suspend fun getWorkoutsHistory(): Result<List<PastWorkout>>

}