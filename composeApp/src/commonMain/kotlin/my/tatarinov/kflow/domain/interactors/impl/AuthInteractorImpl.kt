package my.tatarinov.kflow.domain.interactors.impl

import my.tatarinov.kflow.domain.interactors.AuthInteractor
import my.tatarinov.kflow.domain.models.LoginRequestDomain
import my.tatarinov.kflow.domain.models.RegisterRequestDomain
import my.tatarinov.kflow.domain.repository.AuthRepository

class AuthInteractorImpl(
    private val repository: AuthRepository
) : AuthInteractor {

    override suspend fun logIn(email: String, password: String): Result<String> {
        return repository.logIn(LoginRequestDomain(email, password))
    }

    override suspend fun register(
        email: String, password: String, firstName: String, lastName: String, sex: String
    ): Result<String> {
        return repository.register(RegisterRequestDomain(email, password, firstName, lastName, sex))
    }
}