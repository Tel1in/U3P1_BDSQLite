package mx.tecnm.tepic.u3p1_bdsqlite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_conductor.*
import kotlinx.android.synthetic.main.activity_vehiculo.*

class activityVehiculo : AppCompatActivity() {
    var listID= ArrayList<Int>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vehiculo)
        button8.setOnClickListener {
            val vehiculo = Vehiculo(this)
            vehiculo.placa = Placa.text.toString()
            vehiculo.marca = Marca.text.toString()
            vehiculo.modelos = Modelo.text.toString()
            vehiculo.idCon= idConductor.text.toString()

            val resultados = vehiculo.insertar()
            if(resultados){
                Toast.makeText(this,"SE INSERTO CORRECTAMENTE", Toast.LENGTH_LONG).show()
                Placa.text.clear()
                Marca.text.clear()
                Modelo.text.clear()
                idConductor.text.clear()
                mostrarVehiculo()
            }else{
                Toast.makeText(this,"NO SE INSERTO CORRECTAMENTE", Toast.LENGTH_LONG).show()
            }

        }

        button14.setOnClickListener {
            finish()
        }
    }

    fun mostrarVehiculo(){
        val arreglovehiculos = Vehiculo(this).consultar()
        listaVehiculo.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,arreglovehiculos)
        listID.clear()
        listID = Vehiculo(this).obtenerid()
        activarEvento(listaVehiculo)
    }

    private fun activarEvento(listaVehiculo: ListView) {
        listaVehiculo.setOnItemClickListener { adapterView, view, indiceSeleccionado, l ->
            val idSeleccionado = listID[indiceSeleccionado]
            AlertDialog.Builder(this)
                .setTitle("ATENCIO")
                .setMessage("Que desea hacer con el vehiculo")
                .setPositiveButton("EDITAR"){d,i -> actualizar(idSeleccionado)}
                .setNegativeButton("Eliminar"){d, i -> eliminar(idSeleccionado)}
                .setNeutralButton("Cancelar"){id, i->}
                .show()
        }

    }

    private fun actualizar(idSeleccionado: Int){
        val intento = Intent(this, EditarVehiculo::class.java)
        intento.putExtra("idActualizar", idSeleccionado.toString())
        startActivity(intento)

        AlertDialog.Builder(this).setMessage("QUIERES ACTUALIZAR LA LISTA")
            .setPositiveButton("si"){d,i-> mostrarVehiculo()}
            .setNegativeButton("no"){d,i-> d.cancel()}
            .show()

    }

    private fun eliminar(idSeleccionado: Int) {
        AlertDialog.Builder(this)
            .setTitle("ATENCIO")
            .setMessage("Seguro que deseas eliminar")
            .setPositiveButton("SI"){d,i ->
                val resultado = Vehiculo(this).eliminar(idSeleccionado)
                if(resultado){
                    Toast.makeText(this,"SE ELIMINO CORRECTAMENTE",Toast.LENGTH_LONG).show()
                    mostrarVehiculo()
                }else{
                    Toast.makeText(this,"NO SE ELIMINO CORRECTAMENTE",Toast.LENGTH_LONG).show()
                }
            }
            .setNegativeButton("NO"){d,i->
                d.cancel()
            }
            .show()

    }






}