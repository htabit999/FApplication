package com.example.fbapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class listeProjetActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_liste_projet)
        val db = FirebaseDatabase.getInstance().getReference("projet-4f405")
        val menuListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var tblEml = arrayOf("")
                var tblName = arrayOf("")
                var imageId = arrayOf<Int>(R.drawable.ic_launcher_background)
                for (i in snapshot.children) {
                    var name = i.child("Projet").getValue().toString()
                    tblEml = addElement(tblEml, name)
                }
                val listView = findViewById<ListView>(R.id.listsView) as ListView
                val myListAdapter = listeAdapter(this , tblName, tblEml, imageId)
                listView.adapter = myListAdapter
                listView.setOnItemClickListener() { adapterView, view, position, id -> val itemAtPos = adapterView.getItemAtPosition(position)
                    //Toast.makeText(this, "Click on item at $itemAtPos its item id $itemIdAtPos", Toast.LENGTH_LONG).show()
                }
            }
            override fun onCancelled(databaseError: DatabaseError) {
            }
        }
    }
    public fun addElement(tblName: Array<String>, chaine: String): Array<String>
    {
        val tblList = tblName.toMutableList()
        tblList.add(chaine)
        return tblList.toTypedArray()
    }
}
