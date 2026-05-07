package my.tatarinov.kflow.di

import com.liftric.kvault.KVault
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

actual val baseUrl: String = "http://10.0.2.2:8080/"
actual val platformModule: Module = module {
    single { KVault(androidContext(), "kflow_secure_storage") }
}