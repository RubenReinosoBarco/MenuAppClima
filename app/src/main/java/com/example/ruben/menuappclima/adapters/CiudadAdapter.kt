package com.example.ruben.menuappclima.adapters

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.example.ruben.menuappclima.R
import com.example.ruben.menuappclima.inflate
import com.example.ruben.menuappclima.listeners.RecyclerCiudadListener

import com.example.ruben.menuappclima.loadByResource
import com.example.ruben.menuappclima.models.MCiudad
import kotlinx.android.synthetic.main.reclycler_flight.view.*

class CiudadAdapter(private val MCiudads:List<MCiudad>, private val listener: RecyclerCiudadListener)
    : RecyclerView.Adapter<CiudadAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(parent.inflate(R.layout.reclycler_flight))

    override fun onBindViewHolder(holder: ViewHolder, position: Int)= holder.bind(MCiudads[position], listener)

    override fun getItemCount()= MCiudads.size

    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        fun bind(MCiudad: MCiudad, listener: RecyclerCiudadListener)=with(itemView){
            textViewCityName.text = MCiudad.city
            imageViewBg.loadByResource(MCiudad.imgResource)
            //Clicks Events

            setOnClickListener{ listener.onClick(MCiudad, adapterPosition)}
            buttonDelete.setOnClickListener{listener.onDelete(MCiudad, adapterPosition)}
        }
    }

}

