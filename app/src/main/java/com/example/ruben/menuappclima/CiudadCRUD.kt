package com.example.ruben.menuappclima

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.example.ruben.appclima.Ciudad

class CiudadCRUD(context: Context) {

    private var helper: DataBaseHelper? = null

    init {
        helper = DataBaseHelper(context)
    }

    fun newCiudad(item: Ciudad) {
        val db: SQLiteDatabase = helper?.writableDatabase!!
        //Mapeo de columnas con valores a insertar
        val values = ContentValues()
        values.put(CiudadContract.Companion.Entrada.COLUMNA_NOMBRE, item.nombre)
        values.put(CiudadContract.Companion.Entrada.COLUMNA_ID, item.id)

        //Insertar una nueva fila en la tabla
        val newRowId = db.insert(CiudadContract.Companion.Entrada.NOMBRE_TABLA, null, values)
        db.close()

    }

    fun getCiudad(): ArrayList<Ciudad> {

        val items: ArrayList<Ciudad> = ArrayList()
        // Abrir la base de datos modo lectura
        val db: SQLiteDatabase = helper?.readableDatabase!!


        //Especificar las columnas que quiero consultar

        val columnas = arrayOf(CiudadContract.Companion.Entrada.COLUMNA_NOMBRE, CiudadContract.Companion.Entrada.COLUMNA_ID)

        //Crear un cursor para recorrer la tabla

        val c: Cursor = db.query(
                CiudadContract.Companion.Entrada.NOMBRE_TABLA,
                columnas,
                null,
                null,
                null,
                null,
                null
        )

        //Hacer el recorrido del cursor en la talba

        while (c.moveToNext()) {
            items.add(Ciudad(
                    c.getString(c.getColumnIndexOrThrow(CiudadContract.Companion.Entrada.COLUMNA_NOMBRE)),
                    c.getString(c.getColumnIndexOrThrow(CiudadContract.Companion.Entrada.COLUMNA_ID))
            ))
        }

        //Cerrar DB
        db.close()

        return items

    }

    fun getCiudad(id: String): Ciudad {

        var item: Ciudad? = null

        val db: SQLiteDatabase = helper?.readableDatabase!!
        val columnas = arrayOf(CiudadContract.Companion.Entrada.COLUMNA_NOMBRE,
                CiudadContract.Companion.Entrada.COLUMNA_ID)

        val c: Cursor = db.query(
                CiudadContract.Companion.Entrada.NOMBRE_TABLA,
                columnas,
                "id = ?",
                arrayOf(id),
                null,
                null,
                null
        )

        while (c.moveToNext()) {
            item = Ciudad(c.getString(c.getColumnIndexOrThrow(CiudadContract.Companion.Entrada.COLUMNA_NOMBRE)),
                    c.getString(c.getColumnIndexOrThrow(CiudadContract.Companion.Entrada.COLUMNA_ID)))
        }

        c.close()

        return item!!;
    }

    fun updateCiudad(item: Ciudad) {
        val db: SQLiteDatabase = helper?.writableDatabase!!

        val values = ContentValues()
        values.put(CiudadContract.Companion.Entrada.COLUMNA_NOMBRE, item.nombre)
        values.put(CiudadContract.Companion.Entrada.COLUMNA_ID, item.id)

        db.update(
                CiudadContract.Companion.Entrada.NOMBRE_TABLA,
                values,
                "nombre=?",
                arrayOf(item.nombre)
        )
        db.close()
    }

    fun deletaCiudad(item: Ciudad) {
        val db: SQLiteDatabase = helper?.writableDatabase!!

        db.delete(CiudadContract.Companion.Entrada.NOMBRE_TABLA,
                "nombre=?",
                arrayOf(item.nombre))
    }
}