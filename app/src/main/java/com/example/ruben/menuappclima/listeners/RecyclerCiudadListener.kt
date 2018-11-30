package com.example.ruben.menuappclima.listeners

import com.example.ruben.menuappclima.models.MCiudad

interface RecyclerCiudadListener {
    fun onClick(MCiudad: MCiudad, position: Int)
    fun onDelete(MCiudad: MCiudad, position: Int)
}