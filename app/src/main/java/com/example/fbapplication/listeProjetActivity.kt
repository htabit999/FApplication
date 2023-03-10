package com.example.fbapplication

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.security.AccessController.getContext

class listeProjetActivity : AppCompatActivity()
{
    var db = FirebaseDatabase.getInstance().getReference("projet-4f405")
    var tblName = arrayOf<String>("")
    var tblEml = arrayOf<String>("")
    var imageId = arrayOf<Int>(R.drawable.photo)
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        val TAG = javaClass.simpleName
        android.util.Log.d(TAG, "onCreate")
        setContentView(R.layout.activity_liste_projet)
        //var db = FirebaseDatabase.getInstance().getReference("projet-4f405")
        var listeEml=readDataFB()
                val listView = findViewById<ListView>(R.id.listView) as ListView
                val myListAdapter = listeAdapter(this, tblName, tblEml, imageId)
                listView.adapter == myListAdapter
                listView.setOnItemClickListener()
                {
                        adapterView, view, position, id ->
                        val itemAtPos = adapterView.getItemAtPosition(position)
                        val itemIdAtPos = adapterView.getItemIdAtPosition(position)
                        //Toast.makeText(this@listeProjetActivity, "Click on item at $itemAtPos its item id $itemIdAtPos", Toast.LENGTH_LONG).show()
                }
            }
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

    public fun readDataFB() : Array<String>
    {
        val db = FirebaseDatabase.getInstance().getReference("projet-4f405")
        val menuListener = object : ValueEventListener
        {
            var tblEml = arrayOf<String>()
            var tblName = arrayOf<String>()
            var imageId = arrayOf<Int>()
            override fun onDataChange(snapshot: DataSnapshot)
            {
                for (i in snapshot.children) {
                    i.child("Projet").children.forEach {
                        it.children.forEach {
                            it.children.forEach {
                                if (it.key == "projet") {
                                    tblEml = addElement(tblEml, it.value as String)
                                    imageId = addElement(imageId, R.drawable.ic_launcher_background)
                                    tblName = addElement(tblName, it.value as String)
                                }
                            }
                        }
                    }
                }
            }
            override fun onCancelled(databaseError: DatabaseError)
            {
            }
        }
        var tbl = arrayOf<String>("")
        return tbl
    }

