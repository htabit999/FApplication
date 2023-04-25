package com.example.fbapplication

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ListView
import android.widget.Toast
import com.example.fbapplication.models.Project
import com.example.fbapplication.models.Projet
import com.example.fbapplication.models.Tache
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class SuiviActivity : AppCompatActivity() {
    lateinit var listView: ListView
    private var db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?)
    {
        android.util.Log.d(ContentValues.TAG, "onCreate")
        super.onCreate(savedInstanceState)
        val TAG = javaClass.simpleName
        setContentView(R.layout.activity_suivi)
        listView = findViewById(R.id.listeView)
        var list = ArrayList<Project>()
        val role = intent.getStringExtra("role")
        val nom = intent.getStringExtra("nom")
        val user = intent.getStringExtra("user")
        db.collection("Projet").whereEqualTo("Chef", nom)
            .get()
            .addOnCompleteListener {
                if (it.isSuccessful)
                {
                    for (document in it.result) {
                        list.add(
                            Project(
                                document.data.getValue("Projet") as String,
                                document.data.getValue("Description1") as String,
                                document.data.getValue("datedeb") as String,
                                document.data.getValue("datefin") as String,
                                document.data.getValue("Status") as String,
                                document.data.getValue("USERID") as String,
                                document.data.getValue("Avancement").toString().toInt(),
                                document.data.getValue("Chef") as String
                            )
                        )
                    }
                }
            val listView = findViewById<ListView>(R.id.listeView) as ListView
            val myListAdapter = listeAdapterSuivi(this@SuiviActivity, list)
            listView.adapter = myListAdapter
            listView.setOnItemClickListener()
            {
                adapterView, view, position, id ->
                val role = intent.getStringExtra("role")
                val nom = intent.getStringExtra("nom")
                val user = intent.getStringExtra("user")
                var intent : Intent = Intent(applicationContext, SuiviTacheActivity::class.java)
                intent.putExtra("projet", list[position].PROJET)
                intent.putExtra("description1", list[position].DESCRIPTION1)
                intent.putExtra("dated", list[position].DATEDEB)
                intent.putExtra("datef", list[position].DATEFIN)
                intent.putExtra("status", list[position].STATUS)
                intent.putExtra("avancement", list[position].AVANCEMENT.toString())
                intent.putExtra("id", list[position].USERID)
                intent.putExtra("role", role)
                intent.putExtra("nom", nom)
                intent.putExtra("user", user)
                startActivity(intent)
            }
        }
        fun onCancelled(error: DatabaseError) {
        TODO("Not yet implemented")
    }
}
    public fun retourMenu(view: View) {
        val role = intent.getStringExtra("role")
        val nom = intent.getStringExtra("nom")
        val user = intent.getStringExtra("user")
        val intent: Intent =  Intent(applicationContext, menuSuiviActivity::class.java)
        intent.putExtra("role", role)
        intent.putExtra("nom", nom)
        intent.putExtra("user", user)
        startActivity(intent)
    }
}
