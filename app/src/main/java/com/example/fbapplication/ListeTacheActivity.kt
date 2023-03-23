package com.example.fbapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.example.fbapplication.models.Projet
import com.example.fbapplication.models.Tache
import com.google.firebase.database.*

class listeTacheActivity : AppCompatActivity() {
    lateinit var ref : DatabaseReference
    lateinit var dataList : MutableList<Projet>
    lateinit var listView: ListView
    private lateinit var tacheList: ArrayList<Tache>
    var listet = arrayOf<String>()
    var listed1 = arrayOf<String>()
    var listed2 = arrayOf<String>()
    var listecol = arrayOf<String>()
    var listedd = arrayOf<String>()
    var listedf = arrayOf<String>()
    var listest = arrayOf<String>()
    var listeid = arrayOf<String>()
    var listei = arrayOf<Int>()
    var listepr = arrayOf<String>()
    var listeav = arrayOf<String>()


    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        val TAG = javaClass.simpleName
        setContentView(R.layout.activity_liste_tache)
        dataList = mutableListOf()
        listView = findViewById(R.id.listeTView)
        ref= FirebaseDatabase.getInstance().getReference( "projet-4f405")
        ref.addValueEventListener(object : ValueEventListener
        {
            val tacheList = arrayListOf<Tache>()
            override fun onDataChange(snapshot: DataSnapshot)
            {
                if (snapshot!!.exists())
                {
                    dataList.clear()
                    for ( e in snapshot.children)
                    {
                        e.child("Tache").children.forEach() {
                            //Toast.makeText(this@listeTacheActivity, it.key, Toast.LENGTH_LONG).show()
                            it.children.forEach {
                                if (it.key == "idt") {
                                    listei = addElement(listei, R.drawable.tache)
                                    listeid= addElement(listeid, it.value as String)
                                }
                                if (it.key == "tache") {
                                    listet = addElement(listet, it.value as String)
                                }
                                if (it.key == "description1") {
                                    listed1 = addElement(listed1, it.value as String)
                                }
                                if (it.key == "description2") {
                                    listed2 = addElement(listed2, it.value as String)
                                }
                                if (it.key == "collaborateur") {
                                    listecol= addElement(listecol, it.value as String)
                                }
                                if (it.key == "datedeb") {
                                    listedd = addElement(listedd, it.value as String)
                                }
                                if (it.key == "datefin") {
                                    listedf = addElement(listedf, it.value as String)
                                }
                                if (it.key == "status") {
                                    listest= addElement(listest, it.value as String)
                                }
                                if (it.key == "projet") {
                                    listepr = addElement(listepr, it.value as String)
                                }
                                if (it.key == "avancement") {
                                    listeav= addElement(listeav, it.getValue().toString())
                                }
                                //}
                            }
                        }
                    }
                    val listView = findViewById<ListView>(R.id.listeTView) as ListView
                    val myListAdapter = listeAdapterTache(this@listeTacheActivity, listet ,listed1, listecol,listest)
                    listView.adapter = myListAdapter
                    listView.setOnItemClickListener()
                    {
                            adapterView, view, position, id ->
                        val itemdesc1 = listed1[position]
                        val itemdesc2 = listed2[position]
                        val itemdd = listedd[position]
                        val itemdf = listedf[position]
                        val itemst = listest[position]
                        val itemcol = listecol[position]
                        val itemid = listeid[position]
                        val itempr = listepr[position]
                        val itemav = listeav[position]

                        var intent : Intent = Intent(applicationContext, majTacheActivity::class.java)
                        var tache=adapterView.getItemAtPosition(position).toString()
                        intent.putExtra("tache",tache)
                        intent.putExtra("description1",itemdesc1)
                        intent.putExtra("description2",itemdesc2)
                        intent.putExtra("dated",itemdd)
                        intent.putExtra("datef",itemdf)
                        intent.putExtra("col",itemcol)
                        intent.putExtra("status",itemst)
                        intent.putExtra("idt",itemid)
                        intent.putExtra("projet",itempr)
                        intent.putExtra("avancement",itemav)
                        startActivity(intent)
                    }
                }
                val listet = arrayOf<String>()
                val listed1 = arrayOf<String>()
                val listecol = arrayOf<Int>()
                val listest = arrayOf<Int>()

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