package com.example.fbapplication

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ListView
import com.example.fbapplication.models.Project
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlin.collections.ArrayList

class DataFSActivity : AppCompatActivity() {

    lateinit var listView: ListView
    private var db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        android.util.Log.d(TAG, "onCreate")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_fsactivity)
        val user = FirebaseAuth.getInstance().currentUser!!.uid
        listView = findViewById(R.id.listeView)
        var list = ArrayList<Project>()
        db.collection("Projet").whereEqualTo("USERID", user)
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
                                document.data.getValue("Avancement").toString().toInt()
                            )
                        )
                    }
                }
                val listView = findViewById<ListView>(R.id.listeView) as ListView
                val myListAdapter = listeFSAdapter(this@DataFSActivity, list)
                listView.adapter = myListAdapter
                listView.setOnItemClickListener()
                { adapterView, view, position, id ->
                    var intent: Intent = Intent(applicationContext, majProjetActivity::class.java)
                    intent.putExtra("projet", list[position].PROJET)
                    intent.putExtra("description1", list[position].DESCRIPTION1)
                    intent.putExtra("dated", list[position].DATEDEB)
                    intent.putExtra("datef", list[position].DATEFIN)
                    intent.putExtra("status", list[position].STATUS)
                    intent.putExtra("av", list[position].AVANCEMENT.toString())
                    intent.putExtra("id", list[position].USERID)
                    startActivity(intent)
                }
            }
    }
    public fun retourMenu(view: View)
    {
        startActivity(Intent(this, MenuProjetActivity::class.java))
    }
}