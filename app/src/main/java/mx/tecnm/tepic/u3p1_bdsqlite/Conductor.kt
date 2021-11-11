package mx.tecnm.tepic.u3p1_bdsqlite

import android.content.ContentValues
import android.content.Context
import kotlin.collections.ArrayList

class Conductor(p:Context) {

    var nombre = ""
    var domicilio = ""
    var nolicencia = ""
    //var vance = " "
    val pnt = p

    fun insertar() : Boolean{
        val tablaConductor = BasedeDatos(pnt,"CARROSSHIDOS", null,1).writableDatabase
        val datos = ContentValues()
        datos.put("nombre",nombre)
        datos.put("domicilio",domicilio)
        datos.put("nolicencia",nolicencia)
        //datos.put("vence",vance)
        val resultado =  tablaConductor.insert("CONDUCTOR",null, datos)
        if(resultado== -1L){
            return false;
        }

        return true
    }

    fun consultar() : ArrayList<String> {
        val tablaConductor = BasedeDatos(pnt,"CARROSSHIDOS", null,1).readableDatabase
        val resultado = ArrayList<String>()
        val cursos= tablaConductor.query("CONDUCTOR", arrayOf("*"),null,null,null,null,null)
        if(cursos.moveToFirst()){
            do {
                var dato = cursos.getString(0)+"   "+cursos.getString(1)+"   "+cursos.getString(2)+"  "+cursos.getString(3)
                resultado.add(dato)
            }while (cursos.moveToNext())
        }else{
            resultado.add("NO SE ENCOTRO RESULTADOS")
        }

        return resultado

    }

    fun obtenerid() : ArrayList<Int> {
        val tablaConductor = BasedeDatos(pnt,"CARROSSHIDOS", null,1).readableDatabase
        val resultado = ArrayList<Int>()
        val cursos= tablaConductor.query("CONDUCTOR", arrayOf("*"),null,null,null,null,null)
        if(cursos.moveToFirst()){
            do {
                resultado.add(cursos.getInt(0))
            }while (cursos.moveToNext())
        }
        return resultado

    }

    fun eliminar(idEliminar: Int): Boolean {
        val tablaConductor = BasedeDatos(pnt, "CARROSSHIDOS", null, 1).writableDatabase
        val resultado =
            tablaConductor.delete("CONDUCTOR", "IDCONDUCTOR=?", arrayOf(idEliminar.toString()))
        if (resultado == 0) return false
        return true
    }

    fun consultar(idAbuscar:String):Conductor{
        val tablaConductor = BasedeDatos(pnt,"CARROSSHIDOS", null,1).readableDatabase
        val cursos= tablaConductor.query("CONDUCTOR", arrayOf("*"),"IDCONDUCTOR=?",
            arrayOf(idAbuscar),null,null,null)
        val condutor = Conductor(MainActivity())
        if(cursos.moveToFirst()) {
            condutor.nombre = cursos.getString(1)
            condutor.domicilio = cursos.getString(2)
            condutor.nolicencia = cursos.getString(3)
        }
        return condutor
    }

    fun actulizar(idActualizar : String) :Boolean{
        val tablaConductor = BasedeDatos(pnt,"CARROSSHIDOS", null,1).writableDatabase
        val datos = ContentValues()
        datos.put("nombre",nombre)
        datos.put("domicilio",domicilio)
        datos.put("nolicencia",nolicencia)
        val res = tablaConductor.update("CONDUCTOR",datos,"IDCONDUCTOR=?", arrayOf(idActualizar))
        if (res == 0) return false
        return true
    }

}