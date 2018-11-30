package com.example.ruben.menuappclima.fragments

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ruben.menuappclima.R
import com.example.ruben.menuappclima.activities.ActivityClima
import com.example.ruben.menuappclima.adapters.CiudadAdapter
import com.example.ruben.menuappclima.listeners.RecyclerCiudadListener
import com.example.ruben.menuappclima.models.MCiudad
import com.example.ruben.menuappclima.toast
import kotlinx.android.synthetic.main.fragment_departures.view.*
import com.example.ruben.menuappclima.CiudadCRUD



class CiudadesFragment : Fragment() {

    private val list: ArrayList<MCiudad> by lazy { getFlights() }

    private lateinit var recycler: RecyclerView

    private lateinit var adapter: CiudadAdapter

    private val layautManager by lazy { LinearLayoutManager(context) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        activity?.setTitle(R.string.departures_fragment_title)
        val rootView = inflater.inflate(R.layout.fragment_departures, container, false)

        recycler = rootView.recyclerView as RecyclerView

        setRecyclerView()
        return rootView
    }

    private fun setRecyclerView() {

        recycler.setHasFixedSize(true)
        recycler.itemAnimator = DefaultItemAnimator()
        recycler.layoutManager = layautManager
        adapter = (CiudadAdapter(list, object : RecyclerCiudadListener {
            override fun onClick(MCiudad: MCiudad, position: Int) {
                activity?.toast("Vamonos a ${MCiudad.city}!")
                val intent = Intent(context, ActivityClima::class.java)
                intent.putExtra("id", MCiudad.id)
                startActivity(intent)

            }

            override fun onDelete(MCiudad: MCiudad, position: Int) {

                list.remove(MCiudad)
                adapter.notifyItemRemoved(position)
                activity?.toast("${MCiudad.city} a sido borrada")

            }
        }))
        recycler.adapter = adapter
    }

    private fun getFlights(): ArrayList<MCiudad> {

        return object : ArrayList<MCiudad>() {

            init {
                add(MCiudad(2950159, "Berlin", R.drawable.ciudad1))
                add(MCiudad(2517117, "Granada", R.drawable.ciudad2))
                add(MCiudad(3646738, "Caracas", R.drawable.ciudad3))
                add(MCiudad(3114711, "Oviedo", R.drawable.ciudad4))
                add(MCiudad(5128581, "New York", R.drawable.ciudad5))
                add(MCiudad(6361046, "Sevilla", R.drawable.ciudad6))
//                var crud = CiudadCRUD(context!!.applicationContext)
//                var lista = crud.getCiudad()
//                for (b in lista.iterator())
//                    add(MCiudad(b.id!!, b.nombre!!,R.drawable.ciudad1))
            }
        }
    }
}
