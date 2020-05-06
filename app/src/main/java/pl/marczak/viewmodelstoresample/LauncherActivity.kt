package pl.marczak.viewmodelstoresample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_launcher.*

class LauncherActivity : AppCompatActivity(R.layout.activity_launcher) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        button_start.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}
