package com.example.ruben.menuappclima.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.ruben.menuappclima.R
import com.google.gson.Gson
import com.android.volley.Request
import com.example.ruben.appclima.Network
import com.example.ruben.menuappclima.BuildConfig
import com.example.ruben.menuappclima.Ciudad2

class ActivityClima : AppCompatActivity() {

    var tvCiudad:TextView? = null
    var tvGrados:TextView? = null
    var tvEstatus:TextView? = null

    var api:String = BuildConfig.API_KEY

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ciudades)

        tvCiudad = findViewById(R.id.tvCiudad)
        tvGrados = findViewById(R.id.tvGrados)
        tvEstatus = findViewById(R.id.tvEstatus)

        val ciudad = intent.getStringExtra("com.example.ruben.menuappclima.ciudades.CIUDAD")
        val ciudad2 = intent.getIntExtra("id",2950159).toString()

        if (Network.hayRed(this)){
            //Ejecutar solicitud HTTP
            //ciudad de mexico id 3530597S
            //berlin 2950159
            solicitudadHTTPVolley("http://api.openweathermap.org/data/2.5/weather?id="+ciudad2+"&appid="+api+"&units=metric&lang=es")

        } else{
            //Mostrar mensaje de error
            Toast.makeText(this, "No hay red", Toast.LENGTH_SHORT).show()
        }

    }
    //Método para Volley

    private fun solicitudadHTTPVolley(url: String) {

        val queue = Volley.newRequestQueue(this)
        val solicitud = StringRequest(Request.Method.GET, url, Response.Listener<String> { response ->
            try {
                Log.d("solicitudHTTPVolley", response)

                val gson = Gson()
                val ciudad = gson.fromJson(response, Ciudad2::class.java)
                tvCiudad?.text = ciudad.name
                tvGrados?.text = ciudad.main?.temp.toString() + "º"
                tvEstatus?.text = ciudad.weather?.get(0)?.description

            } catch (e: Exception) {

            }
        }, Response.ErrorListener { })
        queue.add(solicitud)
    }
}
