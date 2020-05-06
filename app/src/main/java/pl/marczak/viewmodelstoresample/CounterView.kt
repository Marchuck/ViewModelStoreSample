package pl.marczak.viewmodelstoresample

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.view_counter.view.*

class CounterView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    init {
        View.inflate(context, R.layout.view_counter, this)
    }

    fun bind(
        newValue: Int,
        onDecrement: () -> Unit,
        onIncrement: () -> Unit
    ) {
        button_decrement.setOnClickListener { onDecrement() }
        counter_label.text = newValue.toString()
        button_increment.setOnClickListener { onIncrement() }
    }
}
