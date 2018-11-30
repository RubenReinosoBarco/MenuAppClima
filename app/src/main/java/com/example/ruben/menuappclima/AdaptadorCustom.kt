package com.example.ruben.menuappclima

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.ruben.appclima.Ciudad

class AdaptadorCustom(items:ArrayList<Ciudad>, var listener: ClickListener, var longClickListener: LongClickListener): RecyclerView.Adapter<AdaptadorCustom.ViewHolder>() {


    var items: ArrayList<Ciudad>? =null
    var multiSeleccion = false

    init {
        this.items = items
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdaptadorCustom.ViewHolder{

        val vista = LayoutInflater.from(parent.context).inflate(R.layout.fragment_departures, parent, false)
        val viewHolder = ViewHolder(vista,listener, longClickListener)

        return viewHolder!!
    }

    override fun getItemCount(): Int {
        return items?.count()!!
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = items?.get(position)
        holder.nombre?.text = item?.nombre
        holder.id?.text = item?.id

    }

    fun iniciarActionMove(){

        multiSeleccion = true

    }

    fun destruirActionMode(){
        multiSeleccion = false
        notifyDataSetChanged()
    }

    fun terminarActionMode(){
        //eleminar elementos seleccionados
        multiSeleccion = false
    }


    class ViewHolder(vista: View, listener:ClickListener, longClickListener: LongClickListener):RecyclerView.ViewHolder(vista), View.OnClickListener, View.OnLongClickListener{



        var vista = vista
        var nombre: TextView?=null
        var id: TextView?=null
        var listener: ClickListener? = null
        var longListener:LongClickListener? = null
        init {
            nombre = vista.findViewById(R.id.textViewCityName)
            id = vista.findViewById(R.id.textViewCityNameId)

            this.listener = listener
            this.longListener = longClickListener

            vista.setOnClickListener(this)
            vista.setOnLongClickListener(this)
        }

        override fun onClick(v: View?) {
            this.listener?.onClick(v!!, adapterPosition)
        }

        override fun onLongClick(v: View?): Boolean {
            this.longListener?.longClickListener(v!!, adapterPosition)
            return true
        }
    }
}