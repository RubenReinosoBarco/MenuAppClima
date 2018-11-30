package com.example.ruben.menuappclima.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.ruben.appclima.Ciudad
import com.example.ruben.menuappclima.CiudadCRUD
import com.example.ruben.menuappclima.R

class DetalleCiudad : AppCompatActivity() {

    var crud: CiudadCRUD? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_ciudad)

        val id = findViewById<EditText>(R.id.etID)
        val nombre = findViewById<EditText>(R.id.etNombre)
        val bActualizar = findViewById<Button>(R.id.bActualizar)
        val bEliminar = findViewById<Button>(R.id.bEliminar)

        val index = intent.getStringExtra("ID")

        crud = CiudadCRUD(this)

        val alumno = crud?.getCiudad(index)

        id.setText(alumno!!.id, TextView.BufferType.EDITABLE)
        nombre.setText(alumno!!.nombre, TextView.BufferType.EDITABLE)

        bActualizar.setOnClickListener{

            crud?.updateCiudad(Ciudad(id.text.toString(), nombre.text.toString()))
            startActivity(Intent(this, MainActivity::class.java))
        }

        bEliminar.setOnClickListener{
            crud?.deletaCiudad(Ciudad(id.text.toString(), nombre.text.toString()))
            startActivity(Intent(this, MainActivity::class.java))

        }
    }
}