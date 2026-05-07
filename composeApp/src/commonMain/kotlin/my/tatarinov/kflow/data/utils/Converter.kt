package my.tatarinov.kflow.data.utils

import my.tatarinov.kflow.data.dto.auth.LoginRequest
import my.tatarinov.kflow.data.dto.auth.RegisterRequest
import my.tatarinov.kflow.data.dto.home.UserResponse
import my.tatarinov.kflow.domain.models.LoginRequestDomain
import my.tatarinov.kflow.domain.models.RegisterRequestDomain
import my.tatarinov.kflow.domain.models.User

class Converter {

    fun map(data: LoginRequest): LoginRequestDomain = LoginRequestDomain(
        email = data.email,
        password = data.password
    )
    fun map(data: LoginRequestDomain): LoginRequest = LoginRequest(
        email = data.email,
        password = data.password
    )

    fun map(data: RegisterRequest): RegisterRequestDomain = RegisterRequestDomain(
        email = data.email,
        password = data.password,
        firstName = data.firstName,
        lastName = data.lastName,
        sex = data.sex
    )
    fun map(data: RegisterRequestDomain): RegisterRequest = RegisterRequest(
        email = data.email,
        password = data.password,
        firstName = data.firstName,
        lastName = data.lastName,
        sex = data.sex
    )

    fun map(data: UserResponse): User = User(
        email = data.email,
        firstName = data.firstName,
        lastName = data.lastName,
        sex = data.sex,
        workoutsRemain = data.workoutsRemain
    )
}