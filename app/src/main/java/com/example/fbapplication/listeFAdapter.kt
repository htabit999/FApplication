package com.example.fbapplication

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.example.fbapplication.R
import com.example.fbapplication.models.Project
import com.example.fbapplication.models.Usr
import com.squareup.picasso.Picasso

class listeFAdapter(private val context: Activity, private val title: ArrayList<Project>)
    : ArrayAdapter<Project>(context, R.layout.custom_fslist, title) {
    override fun getView(position: Int, view: View?, parent: ViewGroup): View
    {
        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.custom_fslist, null, true)
        val ttitle = rowView.findViewById(R.id.title) as TextView
        val description = rowView.findViewById(R.id.description) as TextView
        val status = rowView.findViewById(R.id.status) as TextView
        val datedeb = rowView.findViewById(R.id.datedeb) as TextView
        val datefin = rowView.findViewById(R.id.datefin) as TextView
        val avancement = rowView.findViewById(R.id.avancement) as TextView
        var progressBar = rowView.findViewById(R.id.progressBar1) as ProgressBar
        val image = rowView.findViewById(R.id.icon) as ImageView
        ttitle.text = title[position].PROJET
        status.text = "Status : " + title[position].STATUS
        description.text =title[position].DESCRIPTION1
        datedeb.text = "Du : " + title[position].DATEDEB
        datefin.text = "Au : " + title[position].DATEFIN
        avancement.text = "Taux d avancement : " + title[position].AVANCEMENT.toString() + "%"
        progressBar.progress = title[position].AVANCEMENT
        return rowView
    }
}