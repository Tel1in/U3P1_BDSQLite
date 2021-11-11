package mx.tecnm.tepic.u3p1_bdsqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_editar_conductor.*
import kotlinx.android.synthetic.main.activity_editar_vehiculo.*

class EditarVehiculo : AppCompatActivity() {
    var id = " "
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_vehiculo)
        var extra = intent.extras
        id = extra!!.getString("idActualizar")!!

        val vehiculos = Vehiculo(this).consultar(id)
        editarPlaca.setText(vehiculos.placa)
        editarMarca.setText(vehiculos.marca)
        ActualizarModelo.setText(vehiculos.modelos)
        ActualizarConductor.setText(vehiculos.idCon)

        button312.setOnClickListener {
            val conducUpda= Vehiculo(this)
            conducUpda.placa=editarPlaca.text.toString()
            conducUpda.marca=editarMarca.text.toString()
            conducUpda.modelos=ActualizarModelo.text.toString()
            conducUpda.idCon=ActualizarConductor.text.toString()
            val res= conducUpda.actulizar(id)
            if(res){
                Toast.makeText(this,"SE ACTUALIZO CORRECTAMENTE", Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this,"ERROR NO ACTUALIZO CORRECTAMENTE", Toast.LENGTH_LONG).show()
            }
        }

        button412.setOnClickListener {
            finish()
        }


    }
}