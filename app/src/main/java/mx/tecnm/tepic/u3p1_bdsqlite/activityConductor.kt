package mx.tecnm.tepic.u3p1_bdsqlite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_conductor.*

class activityConductor : AppCompatActivity() {
    var listID= ArrayList<Int>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_conductor)
        button.setOnClickListener {
            val conductor = Conductor(this)
            conductor.nombre = campoNombre.text.toString()
            conductor.domicilio = campoDomicilio.text.toString()
            conductor.nolicencia = campoLicencia.text.toString()

            val resultados = conductor.insertar()
            if(resultados){
                Toast.makeText(this,"SE INSERTO CORRECTAMENTE", Toast.LENGTH_LONG).show()
                campoNombre.text.clear()
                campoDomicilio.text.clear()
                campoLicencia.text.clear()
                mostrarConductor()
            }else{
                Toast.makeText(this,"NO SE INSERTO CORRECTAMENTE", Toast.LENGTH_LONG).show()
            }

        }
    }

    fun mostrarConductor(){
        val arregloartistas = Conductor(this).consultar()
        listaConductor.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,arregloartistas)
        listID.clear()
        listID = Conductor(this).obtenerid()
        activarEvento(listaConductor)
    }

    private fun activarEvento(listaConductor: ListView) {
        listaConductor.setOnItemClickListener { adapterView, view, indiceSeleccionado, l ->
            val idSeleccionado = listID[indiceSeleccionado]
            AlertDialog.Builder(this)
                .setTitle("ATENCIO")
                .setMessage("Que desea hacer con el conductor")
                .setPositiveButton("EDITAR"){d,i -> actualizar(idSeleccionado)}
                .setNegativeButton("Eliminar"){d, i -> eliminar(idSeleccionado)}
                .setNeutralButton("Cancelar"){id, i->}
                .show()
        }

    }

    private fun actualizar(idSeleccionado: Int){
        val intento = Intent(this, EditarConductor::class.java)
        intento.putExtra("idActualizar", idSeleccionado.toString())
        startActivity(intento)

        AlertDialog.Builder(this).setMessage("QUIERES ACTUALIZAR LA LISTA")
            .setPositiveButton("si"){d,i-> mostrarConductor()}
            .setNegativeButton("no"){d,i-> d.cancel()}
            .show()

    }

    private fun eliminar(idSeleccionado: Int) {
        AlertDialog.Builder(this)
            .setTitle("ATENCIO")
            .setMessage("Seguro que deseas eliminar")
            .setPositiveButton("SI"){d,i ->
                val resultado = Conductor(this).eliminar(idSeleccionado)
                if(resultado){
                    Toast.makeText(this,"SE ELIMINO CORRECTAMENTE",Toast.LENGTH_LONG).show()
                    mostrarConductor()
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