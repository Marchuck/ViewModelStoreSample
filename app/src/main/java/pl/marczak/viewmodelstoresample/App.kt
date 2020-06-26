package pl.marczak.viewmodelstoresample

import android.app.Application
import androidx.lifecycle.ViewModelStore

class App : Application() {

    val applicationStore = ViewModelStore()
}
