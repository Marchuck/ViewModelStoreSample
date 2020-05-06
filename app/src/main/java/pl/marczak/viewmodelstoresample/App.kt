package pl.marczak.viewmodelstoresample

import android.app.Application
import androidx.lifecycle.ViewModelStore

class App : Application() {

    lateinit var applicationStore: ViewModelStore

    override fun onCreate() {
        super.onCreate()
        applicationStore = ViewModelStore()
    }
}
