package com.example.fbapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ListView
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
                        list.add(
                            Users(
                                document.data.getValue("Nom") as String,
                                document.data.getValue("Prenom") as String,
                                document.data.getValue("Email") as String,
                                document.data.getValue("UID") as String,
                                document.data.getValue("role") as String
                            )
                        )
                    }
                }
                val listView = findViewById<ListView>(R.id.listeTView) as ListView
                val myListAdapter = listeAdapterCollab(this@lCollabActivity, list)
                listView.adapter = myListAdapter
                listView.setOnItemClickListener()
                {
                        adapterView, view, position, id ->
                    var intent : Intent = Intent(applicationContext, majCollabActivity::class.java)
                    var tache=adapterView.getItemAtPosition(position).toString()
                    intent.putExtra("nom", list[position].NOM)
                    intent.putExtra("prenom", list[position].PRENOM)
                    intent.putExtra("email", list[position].EMAIL)
                    intent.putExtra("uid", list[position].UID)
                    intent.putExtra("role", list[position].ROLE)
                    startActivity(intent)
                }
            }
        fun onCancelled(error: DatabaseError) {
            TODO("Not yet implemented")
        }
    }
    public fun retourMenu(view: View)
    {
        startActivity(Intent(this, MenuCollabActivity::class.java))
    }
}