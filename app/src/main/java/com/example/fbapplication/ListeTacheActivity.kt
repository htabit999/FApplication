package com.example.fbapplication

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

class listeTacheActivity : AppCompatActivity() {
    lateinit var ref : DatabaseReference
    //lateinit var dataList : MutableList<Projet>
    lateinit var listView: ListView
    //private lateinit var tacheList: ArrayList<Tache>
    private var db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        val TAG = javaClass.simpleName
        setContentView(R.layout.activity_liste_tache)
        //dataList = mutableListOf()
        listView = findViewById(R.id.listeTView)
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
                                document.data.getValue("USERID") as String
                            )
                        )
                    }
                }
                    val listView = findViewById<ListView>(R.id.listeTView) as ListView
                    val myListAdapter = listeAdapterTache(this@listeTacheActivity, list)
                    listView.adapter = myListAdapter
                    listView.setOnItemClickListener()
                    {
                            adapterView, view, position, id ->
                            var intent : Intent = Intent(applicationContext, majTacheActivity::class.java)
                            var tache=adapterView.getItemAtPosition(position).toString()
                            intent.putExtra("tache", list[position].TACHE)
                            intent.putExtra("projet", list[position].PROJET)
                            intent.putExtra("description1", list[position].DESCRIPTION1)
                            intent.putExtra("dated", list[position].DATEDEB)
                            intent.putExtra("datef", list[position].DATEFIN)
                            intent.putExtra("status", list[position].STATUS)
                            intent.putExtra("avancement", list[position].AVANCEMENT.toString())
                            intent.putExtra("id", list[position].USERID)
                            startActivity(intent)
                    }
                }
                fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }
    }
    public fun retourMenu(view: View)
    {
        startActivity(Intent(this, MenuTacheActivity::class.java))
    }
}