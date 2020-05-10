package pl.marczak.viewmodelstoresample

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelLazy
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore


val Context.app: App
    get() = applicationContext as App

inline fun <reified T : ViewModel> provideStoredViewModel(
    noinline storeProducer: () -> ViewModelStore,
    name: String
) =
    ViewModelLazy(
        T::class,
        storeProducer,
        { adHocFactory { CounterViewModel(name) } })


@Suppress("UNCHECKED_CAST")
fun <T : ViewModel> adHocFactory(factory: () -> T): ViewModelProvider.Factory {
    return object : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>) =
            factory.invoke() as T
    }
}
