package com.example.fbapplication

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.fbapplication.models.Project
import com.example.fbapplication.models.Tache
import de.hdodenhof.circleimageview.CircleImageView

class listeAdapterSuiviTache(private val context: Activity, private val title: ArrayList<Tache>)
    : ArrayAdapter<Tache>(context, R.layout.suivitache, title) {
    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.suivitache, null, true)
        val vtitle = rowView.findViewById(R.id.title) as TextView
        val vdescirption = rowView.findViewById(R.id.description) as TextView
        val vcollaborateur = rowView.findViewById(R.id.collaborateur) as TextView
        val vstatus = rowView.findViewById(R.id.status) as TextView
        val projet = rowView.findViewById(R.id.projet) as TextView
        //val avance = rowView.findViewById(R.id.avancement) as TextView
        val ddebut = rowView.findViewById(R.id.datedb) as TextView
        val dfin = rowView.findViewById(R.id.datefn) as TextView
        val imageView = rowView.findViewById(R.id.icon) as CircleImageView
        vtitle.text = title[position].TACHE
        vdescirption.text = "Description : " +title[position].DESCRIPTION1
        vcollaborateur.text = "Responsable : "+title[position].COLLABORATEUR
        vstatus.text = "Status : "+ title[position].STATUS
        projet.text="Projet : " +title[position].PROJET
        //avance.text="Avancement : " +title[position].AVANCEMENT.toString()
        ddebut.text="Du : " +title[position].DATEDEB
        dfin.text="AU : " +title[position].DATEFIN
        //return rowView
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
                imageView.setImageResource(R.drawable.cn)
            }
            0 -> {
                imageView.setImageResource(R.drawable.d)
            }
            //imageView.setImageResource(title[position])
        }
        return rowView
    }
}

