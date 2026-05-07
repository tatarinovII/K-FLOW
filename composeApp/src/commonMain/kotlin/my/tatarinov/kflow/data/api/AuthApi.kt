package my.tatarinov.kflow.data.api

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import my.tatarinov.kflow.data.dto.auth.AuthResponse
import my.tatarinov.kflow.data.dto.auth.LoginRequest
import my.tatarinov.kflow.data.dto.auth.RegisterRequest

class AuthApi(
    private val client: HttpClient
) {

    suspend fun register(request: RegisterRequest): Result<AuthResponse> = runCatching {
        client.post("register") {
            setBody(request)
        }.body<AuthResponse>()
    }

    suspend fun login(request: LoginRequest): Result<AuthResponse> = runCatching {
        client.post("login") {
            setBody(request)
        }.body<AuthResponse>()
    }

}