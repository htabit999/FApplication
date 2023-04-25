package com.example.fbapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ListView
import android.widget.TextView
import com.example.fbapplication.models.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ListeChefProjetActivity : AppCompatActivity() {
    lateinit var ref : DatabaseReference
    lateinit var listView: ListView
    private var db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        val TAG = javaClass.simpleName
        setContentView(R.layout.activity_liste_chef_projet)
        listView = findViewById(R.id.listeTView)
        var list = ArrayList<Usr>()
        val user = FirebaseAuth.getInstance().currentUser!!.uid
        db.collection("Users").get()
            .addOnCompleteListener {
                if (it.isSuccessful)
                {
                    for (document in it.result) {
                        if (document.data.getValue("Role") =="Chef de projet")
                        {
                            list.add(
                                Usr(
                                    document.data.getValue("Nom") as String,
                                    document.data.getValue("Prenom") as String,
                                    document.data.getValue("Email") as String,
                                    document.data.getValue("UID") as String,
                                    document.data.getValue("Role") as String,
                                    document.data.getValue("Url") as String
                                )
                            )
                        }
                    }
                }
                val listView = findViewById<ListView>(R.id.listeTView) as ListView
                val myListAdapter = listeAdapterCollab(this@ListeChefProjetActivity, list)
                listView.adapter = myListAdapter
                listView.setOnItemClickListener()
                {
                        adapterView, view, position, id ->
                    val role = intent.getStringExtra("role")
                    val nom = intent.getStringExtra("nom")
                    val user = intent.getStringExtra("user")
                    var intent : Intent = Intent(applicationContext, MajChefProjetActivity::class.java)
                    var tache=adapterView.getItemAtPosition(position).toString()
                    intent.putExtra("name", list[position].NOM)
                    intent.putExtra("prenom", list[position].PRENOM)
                    intent.putExtra("email", list[position].EMAIL)
                    intent.putExtra("uid", list[position].UID)
                    intent.putExtra("url", list[position].URL)
                    intent.putExtra("rl", list[position].ROLE)
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
        val role = intent.getStringExtra("role")
        val nom = intent.getStringExtra("nom")
        val user = intent.getStringExtra("user")
        val intent: Intent =  Intent(applicationContext, MenuChefProjetActivity::class.java)
        intent.putExtra("role", role)
        intent.putExtra("nom", nom)
        intent.putExtra("user", user)
        startActivity(intent)
        //startActivity(Intent(this, MenuChefProjetActivity::class.java))
    }
}