package my.tatarinov.kflow.di

import com.liftric.kvault.KVault
import org.koin.core.module.Module
import org.koin.dsl.module

actual val baseUrl: String = "http://192.168.0.221:8080"
actual val platformModule: Module =  module {
    single { KVault(serviceName = "my.tatarinov.kflow.auth") }
}