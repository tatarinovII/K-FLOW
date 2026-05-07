package my.tatarinov.kflow

import android.app.Application
import my.tatarinov.kflow.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class KFlowApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@KFlowApplication)
            modules(appModule)
        }
    }

}