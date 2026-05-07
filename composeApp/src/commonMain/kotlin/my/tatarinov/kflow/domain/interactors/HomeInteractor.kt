package my.tatarinov.kflow.domain.interactors

import my.tatarinov.kflow.domain.models.User

interface HomeInteractor {
    suspend fun getUserData(): Result<User>
}