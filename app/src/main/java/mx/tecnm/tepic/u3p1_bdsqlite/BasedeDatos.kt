package mx.tecnm.tepic.u3p1_bdsqlite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class BasedeDatos(
    context: Context?,
    name: String?,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int
) : SQLiteOpenHelper(context, name, factory, version) {


    override fun onCreate(p: SQLiteDatabase) {
        p.execSQL("CREATE TABLE CONDUCTOR(IDCONDUCTOR INTEGER PRIMARY KEY AUTOINCREMENT, NOMBRE VARCHAR(200),DOMICILIO VARCHAR(200),NOLICENCIA VARCHAR(200))")
        p.execSQL("CREATE TABLE VEHICULO(IDVEHICULO INTEGER PRIMARY KEY AUTOINCREMENT, PLACA VARCHAR(200),MARCA VARCHAR(200),MODELO VARCHAR(200),IDCONDUCTOR REFERENCES CONDUCTOR(IDCONDUCTOR))")

    }

    override fun onUpgrade(p: SQLiteDatabase, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

}