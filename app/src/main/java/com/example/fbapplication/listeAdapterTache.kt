package com.example.fbapplication

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView


class listeAdapterTache(private val context: Activity, private val title: Array<String>, private val description: Array<String>, private val collaborateur: Array<String>,private val status: Array<String>)
    : ArrayAdapter<String>(context, R.layout.tache_list, title) {
    override fun getView(position: Int, view: View?, parent: ViewGroup): View
    {
        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.tache_list, null, true)
        val vtitle = rowView.findViewById(R.id.title ) as TextView
        val vdescirption = rowView.findViewById(R.id.description) as TextView
        val vcollaborateur = rowView.findViewById(R.id.collaborateur) as TextView
        val vstatus = rowView.findViewById(R.id.status) as TextView
        vtitle.text = title[position]
        vdescirption.text = description[position]
        vcollaborateur.text = collaborateur[position]
        vstatus.text = status[position]
        return rowView
    }
}