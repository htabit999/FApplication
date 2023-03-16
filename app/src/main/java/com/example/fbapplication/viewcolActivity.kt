package com.example.fbapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.example.fbapplication.models.Collaborateur
import com.google.firebase.database.*

class viewcolActivity : AppCompatActivity() {

    lateinit var ref: DatabaseReference
    lateinit var dataList: MutableList<Collaborateur>
    lateinit var listView: ListView
    private lateinit var collabList: ArrayList<Collaborateur>
    var lnom = arrayOf<String>()
    var lprenom = arrayOf<String>()
    var listeid = arrayOf<String>()
    var listei = arrayOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val TAG = javaClass.simpleName
        setContentView(R.layout.activity_viewcol)
        dataList = mutableListOf()
        listView = findViewById(R.id.listeCView )
        ref = FirebaseDatabase.getInstance().getReference("projet-4f405")
        ref.addValueEventListener(object : ValueEventListener {
            val collabList = arrayListOf<Collaborateur>()
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot!!.exists()) {
                    dataList.clear()
                    for (e in snapshot.children) {
                        e.child("Collaborateur").children.forEach() {
                            it.children.forEach {
                                if (it.key == "idc") {
                                    listei = addElement(listei, R.drawable.photo)
                                    listeid = addElement(listeid, it.value as String)
                                }
                                if (it.key == "nom") {
                                    lnom = addElement(lnom, it.value as String)
                                }
                                if (it.key == "prenom") {
                                    lprenom = addElement(lprenom, it.value as String)
                                }
                            }
                        }
                    }
                    val listView = findViewById<ListView>(R.id.listeCView) as ListView
                    val myListAdapter =  listeAdapter(this@viewcolActivity, lnom, lprenom, listei)
                    listView.adapter = myListAdapter
                    listView.setOnItemClickListener()
                    { adapterView, view, position, id ->
                        val itemnom = lnom[position]
                        val itempre = lprenom[position]
                        val itemid = listeid[position]

                        var intent: Intent =
                            Intent(applicationContext, ProjetActivity::class.java)
                        var collab = adapterView.getItemAtPosition(position).toString()
                        intent.putExtra("nom", itemnom)
                        intent.putExtra("prenom", itempre)
                        intent.putExtra("idc", itemid)
                        startActivity(intent)
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
