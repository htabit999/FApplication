package com.example.fbapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Log.v
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.core.view.get
import com.example.fbapplication.models.Projet
import com.google.firebase.database.*
import kotlinx.coroutines.NonCancellable.children

class DataActivity : AppCompatActivity() {
    lateinit var ref : DatabaseReference
    lateinit var dataList : MutableList<Project>
    lateinit var listView: ListView
    var listep = arrayOf<String>()
    var listed1 = arrayOf<String>()
    var listed2 = arrayOf<String>()
    var listechef = arrayOf<String>()
    var listedd = arrayOf<String>()
    var listedf = arrayOf<String>()
    var listest = arrayOf<String>()
    var listei = arrayOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        val TAG = javaClass.simpleName
        setContentView(R.layout.activity_data)
        dataList = mutableListOf()
        listView = findViewById(R.id.listeView )
        ref= FirebaseDatabase.getInstance().getReference( "projet-4f405")
        ref.addValueEventListener(object : ValueEventListener

        {
            override fun onDataChange(snapshot: DataSnapshot) {
            if (snapshot!!.exists())
            {
                dataList.clear()
                for ( e in snapshot.children)
                {
                    e.child("Projet").children.forEach() {
                        it.children.forEach {
                            it.children.forEach {
                                if (it.key == "projet") {
                                    listep = addElement(listep, it.value as String)
                                    listei = addElement(listei, R.drawable.photo)
                                    //Log.v("Projet",it.value as String);
                                }
                                if (it.key == "description1") {
                                    listed1 = addElement(listed1, it.value as String)
                                    Log.v("Description",it.value as String);
                                }
                                if (it.key == "description2") {
                                    listed2 = addElement(listed2, it.value as String)
                                    Log.v("Description",it.value as String);
                                }
                                if (it.key == "chef") {
                                     listechef= addElement(listechef, it.value as String)
                                   // Log.v("Description",it.value as String);
                                }
                                if (it.key == "datedeb") {
                                    listedd = addElement(listedd, it.value as String)
                                    //Log.v("Description",it.value as String);
                                }
                                if (it.key == "datefin") {
                                    listedf = addElement(listedf, it.value as String)
                                    //Log.v("Description",it.value as String);
                                }
                                if (it.key == "status") {
                                    listest= addElement(listest, it.value as String)
                                    //Log.v("Description",it.value as String);
                                }
                            }
                        }
                    }
                }
                val listView = findViewById<ListView>(R.id.listeView) as ListView
                val myListAdapter = listeAdapter(this@DataActivity, listep ,listed1, listei)
                listView.adapter = myListAdapter
                listView.setOnItemClickListener()
                {
                        adapterView, view, position, id ->
                        val itemAtPos = adapterView.getItemAtPosition(position)
                        val itemIdAtPos = adapterView.getItemIdAtPosition(position)
                        val itemdesc1 = listed1[position]
                        val itemdesc2 = listed2[position]
                        val itemdd = listedd[position]
                        val itemdf = listedf[position]
                        val itemst = listest[position]
                        val itemch = listechef[position]
                        var intent : Intent = Intent(applicationContext,majProjetActivity::class.java)
                        var projet=adapterView.getItemAtPosition(position).toString()
                        intent.putExtra("projet",projet)
                        intent.putExtra("description1",itemdesc1)
                        intent.putExtra("description2",itemdesc2)
                        intent.putExtra("dated",itemdd)
                        intent.putExtra("datef",itemdf)
                        intent.putExtra("chef",itemch)
                        intent.putExtra("status",itemst)
                        startActivity(intent)
                        //Toast.makeText(this@DataActivity, "Click on item at ", Toast.LENGTH_LONG).show()
                }
            }

            fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        }
        )
    }
}