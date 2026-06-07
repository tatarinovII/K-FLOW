package my.tatarinov.kflow.domain.interactors.impl

import my.tatarinov.kflow.domain.interactors.ProfileInteractor
import my.tatarinov.kflow.domain.models.PastWorkout
import my.tatarinov.kflow.domain.models.User
import my.tatarinov.kflow.domain.repository.ProfileRepository

class ProfileInteractorImpl(
    private val repository: ProfileRepository
) : ProfileInteractor {
    override suspend fun getHistory(): Result<List<PastWorkout>> {
        return repository.getWorkoutsHistory()
    }

    override suspend fun getUserData(): Result<User> {
        return repository.getUserData()
    }
}