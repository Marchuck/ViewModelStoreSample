package pl.marczak.viewmodelstoresample

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelLazy
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore


val Context.app: App
    get() = applicationContext as App

class CounterViewModelFactory(private val name: String) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CounterViewModel(name) as T
    }
}

inline fun <reified T : ViewModel> provideStoredViewModel(
    noinline storeProducer: () -> ViewModelStore,
    name: String
) =
    ViewModelLazy(
        T::class,
        storeProducer,
        { CounterViewModelFactory(name) })

