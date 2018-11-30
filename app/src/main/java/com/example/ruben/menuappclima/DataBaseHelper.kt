package com.example.ruben.menuappclima

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DataBaseHelper (context : Context): SQLiteOpenHelper(context, CiudadContract.Companion.Entrada.NOMBRE_TABLA, null, CiudadContract.Companion.VERSION) {

    companion object {
        val CREATE_CIUDAD_TABLA = "CREATE TABLE " + CiudadContract.Companion.Entrada.NOMBRE_TABLA +
                " (" + CiudadContract.Companion.Entrada.COLUMNA_NOMBRE + " TEXT PRIMARY KEY, " +
                CiudadContract.Companion.Entrada.COLUMNA_ID + " TEXT )"

        val REMOVE_CIUDAD_TABLA = "DROP TABLE IF EXISTS " + CiudadContract.Companion.Entrada.NOMBRE_TABLA
    }

    override fun onCreate(db: SQLiteDatabase?) {

        db?.execSQL(CREATE_CIUDAD_TABLA)
    }

    override fun onUpgrade(db: SQLiteDatabase?, i: Int, i2: Int) {

        db?.execSQL(REMOVE_CIUDAD_TABLA)
        onCreate(db)


    }


}