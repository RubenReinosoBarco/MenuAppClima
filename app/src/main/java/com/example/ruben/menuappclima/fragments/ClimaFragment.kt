package com.example.ruben.menuappclima.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ruben.menuappclima.CiudadCRUD

import com.example.ruben.menuappclima.R

class ClimaFragment : Fragment() {



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        activity?.setTitle(R.string.arrivals_fragment_title)


        return inflater.inflate(R.layout.activity_ciudades, container, false)
    }


}