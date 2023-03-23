package com.example.fbapplication

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.example.fbapplication.models.Projet
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlin.collections.ArrayList

class DataActivity : AppCompatActivity()
{
    lateinit var ref : DatabaseReference
    //lateinit var dataList : MutableList<Projet>
    lateinit var listView: ListView
    //private lateinit var projList: ArrayList<Projet>
    //private var db = Firebase.firestore
    var listep = arrayOf<String>()
    var listed1 = arrayOf<String>()
    var listed2 = arrayOf<String>()
    var listechef = arrayOf<String>()
    var listedd = arrayOf<String>()
    var listedf = arrayOf<String>()
    var listest = arrayOf<String>()
    var listeid = arrayOf<String>()
    var listeav = arrayOf<String>()
    var listei = arrayOf<Int>()
    //var mApp = LoginActivity()
    //var strGlobalVar = mApp.user.toString()
    //lateinit var test : Array<String>
    private val test=ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?)
    {
        android.util.Log.d(TAG, "onCreate")
        super.onCreate(savedInstanceState)
        val TAG = javaClass.simpleName
        setContentView(R.layout.activity_data)
        var intent1 :Intent= getIntent()
        val user = FirebaseAuth.getInstance().currentUser!!.uid
        //var user = intent1.getStringExtra("user").toString()
        //dataList = mutableListOf()
        listView = findViewById(R.id.listeView)
        var items = mutableListOf<Projet>()
        val prjList = listep.toMutableList()
        //var list = arrayListOf(Projet)

        ref= FirebaseDatabase.getInstance().getReference( "projet-4f405")
        ref.addValueEventListener(object : ValueEventListener
        {
            val user = FirebaseAuth.getInstance().currentUser!!.uid
            val projList = arrayListOf<Projet>()
            //val empId = dbRef.push().key!!
            override fun onDataChange(snapshot: DataSnapshot)
            {
                if (snapshot!!.exists())
                {
                //dataList.clear()
                for ( e in snapshot.children)
                {
                    //Toast.makeText(this@DataActivity, e.key, Toast.LENGTH_LONG).show()
                    e.child("Projet").children.forEach() {
                        //Toast.makeText(this@DataActivity, e.child("Projet").children.toString(), Toast.LENGTH_LONG).show()
                        it.children.forEach {
                                //it.children.forEach {
                                if (it.key == "id") {
                                    listeid= addElement(listeid, it.value as String)
                                }
                                if (it.key == "projet") {
                                    listep = addElement(listep, it.value as String)
                                }
                                if (it.key == "description1") {
                                    listed1 = addElement(listed1, it.value as String)
                                }
                                if (it.key == "description2") {
                                    listed2 = addElement(listed2, it.value as String)
                                }
                                if (it.key == "chef") {
                                     listechef= addElement(listechef, it.value as String)
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
                                if (it.key == "avancement") {
                                    listeav= addElement(listeav,it.getValue().toString())
                                    // Toast.makeText(this@DataActivity, it.value as String, Toast.LENGTH_LONG).show()
                                    if (0<= it.getValue().toString().toInt() && it.getValue().toString().toInt()<= 10 ){
                                       listei = addElement(listei, R.drawable.d)
                                    }
                                    if (11<= it.getValue().toString().toInt() && it.getValue().toString().toInt()<= 20 ){
                                       listei = addElement(listei, R.drawable.v)
                                    }
                                   if (21<= it.getValue().toString().toInt() && it.getValue().toString().toInt()<= 30 ){
                                        listei = addElement(listei, R.drawable.t)
                                   }
                                    if (31<= it.getValue().toString().toInt() && it.getValue().toString().toInt()<= 40){
                                        listei = addElement(listei, R.drawable.k)
                                    }
                                    if (41<= it.getValue().toString().toInt() && it.getValue().toString().toInt()<= 50 ){
                                        listei = addElement(listei, R.drawable.c)
                                    }
                                    if (51<= it.getValue().toString().toInt() && it.getValue().toString().toInt()<= 60 ){
                                        listei = addElement(listei, R.drawable.s)
                                    }
                                   if (61<= it.getValue().toString().toInt() && it.getValue().toString().toInt()<= 70 ){
                                      listei = addElement(listei, R.drawable.sd)
                                   }
                                    if (71<= it.getValue().toString().toInt() && it.getValue().toString().toInt()<= 80 ){
                                       listei = addElement(listei, R.drawable.kv)
                                    }
                                  if (81<= it.getValue().toString().toInt() && it.getValue().toString().toInt()<= 90 ){
                                     listei = addElement(listei, R.drawable.kd)
                                  }
                                if (91<= it.getValue().toString().toInt() && it.getValue().toString().toInt()<= 100 ){
                                   listei = addElement(listei, R.drawable.kd)
                                }
                                }
                            }
                        }
                }
                val listView = findViewById<ListView>(R.id.listeView) as ListView
                val myListAdapter = listeAdapter(this@DataActivity, listep ,listed1, listei)
                listView.adapter = myListAdapter
                listView.setOnItemClickListener()
                { adapterView, view, position, id ->
                    val itemAtPos = adapterView.getItemAtPosition(position)
                    val itemIdAtPos = adapterView.getItemIdAtPosition(position)
                    val itemdesc1 = listed1[position]
                    val itemdesc2 = listed2[position]
                    val itemdd = listedd[position]
                    val itemdf = listedf[position]
                    val itemst = listest[position]
                    val itemch = listechef[position]
                    val itemid = listeid[position]
                    val itemav = listeav[position]
                    var intent: Intent = Intent(applicationContext, majProjetActivity::class.java)
                    var projet = adapterView.getItemAtPosition(position).toString()
                    intent.putExtra("projet", projet)
                    intent.putExtra("description1", itemdesc1)
                    intent.putExtra("description2", itemdesc2)
                    intent.putExtra("dated", itemdd)
                    intent.putExtra("datef", itemdf)
                    intent.putExtra("chef", itemch)
                    intent.putExtra("status", itemst)
                    intent.putExtra("id", itemid)
                    intent.putExtra("av", itemav)
                    startActivity(intent)
                    //Toast.makeText(this@DataActivity, "Click on item at ", Toast.LENGTH_LONG).show()
                }
            }

            fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

            val listep = arrayOf<String>()
            val listed1 = arrayOf<String>()
            val listei = arrayOf<Int>()

        }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}
