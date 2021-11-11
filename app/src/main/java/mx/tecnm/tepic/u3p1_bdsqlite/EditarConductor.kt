package mx.tecnm.tepic.u3p1_bdsqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_editar_conductor.*

class EditarConductor : AppCompatActivity() {
    var id = " "
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_conductor)

        var extra = intent.extras
        id = extra!!.getString("idActualizar")!!

        val conductor = Conductor(this).consultar(id)
        ActualizarNombre.setText(conductor.nombre)
        ActualizarDomicilio.setText(conductor.domicilio)
        ActualizarLicencia.setText(conductor.nolicencia)


        button1.setOnClickListener {
            val conducUpda= Conductor(this)
            conducUpda.nombre=ActualizarNombre.text.toString()
            conducUpda.domicilio=ActualizarDomicilio.text.toString()
            conducUpda.nolicencia=ActualizarLicencia.text.toString()
            val res= conducUpda.actulizar(id)
            if(res){
                Toast.makeText(this,"SE ACTUALIZO CORRECTAMENTE", Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this,"ERROR NO ACTUALIZO CORRECTAMENTE", Toast.LENGTH_LONG).show()
            }
        }

        button2.setOnClickListener {
            finish()
        }

    }
}