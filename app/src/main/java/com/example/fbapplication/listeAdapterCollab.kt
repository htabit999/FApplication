package com.example.fbapplication

import android.app.Activity
import android.app.Application
import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.fbapplication.models.Users
import com.example.fbapplication.models.Usr
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView


class listeAdapterCollab(private var context: Activity, private val title: ArrayList<Usr>)
    : ArrayAdapter<Usr>(context, R.layout.collab_list, title) {
    override fun getView(position: Int, view: View?, parent: ViewGroup): View
    {
        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.collab_list, null, true)
        val nom = rowView.findViewById(R.id.nom) as TextView
        val prenom = rowView.findViewById(R.id.prenom) as TextView
        val email = rowView.findViewById(R.id.email) as TextView
        //val role = rowView.findViewById(R.id.role) as TextView
        val image = rowView.findViewById(R.id.icon) as CircleImageView
        nom.text = "Nom : " + title[position].NOM
        prenom.text = "Prenom : " + title[position].PRENOM
        email.text = "Email : " + title[position].EMAIL
        //role.text =""
            //title[position].ROLE
        Picasso.get().load(title[position].URL).into(image)
        //context.grantUriPermission(context.getPackageName(), Uri.parse(title[position].PRENOM), Intent.FLAG_GRANT_READ_URI_PERMISSION);
        //val bitmap: Bitmap = MediaStore.Images.Media.getBitmap(context.contentResolver, Uri.parse(title[position].PRENOM))
        //image.setImageBitmap(bitmap)
        //
        return rowView
    }
}