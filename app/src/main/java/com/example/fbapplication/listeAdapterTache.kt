package com.example.fbapplication

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.fbapplication.R
import com.example.fbapplication.models.Tache


class listeAdapterTache(private val context: Activity, private val title: ArrayList<Tache>)
    : ArrayAdapter<Tache>(context, R.layout.tache_list, title) {
    override fun getView(position: Int, view: View?, parent: ViewGroup): View
    {
        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.tache_list, null, true)
        val vtitle = rowView.findViewById(R.id.title) as TextView
        val vdescirption = rowView.findViewById(R.id.description) as TextView
        val vcollaborateur = rowView.findViewById(R.id.collaborateur) as TextView
        val vstatus = rowView.findViewById(R.id.status) as TextView
        vtitle.text = title[position].TACHE
        vdescirption.text = title[position].DESCRIPTION1
        vcollaborateur.text = title[position].PROJET
        vstatus.text = title[position].STATUS
        return rowView
    }
}