package com.example.fbapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Log.v
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import com.example.fbapplication.models.Projet
import com.google.firebase.database.*
import kotlinx.coroutines.NonCancellable.children

class DataActivity : AppCompatActivity() {
    lateinit var ref : DatabaseReference
    lateinit var dataList : MutableList<Project>
    lateinit var listView: ListView
    var listep = arrayOf<String>()
    var listed = arrayOf<String>()
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
                                    Log.v("Projet",it.value as String);
                                }
                                if (it.key == "description1") {
                                    listed = addElement(listed, it.value as String)
                                    Log.v("Description",it.value as String);
                                }
                            }
                        }
                    }
                }
                val listView = findViewById<ListView>(R.id.listeView) as ListView
                val myListAdapter = listeAdapter(this@DataActivity, listep ,listed, listei)
                listView.adapter = myListAdapter
                listView.setOnItemClickListener()
                {
                        adapterView, view, position, id ->
                        val itemAtPos = adapterView.getItemAtPosition(position)
                        val itemIdAtPos = adapterView.getItemIdAtPosition(position)
                        Toast.makeText(this@DataActivity, "Click on item at ", Toast.LENGTH_LONG).show()
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