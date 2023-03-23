package com.example.fbapplication

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.fbapplication.models.Projet
import com.example.fbapplication.models.Tache
import com.google.firebase.database.*

class detSuiviActivity : AppCompatActivity() {
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
    var listeav = arrayOf<String>()
    var listepr = arrayOf<String>()
    var listei = arrayOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?)
    {
        android.util.Log.d(ContentValues.TAG, "onCreate")
        super.onCreate(savedInstanceState)
        val intent = intent
        var nprj = intent.getStringExtra("projet")
        val TAG = javaClass.simpleName
        setContentView(R.layout.activity_det_suivi)
        dataList = mutableListOf()
        listView = findViewById(R.id.listeView)
        //Toast.makeText(this@detSuiviActivity, nprj , Toast.LENGTH_LONG).show()
        //ref= FirebaseDatabase.getInstance().getReference( "projet-4f405/Base/Tache")
        ref= FirebaseDatabase.getInstance().getReference( "projet-4f405")
            //.child("Tache")
            //. .getReference( "projet-4f405")
        //ref.orderByChild("projet").equalTo(nprj).addValueEventListener(object : ValueEventListener {
        ref.addValueEventListener(object : ValueEventListener {
            val tacheList = arrayListOf<Tache>()
            //val empId = dbRef.push().key!!
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot!!.exists()) {
                    dataList.clear()
                    for (e in snapshot.children) {
                        e.child("Tache").children.forEach() {
                             it.children.forEach {
                                if (it.key == "id") {
                                    listeid = addElement(listeid, it.value as String)
                                }
                                if (it.key == "collaborateur") {
                                    listecol = addElement(listecol, it.value as String)
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
                                if (it.key == "projet") {
                                    listepr = addElement(listepr, it.value as String)
                                }
                                if (it.key == "datedeb") {
                                    listedd = addElement(listedd, it.value as String)
                                }
                                if (it.key == "datefin") {
                                    listedf = addElement(listedf, it.value as String)
                                }
                                if (it.key == "status") {
                                    listest = addElement(listest, it.value as String)
                                }
                                if (it.key == "avancement") {
                                    listei = addElement(listei, R.drawable.d)
                                    listeav = addElement(listeav, it.getValue().toString().toInt())
                                }
                            }
                        }
                    }
                }
                var listetN = arrayOf<String>()
                var listesN = arrayOf<String>()
                var listecN = arrayOf<String>()
                var listeaN = arrayOf<Int>()
                var listeiN = arrayOf<Int>()
                var index = 0
                while (index < listet.size)
                {
                    if (listepr[index]==nprj)
                    {
                        listetN=addElement(listetN,listet[index])
                        listesN=addElement(listesN,listest[index])
                        listecN=addElement(listecN,listecol[index])
                        listeaN=addElement(listeaN,listeav[index])
                        when (listeav[index]) {
                            10-> listeiN = addElement(listeiN, R.drawable.d)
                            20-> listeiN = addElement(listeiN, R.drawable.v)
                            30-> listeiN = addElement(listeiN, R.drawable.t)
                            40-> listeiN = addElement(listeiN, R.drawable.k)
                            50-> listeiN = addElement(listeiN, R.drawable.c)
                            60-> listeiN = addElement(listeiN, R.drawable.s)
                            70-> listeiN = addElement(listeiN, R.drawable.sd)
                            80-> listeiN = addElement(listeiN, R.drawable.kv)
                            90-> listeiN = addElement(listeiN, R.drawable.kd)
                        }
                        //listeiN=addElement(listeiN,listei[index])
                    }
                    index++
                }
                val listView = findViewById<ListView>(R.id.listeView) as ListView
                val myListAdapter =
                    listeAdapterDetSuivi(this@detSuiviActivity, listetN, listecN, listesN, listeiN)
                listView.adapter = myListAdapter
                    var intent : Intent = Intent(applicationContext, listeAdapterSuivi::class.java)
                    intent.putExtra("projet", nprj)
            }
            var listet = arrayOf<String>()
            var listest = arrayOf<String>()
            val listecl = arrayOf<String>()
            var listeav = arrayOf<Int>()
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        }
            // fun(error: DatabaseError) {
              //TODO("Not yet implemented")
           // }
        )
    }
}