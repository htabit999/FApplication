package com.example.fbapplication

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ListView
import android.widget.Toast
import com.example.fbapplication.models.Project
import com.example.fbapplication.models.ProjectView
import com.example.fbapplication.models.Projet
import com.example.fbapplication.models.Tache
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class SuiviTacheActivity : AppCompatActivity() {
    lateinit var listView: ListView
    private var db = Firebase.firestore
    override fun onCreate(savedInstanceState: Bundle?)
    {
        android.util.Log.d(ContentValues.TAG, "onCreate")
        super.onCreate(savedInstanceState)
        val TAG = javaClass.simpleName
        setContentView(R.layout.activity_suivi_tache)
        listView = findViewById(R.id.listeView)
        var list = ArrayList<Tache>()
        val intent = intent
        val role = intent.getStringExtra("role")
        val nom = intent.getStringExtra("nom")
        val user = intent.getStringExtra("user")
        val collection1 = db.collection("Tache")
        val collection2 = db.collection("Projet")
        val query = collection1
        //.whereArrayContains("Id", "Projet")
        db.collection("Tache")
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    val id = document.get("Projet").toString()
                    val data = document.data
                    collection2.document(id).get().addOnSuccessListener { secondDocument ->
                        val secondData = secondDocument.data
                        if (secondData != null) {
                            if (secondData.getValue("Chef").toString() == nom) {
                                list.add(
                                    Tache(
                                        document.data.getValue("Tache") as String,
                                        document.data.getValue("Description1") as String,
                                        document.data.getValue("datedeb") as String,
                                        document.data.getValue("datefin") as String,
                                        document.data.getValue("Projet") as String,
                                        document.data.getValue("Avancement").toString().toInt(),
                                        document.data.getValue("Status") as String,
                                        document.data.getValue("USERID") as String,
                                        document.data.getValue("Collaborateur") as String
                                    )
                                )
                            }
                        }
                        val listView = findViewById<ListView>(R.id.listeView) as ListView
                        val myListAdapter = listeAdapterSuiviTache(this@SuiviTacheActivity, list)
                        listView.adapter = myListAdapter
                        //
                        //db.collection("Tache").whereEqualTo("Projet", projet)
                        //.get()
                        // .addOnCompleteListener {
                        //if (it.isSuccessful)
                        //    {
                        //    for (document in it.result) {
                        //        list.add(
                        //            Tache(
                        //            document.data.getValue("Tache") as String,
                        //            document.data.getValue("Description1") as String,
                        //           document.data.getValue("datedeb") as String,
                        //           document.data.getValue("datefin") as String,
                        //           document.data.getValue("Projet") as String,
                        //           document.data.getValue("Avancement").toString().toInt(),
                        //           document.data.getValue("Status") as String,
                        //           document.data.getValue("USERID") as String ,
                        //           document.data.getValue("Collaborateur") as String
                        //           )
                        //       )
                        //   }
                        //}
                        //val listView = findViewById<ListView>(R.id.listeView) as ListView
                        //val myListAdapter = listeAdapterSuiviTache(this@SuiviTacheActivity, list)
                        //listView.adapter = myListAdapter
                    }
                }
            }
        fun onCancelled(error: DatabaseError) {
                        TODO("Not yet implemented")
        }
    }
    public fun retourMenu(view: View)
    {
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