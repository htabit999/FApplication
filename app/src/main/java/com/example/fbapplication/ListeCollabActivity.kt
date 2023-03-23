package com.example.fbapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.example.fbapplication.models.Collaborateur
import com.google.firebase.database.*

class listeCollabActivity : AppCompatActivity() {

    lateinit var ref: DatabaseReference
    lateinit var dataList: MutableList<Collaborateur>
    lateinit var listView: ListView
    private lateinit var collabList: ArrayList<Collaborateur>
    var lnom = arrayOf<String>()
    var lprenom = arrayOf<String>()
    var lfonction = arrayOf<String>()
    var lrole = arrayOf<String>()
    var lemail = arrayOf<String>()
    var listei = arrayOf<Int>()
    var listeid = arrayOf<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val TAG = javaClass.simpleName
        setContentView(R.layout.activity_liste_collab)
        dataList = mutableListOf()
        listView = findViewById(R.id.listeCView)
        ref = FirebaseDatabase.getInstance().getReference("projet-4f405")
        ref.addValueEventListener(object : ValueEventListener
        {
            val collabList = arrayListOf<Collaborateur>()
            override fun onDataChange(snapshot: DataSnapshot)
            {
                if (snapshot!!.exists())
                {
                    dataList.clear()
                    for (e in snapshot.children)
                    {
                        e.child("Collaborateur").children.forEach()
                        {
                            it.children.forEach()
                            {
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
                                if (it.key == "email") {
                                    lemail = addElement(lemail, it.value as String)
                                }
                                if (it.key == "fonction") {
                                    lfonction = addElement(lfonction, it.value as String)
                                }
                                if (it.key == "role") {
                                    lrole = addElement(lrole, it.value as String)
                                }
                            }
                        }
                    }
                    val listView = findViewById<ListView>(R.id.listeCView) as ListView
                    val myListAdapter =  listeAdapter(this@listeCollabActivity, lnom, lprenom, listei)
                    listView.adapter = myListAdapter
                    listView.setOnItemClickListener()
                    { adapterView, view, position, id ->
                        val itemnom = lnom[position]
                        val itempre = lprenom[position]
                        val itemfct = lfonction[position]
                        val itemdeml = lemail[position]
                        val itemrol = lrole[position]
                        val itemid = listeid[position]

                        var intent: Intent =
                            Intent(applicationContext, majCollabActivity::class.java)
                        var collab = adapterView.getItemAtPosition(position).toString()
                        intent.putExtra("nom", itemnom)
                        intent.putExtra("prenom", itempre)
                        intent.putExtra("fonction", itemfct)
                        intent.putExtra("email", itemdeml)
                        intent.putExtra("role", itemrol)
                        intent.putExtra("idc", itemid)
                        startActivity(intent)
                    }
                }

                val lnom = arrayOf<String>()
                val lprenom = arrayOf<String>()
                val listei = arrayOf<Int>()

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
