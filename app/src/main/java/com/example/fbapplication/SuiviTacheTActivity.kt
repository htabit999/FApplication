package com.example.fbapplication

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ListView
//import com.example.fbapplication.models.Project
//import com.example.fbapplication.models.Projet
import com.example.fbapplication.models.Tache
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class SuiviTacheTActivity : AppCompatActivity() {
    lateinit var listView: ListView
    private var db = Firebase.firestore
    override fun onCreate(savedInstanceState: Bundle?)
    {
        android.util.Log.d(ContentValues.TAG, "onCreate")
        super.onCreate(savedInstanceState)
        //val TAG = javaClass.simpleName
        setContentView(R.layout.activity_suivi_tache_tactivity)
        listView = findViewById(R.id.listeView)
        var list = ArrayList<Tache>()
        val user = FirebaseAuth.getInstance().currentUser!!.uid
        db.collection("Tache").whereEqualTo("USERID", user)
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
                                document.data.getValue("USERID") as String,
                                document.data.getValue("Collaborateur") as String
                            )
                        )
                    }
                }
                val listView = findViewById<ListView>(R.id.listeView) as ListView
                val myListAdapter = listeAdapterSuiviTache(this@SuiviTacheTActivity, list)
                listView.adapter = myListAdapter
            }
        fun onCancelled(error: DatabaseError) {
            TODO("Not yet implemented")
        }
    }
    public fun retourMenu(view: View)
    {
        var intent1 :Intent= getIntent()
        var nom = intent1.getStringExtra("nom").toString()
        val intent: Intent =  Intent(applicationContext, menuSuiviActivity::class.java)
        intent.putExtra("nom", nom)
        startActivity(intent)
        //startActivity(Intent(this, menuSuiviActivity::class.java))
    }
}