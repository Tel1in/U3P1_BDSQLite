package mx.tecnm.tepic.u3p1_bdsqlite

import android.content.ContentValues
import android.content.Context


class Vehiculo(p:Context){
    var placa = " "
    var marca = " "
    var modelos = " "
    var idCon = " "

    val pnt = p

    fun insertar() : Boolean{
        val tablaVehiculos = BasedeDatos(pnt,"CARROSSHIDOS", null,1).writableDatabase
        val datos = ContentValues()
        datos.put("placa",placa)
        datos.put("marca",marca)
        datos.put("modelo",modelos)
        datos.put("idconductor",idCon)
        //datos.put("vence",vance)
        val resultado =  tablaVehiculos.insert("VEHICULO",null, datos)
        if(resultado== -1L){
            return false;
        }

        return true
    }
    fun consultar() : ArrayList<String> {
        val tablaVehiculos = BasedeDatos(pnt,"CARROSSHIDOS", null,1).readableDatabase
        val resultado = ArrayList<String>()
        val cursos= tablaVehiculos.query("VEHICULO", arrayOf("*"),null,null,null,null,null)
        if(cursos.moveToFirst()){
            do {
                var dato = cursos.getString(0)+"   "+cursos.getString(1)+"   "+cursos.getString(2)+"  "+cursos.getString(3)+"  "+cursos.getString(4)
                resultado.add(dato)
            }while (cursos.moveToNext())
        }else{
            resultado.add("NO SE ENCOTRO RESULTADOS")
        }

        return resultado

    }

    fun obtenerid() : ArrayList<Int> {
        val tablaVehiculos = BasedeDatos(pnt,"CARROSSHIDOS", null,1).readableDatabase
        val resultado = ArrayList<Int>()
        val cursos= tablaVehiculos.query("VEHICULO", arrayOf("*"),null,null,null,null,null)
        if(cursos.moveToFirst()){
            do {
                resultado.add(cursos.getInt(0))
            }while (cursos.moveToNext())
        }
        return resultado

    }

    fun eliminar(idEliminar: Int): Boolean {
        val tablaVehiculos = BasedeDatos(pnt, "CARROSSHIDOS", null, 1).writableDatabase
        val resultado =
            tablaVehiculos.delete("VEHICULO", "IDVEHICULO=?", arrayOf(idEliminar.toString()))
        if (resultado == 0) return false
        return true
    }

    fun consultar(idAbuscar:String):Vehiculo{
        val tablaVehiculos = BasedeDatos(pnt,"CARROSSHIDOS", null,1).readableDatabase
        val cursos= tablaVehiculos.query("VEHICULO", arrayOf("*"),"IDVEHICULO=?",
            arrayOf(idAbuscar),null,null,null)
        val vehiculos = Vehiculo(MainActivity())
        if(cursos.moveToFirst()) {
            vehiculos.placa= cursos.getString(1)
            vehiculos.marca = cursos.getString(2)
            vehiculos.modelos = cursos.getString(3)
            vehiculos.idCon=cursos.getString(4)
        }
        return vehiculos
    }

    fun actulizar(idActualizar : String) :Boolean{
        val tablaVehiculos = BasedeDatos(pnt,"CARROSSHIDOS", null,1).writableDatabase
        val datos = ContentValues()
        datos.put("placa",placa)
        datos.put("marca",marca)
        datos.put("modelo",modelos)
        datos.put("idconductor",idCon)
        val res = tablaVehiculos.update("VEHICULO",datos,"IDVEHICULO=?", arrayOf(idActualizar))
        if (res == 0) return false
        return true
    }
}