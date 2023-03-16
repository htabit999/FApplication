package com.example.fbapplication

import android.app.Activity
import android.content.Intent
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class listeAdapterSuivi(private val context: Activity, private val title: Array<String>, private val chef: Array<String>,private val status: Array<String>, private val imgid: Array<Int>)
        : ArrayAdapter<String>(context, R.layout.suivi_list, title) {
        override fun getView(position: Int, view: View?, parent: ViewGroup): View
        {
            val inflater = context.layoutInflater
            val rowView = inflater.inflate(R.layout.suivi_list, null, true)
            val titleText = rowView.findViewById(R.id.title) as TextView
            val imageView = rowView.findViewById(R.id.icon ) as ImageView
            val chefText = rowView.findViewById(R.id.chef) as TextView
            val statusText = rowView.findViewById(R.id.status) as TextView
            titleText.text = title[position]
            chefText.text = chef[position]
            statusText.text = status[position]
            imageView.setImageResource(imgid[position])
            return rowView
        }
}

