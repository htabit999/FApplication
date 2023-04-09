package com.example.fbapplication

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ListView
import com.example.fbapplication.models.Project
import com.example.fbapplication.models.Projet
import com.example.fbapplication.models.Tache
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class SuiviTacheAdmActivity : AppCompatActivity() {
    lateinit var listView: ListView
    private var db = Firebase.firestore
    override fun onCreate(savedInstanceState: Bundle?)
    {
        android.util.Log.d(ContentValues.TAG, "onCreate")
        super.onCreate(savedInstanceState)
        val TAG = javaClass.simpleName
        setContentView(R.layout.activity_suivi_tache_adm)
        listView = findViewById(R.id.listeView)
        var list = ArrayList<Tache>()
        val user = FirebaseAuth.getInstance().currentUser!!.uid
        val intent = intent
        val projet = intent.getStringExtra("projet")
          //  .whereEqualTo("Projet", projet)
        db.collection("Tache")
            .get()
            .addOnCompleteListener {
                if (it.isSuccessful)
                {
                    for (document in it.result) {
                        list.add(
                            Tache(
                                document.data.getValue("Tache") as String,
                                document.data.getValue("Description1") as String,
                                document.data.getValue("datedeb") as String,
                                document.data.getValue("datefin") as String,
                                document.data.getValue("Projet") as String,
                                document.data.getValue("Avancement").toString().toInt(),
                                document.data.getValue("Status") as String,
                                document.data.getValue("USERID") as String ,
                                document.data.getValue("Collaborateur") as String
                            )
                        )
                    }
                }
                val listView = findViewById<ListView>(R.id.listeView) as ListView
                val myListAdapter = listeAdapterSuiviTache(this@SuiviTacheAdmActivity, list)
                listView.adapter = myListAdapter
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
        val intent: Intent =  Intent(applicationContext, menuSuiviAdmActivity::class.java)
        intent.putExtra("role", role)
        intent.putExtra("nom", nom)
        intent.putExtra("user", user)
        startActivity(intent)
    }
}