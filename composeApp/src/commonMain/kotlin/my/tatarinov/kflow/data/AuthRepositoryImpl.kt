package my.tatarinov.kflow.data

import my.tatarinov.kflow.data.utils.Converter
import my.tatarinov.kflow.domain.models.LoginRequestDomain
import my.tatarinov.kflow.domain.models.RegisterRequestDomain
import my.tatarinov.kflow.domain.repository.AuthRepository

class AuthRepositoryImpl(
    private val api: AuthApi,
    private val converter: Converter
): AuthRepository {

    override suspend fun logIn(data: LoginRequestDomain): Result<String> {
        val response = api.login(converter.map(data))
        val token = response.getOrDefault("").toString()
        if (token.isNotEmpty() && response.isSuccess) return Result.success(token)
        else return Result.failure(response.exceptionOrNull() ?: Exception("Maybe empty token output"))
    }

    override suspend fun register(data: RegisterRequestDomain): Result<String> {
        val response = api.register(converter.map(data))
        val token = response.getOrDefault("").toString()
        if (token.isNotEmpty() && response.isSuccess) return Result.success(token)
        else return Result.failure(response.exceptionOrNull() ?: Exception("Maybe empty token output"))
    }
}