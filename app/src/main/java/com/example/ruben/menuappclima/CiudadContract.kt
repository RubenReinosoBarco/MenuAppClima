package com.example.ruben.menuappclima

import android.provider.BaseColumns

class CiudadContract{


    companion object {
        val VERSION = 1
        class Entrada:BaseColumns{

            companion object {
                val NOMBRE_TABLA = "ciudad"

                val COLUMNA_NOMBRE = "nombre"
                val COLUMNA_ID = "id"
            }

        }
    }
}