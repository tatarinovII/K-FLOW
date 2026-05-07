package my.tatarinov.kflow.data.repos

import my.tatarinov.kflow.data.api.AuthApi
import my.tatarinov.kflow.data.storage.TokenStorage
import my.tatarinov.kflow.data.utils.Converter
import my.tatarinov.kflow.domain.models.LoginRequestDomain
import my.tatarinov.kflow.domain.models.RegisterRequestDomain
import my.tatarinov.kflow.domain.repository.AuthRepository

class AuthRepositoryImpl(
    private val api: AuthApi,
    private val converter: Converter,
    private val tokenStorage: TokenStorage
): AuthRepository {

    override suspend fun logIn(data: LoginRequestDomain): Result<String> {
        val response = api.login(converter.map(data))
        return response.fold(
            onSuccess = { authResponse ->
                if (authResponse.token.isNotEmpty()) {
                    tokenStorage.saveToken(authResponse.token)
                    Result.success(authResponse.token)
                } else {
                    Result.failure(Exception("Empty token"))
                }
            },
            onFailure = { Result.failure(it) }
        )
    }

    override suspend fun register(data: RegisterRequestDomain): Result<String> {
        val response = api.register(converter.map(data))
        return response.fold(
            onSuccess = { authResponse ->
                if (authResponse.token.isNotEmpty()) {
                    tokenStorage.saveToken(authResponse.token)
                    Result.success(authResponse.token)
                } else {
                    Result.failure(Exception("Empty token"))
                }
            },
            onFailure = { Result.failure(it) }
        )
    }
}