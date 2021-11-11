package mx.tecnm.tepic.u3p1_bdsqlite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button33.setOnClickListener {
            val lanzar = Intent(this, activityConductor::class.java)
            startActivity(lanzar)
        }

        button43.setOnClickListener {
            val lanzar = Intent(this, activityVehiculo::class.java)
            startActivity(lanzar)
        }

    }
}