package my.tatarinov.kflow.di

import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import my.tatarinov.kflow.data.AuthApi
import my.tatarinov.kflow.data.AuthRepositoryImpl
import my.tatarinov.kflow.data.utils.Converter
import my.tatarinov.kflow.domain.interactors.AuthInteractor
import my.tatarinov.kflow.domain.interactors.impl.AuthInteractorImpl
import my.tatarinov.kflow.domain.repository.AuthRepository
import my.tatarinov.kflow.presentation.auth.AuthViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

expect val baseUrl: String
val dataModule = module {
    single<HttpClient> {
        HttpClient {
            install(ContentNegotiation) {
                json(Json{
                    ignoreUnknownKeys = true
                    isLenient = true
                    encodeDefaults = true
                })
            }
            install(Logging) {
                level = LogLevel.ALL
                logger = Logger.DEFAULT
            }

            defaultRequest {
                url(baseUrl)
                contentType(ContentType.Application.Json)
            }

            install(HttpTimeout) {
                requestTimeoutMillis = 15_000
                connectTimeoutMillis = 10_000
            }
        }
    }

    single {
        Converter()
    }

    single<AuthApi> {
        AuthApi(get())
    }

    single<AuthRepository> {
        AuthRepositoryImpl(get(), get())
    }
}

val domainModule = module {
    single<AuthInteractor> {
        AuthInteractorImpl(get())
    }
}

val viewModelModule = module {
    viewModelOf(::AuthViewModel)
}

val appModule = listOf(dataModule, domainModule, viewModelModule)