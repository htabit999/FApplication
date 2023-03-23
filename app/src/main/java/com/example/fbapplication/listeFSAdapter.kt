package com.example.fbapplication

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.fbapplication.R
import com.example.fbapplication.models.Project

class listeFSAdapter(private val context: Activity, private val title: ArrayList<Project>)
    : ArrayAdapter<Project>(context, R.layout.custom_list, title) {
    override fun getView(position: Int, view: View?, parent: ViewGroup): View
    {
        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.custom_list, null, true)
        val titleText = rowView.findViewById(R.id.title) as TextView
        val imageView = rowView.findViewById(R.id.icon) as ImageView
        val subtitleText = rowView.findViewById(R.id.description) as TextView
        val statusText = rowView.findViewById(R.id.status) as TextView
        //Toast.makeText(this, "Titre = " + title[position].PROJET, Toast.LENGTH_LONG).show()
        titleText.text = title[position].PROJET
        statusText.text = title[position].STATUS

        when (title[position].AVANCEMENT)
         {
             10 -> imageView.setImageResource(R.drawable.d)
             20 -> imageView.setImageResource(R.drawable.v)
             30 -> imageView.setImageResource(R.drawable.t)
             40 -> imageView.setImageResource(R.drawable.k)
             50 -> imageView.setImageResource(R.drawable.c)
             60 -> imageView.setImageResource(R.drawable.s)
             70 -> imageView.setImageResource(R.drawable.sd)
             80 -> imageView.setImageResource(R.drawable.kv)
             90 -> imageView.setImageResource(R.drawable.kd)
             else -> {
                 imageView.setImageResource(R.drawable.d)
                }
         }
        subtitleText.text = title[position].PROJET
        return rowView
    }
}