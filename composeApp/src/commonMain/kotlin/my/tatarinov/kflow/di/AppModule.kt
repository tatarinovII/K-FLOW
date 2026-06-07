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
import my.tatarinov.kflow.data.api.AuthApi
import my.tatarinov.kflow.data.api.HomeApi
import my.tatarinov.kflow.data.repos.AuthRepositoryImpl
import my.tatarinov.kflow.data.repos.HomeRepositoryImpl
import my.tatarinov.kflow.data.storage.TokenStorage
import my.tatarinov.kflow.data.utils.Converter
import my.tatarinov.kflow.domain.interactors.AuthInteractor
import my.tatarinov.kflow.domain.interactors.HomeInteractor
import my.tatarinov.kflow.domain.interactors.TokenInteractor
import my.tatarinov.kflow.domain.interactors.impl.AuthInteractorImpl
import my.tatarinov.kflow.domain.interactors.impl.HomeInteractorImpl
import my.tatarinov.kflow.domain.interactors.impl.TokenInteractorImpl
import my.tatarinov.kflow.domain.repository.AuthRepository
import my.tatarinov.kflow.domain.repository.HomeRepository
import my.tatarinov.kflow.presentation.auth.AuthViewModel
import my.tatarinov.kflow.presentation.home.HomeViewModel
import my.tatarinov.kflow.presentation.profile.ProfileViewModel
import my.tatarinov.kflow.presentation.utils.DateFormatter
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

expect val baseUrl: String
expect val platformModule: Module
val dataModule = module {
    single<HttpClient> {
        HttpClient {
            expectSuccess = true
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

    single<HomeApi> {
        HomeApi(get(), get())
    }

    single<AuthRepository> {
        AuthRepositoryImpl(get(), get(), get())
    }

    single<HomeRepository> {
        HomeRepositoryImpl(get(), get())
    }

    single { TokenStorage(get()) }

    single {
        DateFormatter
    }
}

val domainModule = module {
    single<AuthInteractor> {
        AuthInteractorImpl(get())
    }
    single<HomeInteractor> {
        HomeInteractorImpl(get())
    }
    single<TokenInteractor> {
        TokenInteractorImpl(get())
    }
}

val viewModelModule = module {
    viewModelOf(::AuthViewModel)
    viewModelOf(::HomeViewModel)
    viewModelOf(::ProfileViewModel)
}

val appModule = listOf(dataModule, domainModule, viewModelModule, platformModule)