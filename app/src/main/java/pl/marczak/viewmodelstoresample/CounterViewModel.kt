package pl.marczak.viewmodelstoresample

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class CounterViewModel(private val name: String) : ViewModel() {

    val counter = MutableLiveData<Int>().apply { value = initialValue() }

    fun increment() {
        Log.d("CounterViewModel", "increment() called on $name!")
        val currentValue = getCurrentValue()
        counter.value = currentValue + 1
    }

    fun decrement() {
        Log.d("CounterViewModel", "decrement() called on $name!")
        val currentValue = getCurrentValue()
        counter.value = currentValue - 1
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("CounterViewModel", "onCleared() called on $name!")
        counter.value = initialValue()
    }

    private fun getCurrentValue(): Int = counter.value ?: initialValue()

    private fun initialValue() = 0
}
