package pl.marczak.viewmodelstoresample

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.*
import kotlinx.android.synthetic.main.fragment_counter.*

class CounterFragment : Fragment(R.layout.fragment_counter) {

    val narrowedViewModelStore = ViewModelStore()

    val fragmentViewModel by provideStoredViewModel<CounterViewModel>(
        { viewModelStore },
        "Fragment Scoped"
    )

    val narrowedViewModel by provideStoredViewModel<CounterViewModel>(
        { narrowedViewModelStore },
        "Fragment-narrow Scoped"
    )

    val extendedViewModel by viewModels<CounterViewModel1>(
        ownerProducer = { requireActivity() },
        factoryProducer = adHocFactoryProducer { CounterViewModel1("Fragment-extended Scoped") }
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragmentViewModel.counter.observe {
            counter_fragment_scope.bind(
                it,
                fragmentViewModel::decrement,
                fragmentViewModel::increment
            )
        }

        narrowedViewModel.counter.observe {
            counter_narrowed_scope.bind(
                it,
                narrowedViewModel::decrement,
                narrowedViewModel::increment
            )
        }
        extendedViewModel.counter.observe {
            counter_extended_scope.bind(
                it,
                extendedViewModel::decrement,
                extendedViewModel::increment
            )
        }
    }

    override fun onPause() {
        super.onPause()
        narrowedViewModelStore.clear()
    }

    private fun <T> LiveData<T>.observe(observer: (T) -> Unit) {
        observe(viewLifecycleOwner, Observer(observer))
    }
}
