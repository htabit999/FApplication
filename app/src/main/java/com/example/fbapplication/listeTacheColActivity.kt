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

class listeTacheColActivity : AppCompatActivity() {
    lateinit var listView: ListView
    private var db = Firebase.firestore
    override fun onCreate(savedInstanceState: Bundle?)
    {
        android.util.Log.d(ContentValues.TAG, "onCreate")
        super.onCreate(savedInstanceState)
        //val TAG = javaClass.simpleName
        setContentView(R.layout.activity_liste_tache_col)
        listView = findViewById(R.id.listeView)
        var list = ArrayList<Tache>()
        val user = FirebaseAuth.getInstance().currentUser!!.uid
        var nom = intent.getStringExtra("nom").toString()
        var role = intent.getStringExtra("role").toString()
        db.collection("Tache").whereEqualTo("Collaborateur", nom)
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
                val myListAdapter = listeAdapterSuiviTache(this@listeTacheColActivity, list)
                listView.adapter = myListAdapter
                listView.setOnItemClickListener()
                {
                        adapterView, view, position, id ->
                    val role = intent.getStringExtra("role")
                    val nom = intent.getStringExtra("nom")
                    val user = intent.getStringExtra("user")
                    var intent : Intent = Intent(applicationContext, majTacheColActivity::class.java)
                    //var tache=adapterView.getItemAtPosition(position).toString()
                    intent.putExtra("tache", list[position].TACHE)
                    intent.putExtra("projet", list[position].PROJET)
                    intent.putExtra("description1", list[position].DESCRIPTION1)
                    intent.putExtra("dated", list[position].DATEDEB)
                    intent.putExtra("datef", list[position].DATEFIN)
                    intent.putExtra("status", list[position].STATUS)
                    intent.putExtra("avancement", list[position].AVANCEMENT.toString())
                    intent.putExtra("id", list[position].USERID)
                    intent.putExtra("col", list[position].COLLABORATEUR)
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
    public fun retourMenu(view: View)
    {
        var intent1 :Intent= getIntent()
        var nom = intent1.getStringExtra("nom").toString()
        var role = intent.getStringExtra("role").toString()
        val intent: Intent =  Intent(applicationContext, MenuColActivity::class.java)
        intent.putExtra("nom", nom)
        intent.putExtra("role", role)
        startActivity(intent)
        //startActivity(Intent(this, menuSuiviActivity::class.java))
    }
}