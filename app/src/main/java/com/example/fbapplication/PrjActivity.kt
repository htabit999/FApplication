package com.example.fbapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.fbapplication.models.Projet
import com.google.firebase.database.*

class PrjActivity : AppCompatActivity() {

    lateinit private var db : DatabaseReference
    private lateinit var projList: ArrayList<Projet>
    var listep = arrayOf<String>()
    var listed = arrayOf<String>()
    var listei = arrayOf<Int>()
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        val TAG = javaClass.simpleName
        projList=arrayListOf<Projet>()
        setContentView(R.layout.activity_data)
        db= FirebaseDatabase.getInstance().getReference("projet-4f405")
        val addValueEventListener = db.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot!!.exists()) {
                    projList.clear()
                    for (e in snapshot.children) {
                        Log.v("oooooo","DDDDDDDDDDDDDDDDD")
                        e.child("Projet").children.forEach() {
                            it.children.forEach {
                                //it.children.forEach {
                                    Log.v("oooooo","PPPPPPPPPPPPP")
                                    val projData = e.getValue(Projet::class.java)
                                    projList.add(projData!!)
                                    Log.v("oooooo",projList.toString())
                                    Toast.makeText(this@PrjActivity, projList.toString(), Toast.LENGTH_LONG).show()
                                    listep = addElement(listep, projData.PROJET.toString())
                                    listed = addElement(listep, projData.DESCRIPTION1.toString())
                                    listei = addElement(listei, R.drawable.photo)
                               // }
                            }
                        }
                    }
                    //
                    val listView = findViewById<ListView>(R.id.listeView) as ListView
                    val myListAdapter = listeAdapter(this@PrjActivity, listep ,listed, listei)
                    listView.adapter = myListAdapter
                    listView.setOnItemClickListener()
                    {
                            adapterView, view, position, id ->
                        var intent : Intent = Intent(applicationContext, majProjetActivity::class.java)
                        var projet=adapterView.getItemAtPosition(position).toString()
                        //Toast.makeText(this@DataActivity, "Click on item at ", Toast.LENGTH_LONG).show()
                    }
                }
            }
            override fun onCancelled(error: DatabaseError){
            }
        })
    }
}