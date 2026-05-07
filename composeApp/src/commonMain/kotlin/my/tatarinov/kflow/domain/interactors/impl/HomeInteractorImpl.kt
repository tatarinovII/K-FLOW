package my.tatarinov.kflow.domain.interactors.impl

import my.tatarinov.kflow.domain.interactors.HomeInteractor
import my.tatarinov.kflow.domain.models.User
import my.tatarinov.kflow.domain.repository.HomeRepository

class HomeInteractorImpl(
    private val repository: HomeRepository
): HomeInteractor {
    override suspend fun getUserData(): Result<User> {
        return repository.getUserData()
    }
}