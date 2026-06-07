package my.tatarinov.kflow.domain.interactors

import my.tatarinov.kflow.domain.models.PastWorkout
import my.tatarinov.kflow.domain.models.User

interface ProfileInteractor {

    suspend fun getHistory(): Result<List<PastWorkout>>
    suspend fun getUserData(): Result<User>

}