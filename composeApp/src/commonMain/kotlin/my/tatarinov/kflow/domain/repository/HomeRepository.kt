package my.tatarinov.kflow.domain.repository

import my.tatarinov.kflow.domain.models.User

interface HomeRepository {
    suspend fun getUserData(): Result<User>
}