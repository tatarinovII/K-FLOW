package my.tatarinov.kflow.domain.interactors.impl

import my.tatarinov.kflow.domain.interactors.HomeInteractor
import my.tatarinov.kflow.domain.models.UpcomingWorkout
import my.tatarinov.kflow.domain.models.User
import my.tatarinov.kflow.domain.repository.HomeRepository

class HomeInteractorImpl(
    private val repository: HomeRepository
): HomeInteractor {
    override suspend fun getUserData(): Result<User> {
        return repository.getUserData()
    }

    override suspend fun getUpcomingWorkouts(): Result<List<UpcomingWorkout>> {
        return repository.getUpcomingWorkouts()
    }

    override suspend fun bookWorkout(id: String): Result<Unit> {
        return repository.bookWorkout(id)
    }

    override suspend fun cancelBooking(id: String): Result<Unit> {
        return repository.deleteBooking(id)
    }
}