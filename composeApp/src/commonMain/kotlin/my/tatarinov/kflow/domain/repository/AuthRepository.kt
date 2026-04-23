package my.tatarinov.kflow.domain.repository

import my.tatarinov.kflow.domain.models.LoginRequestDomain
import my.tatarinov.kflow.domain.models.RegisterRequestDomain

interface AuthRepository {
    suspend fun logIn(data: LoginRequestDomain): Result<String>
    suspend fun register(data: RegisterRequestDomain): Result<String>
}