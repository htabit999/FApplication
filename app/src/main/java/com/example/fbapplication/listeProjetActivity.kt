package com.example.fbapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.example.fbapplication.models.Project
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class listeProjetActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val TAG = javaClass.simpleName
        android.util.Log.d(TAG, "onCreate")
        setContentView(R.layout.activity_liste_projet)
        val db = FirebaseDatabase.getInstance().getReference("projet-4f405")
        val menuListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var tblEml = arrayOf<String>()
                var tblName = arrayOf<String>()
                var imageId = arrayOf<Int>()
                for (i in snapshot.children) {
                    i.child("Projet").children.forEach {
                        it.children.forEach {
                            it.children.forEach {
                                if (it.key == "projet") {
                                    tblEml = addElement(tblEml, it.value as String)
                                }
                                if (it.key == "description1") {
                                    imageId = addElement(imageId, R.drawable.ic_launcher_background)
                                    tblName = addElement(tblName, it.value as String)
                                }
                            }
//                            android.util.Log.d(
//                                TAG,
//                                "onDataChange: ${project.toString()}"
//                            )
                        }
                    }
//                    android.util.Log.d(TAG, "onDataChange: ${name.toString()}")
//                    var projects = name.
//                    var firstProject = projects.first()
//                    android.util.Log.d(TAG, "onDataChange: $firstProject")
//                    tblEml = addElement(tblEml, name)

                }
                val listView = findViewById<ListView>(R.id.listsView) as ListView
                val myListAdapter = listeAdapter(this@listeProjetActivity, tblName, tblEml, imageId)
                listView.adapter = myListAdapter
                listView.setOnItemClickListener() { adapterView, view, position, id ->
                    val itemAtPos = adapterView.getItemAtPosition(position)
//                    Toast.makeText(this@listeProjetActivity, "Click on item at $itemAtPos its item id $itemIdAtPos", Toast.LENGTH_LONG).show()
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
            }
        }
        db.addValueEventListener(menuListener)
    }

    public fun addElement(tblName: Array<String>, chaine: String): Array<String> {
        val tblList = tblName.toMutableList()
        tblList.add(chaine)
        return tblList.toTypedArray()
    }

    public fun addElement(tblName: Array<Int>, chaine: Int): Array<Int> {
        val tblList = tblName.toMutableList()
        tblList.add(chaine)
        return tblList.toTypedArray()
    }
}
