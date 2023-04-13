package com.example.fbapplication

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.fbapplication.models.Project

class listeAdapterSuivi(private val context: Activity, private val title: ArrayList<Project>)
        : ArrayAdapter<Project>(context, R.layout.suivi_list, title) {
        override fun getView(position: Int, view: View?, parent: ViewGroup): View {

            val inflater = context.layoutInflater
            val rowView = inflater.inflate(R.layout.suivi_list, null, true)
            val titleText = rowView.findViewById(R.id.title) as TextView
            val imageView = rowView.findViewById(R.id.icon) as ImageView
            val chefText = rowView.findViewById(R.id.chef) as TextView
            val statusText = rowView.findViewById(R.id.status) as TextView
            titleText.text = title[position].PROJET
            chefText.text = title[position].DESCRIPTION1
            statusText.text = title[position].STATUS

            when (title[position].AVANCEMENT) {
                10 -> {
                    imageView.setImageResource(R.drawable.d)
                }
                20 -> {
                    imageView.setImageResource(R.drawable.v)
                }
                30 -> {
                    imageView.setImageResource(R.drawable.t)
                }
                40 -> {
                    imageView.setImageResource(R.drawable.k)
                }
                50 -> {
                    imageView.setImageResource(R.drawable.c)
                }
                60 -> {
                    imageView.setImageResource(R.drawable.s)
                }
                70 -> {
                    imageView.setImageResource(R.drawable.sd)
                }
                80 -> {
                    imageView.setImageResource(R.drawable.kv)
                }
                90 -> {
                    imageView.setImageResource(R.drawable.kd)
                }
                100 -> {
                    imageView.setImageResource(R.drawable.kd)
                }
                0 -> {
                    imageView.setImageResource(R.drawable.d)
                }
                //imageView.setImageResource(title[position])
            }
            return rowView
        }
}

