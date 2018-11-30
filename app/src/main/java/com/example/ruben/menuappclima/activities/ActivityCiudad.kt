package com.example.ruben.menuappclima.activities

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.ruben.appclima.Ciudad
import com.example.ruben.menuappclima.*
import com.example.ruben.menuappclima.fragments.NuevaCiudad

class ActivityCiudad : AppCompatActivity() {


    var lista: RecyclerView? = null
    var adaptador: AdaptadorCustom? = null
    var layoutManager: RecyclerView.LayoutManager? = null
    var alumnos:ArrayList<Ciudad>? = null
    var crud: CiudadCRUD? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ciudades_bm)

        val fab = findViewById<FloatingActionButton>(R.id.dab)
        lista = findViewById(R.id.lista)

        lista?.setHasFixedSize(true)

        layoutManager = LinearLayoutManager(this)
        lista?.layoutManager = layoutManager

        fab.setOnClickListener{
            startActivity(Intent(this, NuevaCiudad::class.java))
        }

        crud = CiudadCRUD(this)

        alumnos = crud?.getCiudad()

        adaptador = AdaptadorCustom(alumnos!!, object: ClickListener {
            override fun onClick(vista: View, index: Int) {
                //click
                val intent = Intent(applicationContext, DetalleCiudad::class.java)
                intent.putExtra("ID",alumnos!!.get(index).id)
                startActivity(intent)
            }
        }, object : LongClickListener {
            override fun longClick(vista: View, index: Int) {}
        })
        lista?.adapter = adaptador
    }
}