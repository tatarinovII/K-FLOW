package my.tatarinov.kflow

import my.tatarinov.kflow.di.appModule
import org.koin.core.context.startKoin

fun initKoin() {
    startKoin { modules(appModule) }
}