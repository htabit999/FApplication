package com.example.fbapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ListView
import android.widget.TextView
import com.example.fbapplication.models.Project
import com.example.fbapplication.models.Projet
import com.example.fbapplication.models.Tache
import com.example.fbapplication.models.Users
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class lCollabActivity : AppCompatActivity() {
    lateinit var ref : DatabaseReference
    lateinit var listView: ListView
    private var db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        val TAG = javaClass.simpleName
        setContentView(R.layout.activity_lcollab)
        listView = findViewById(R.id.listeTView)
        var list = ArrayList<Users>()
        val user = FirebaseAuth.getInstance().currentUser!!.uid
        db.collection("Users").get()
            .addOnCompleteListener {
                if (it.isSuccessful)
                {
                    for (document in it.result) {
                        if (document.data.getValue("Role") =="Collaborateur")
                        {
                            list.add(
                            Users(
                                document.data.getValue("Nom") as String,
                                document.data.getValue("Prenom") as String,
                                document.data.getValue("Email") as String,
                                document.data.getValue("UID") as String,
                                document.data.getValue("Role") as String
                            )
                            )
                        }
                    }
                }
                val listView = findViewById<ListView>(R.id.listeTView) as ListView
                val myListAdapter = listeAdapterCollab(this@lCollabActivity, list)
                listView.adapter = myListAdapter
                listView.setOnItemClickListener()
                {
                        adapterView, view, position, id ->
                    var intent1 :Intent= getIntent()
                    val role = intent.getStringExtra("role")
                    val nom = intent.getStringExtra("nom")
                    val user = intent.getStringExtra("user")

                    var intent : Intent = Intent(applicationContext, majCollabActivity::class.java)
                    var tache=adapterView.getItemAtPosition(position).toString()
                    intent.putExtra("nomc", list[position].NOM)
                    intent.putExtra("prenom", list[position].PRENOM)
                    intent.putExtra("email", list[position].EMAIL)
                    intent.putExtra("uid", list[position].UID)
                    intent.putExtra("role", list[position].ROLE)
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
        var user = intent1.getStringExtra("user").toString()
        var nom = intent1.getStringExtra("nom").toString()
        var role = intent1.getStringExtra("role").toString()
        val intent: Intent =  Intent(applicationContext, MenuCollabActivity::class.java)
        intent.putExtra("user", user)
        intent.putExtra("role", role)
        intent.putExtra("nom", nom)
        startActivity(intent)
        //startActivity(Intent(this, MenuCollabActivity::class.java))
    }
}