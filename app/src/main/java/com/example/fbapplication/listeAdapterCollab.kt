package com.example.fbapplication

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.fbapplication.R
import com.example.fbapplication.models.Tache
import com.example.fbapplication.models.Users


class listeAdapterCollab(private val context: Activity, private val title: ArrayList<Users>)
    : ArrayAdapter<Users>(context, R.layout.collab_list, title) {
    override fun getView(position: Int, view: View?, parent: ViewGroup): View
    {
        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.collab_list, null, true)
        val nom = rowView.findViewById(R.id.nom) as TextView
        val prenom = rowView.findViewById(R.id.prenom) as TextView
        val email = rowView.findViewById(R.id.email) as TextView
        val role = rowView.findViewById(R.id.role) as TextView
        nom.text = title[position].NOM
        prenom.text = title[position].PRENOM
        email.text = title[position].EMAIL
        role.text = title[position].ROLE
        return rowView
    }
}