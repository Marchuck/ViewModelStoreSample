package pl.marczak.viewmodelstoresample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.commitNow
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelStore
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    val activityScopedViewModel by provideStoredViewModel<CounterViewModel>(
        { viewModelStore },
        "Activity Scoped"
    )

    val applicationScopedViewModel by provideStoredViewModel<CounterViewModel>(
        { app.applicationStore },
        "Application Scoped"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            setupFragment()
        }

        applicationScopedViewModel.counter.observe { value ->
            counter_app_scope.bind(
                newValue = value,
                onDecrement = applicationScopedViewModel::decrement,
                onIncrement = applicationScopedViewModel::increment
            )
        }

        activityScopedViewModel.counter.observe { value ->
            counter_activity_scope.bind(
                value,
                activityScopedViewModel::decrement,
                activityScopedViewModel::increment
            )
        }

        button_back.setOnClickListener { onBackPressed() }
        button_mess.setOnClickListener { setupFragment() }
    }

    private fun setupFragment() {
        supportFragmentManager.commitNow {
            replace(R.id.fragment_container, CounterFragment())
        }
    }

    private fun lifecycleOwner(): LifecycleOwner = this

    private fun <T> LiveData<T>.observe(observer: (T) -> Unit) {
        observe(lifecycleOwner(), Observer(observer))
    }
}
