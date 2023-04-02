package com.example.fbapplication

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ListView
import com.example.fbapplication.models.Project
import com.example.fbapplication.models.Projet
import com.example.fbapplication.models.Tache
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class SuiviActivity : AppCompatActivity() {
    //lateinit var ref : DatabaseReference
    //lateinit var dataList : MutableList<Projet>
    lateinit var listView: ListView
    private var db = Firebase.firestore
    private lateinit var projList: ArrayList<Projet>
    override fun onCreate(savedInstanceState: Bundle?)
    {
        android.util.Log.d(ContentValues.TAG, "onCreate")
        super.onCreate(savedInstanceState)
        val TAG = javaClass.simpleName
        setContentView(R.layout.activity_suivi)
        listView = findViewById(R.id.listeView)
        var list = ArrayList<Project>()
        val user = FirebaseAuth.getInstance().currentUser!!.uid
        db.collection("Projet").whereEqualTo("USERID", user)
            .get()
            .addOnCompleteListener {
                if (it.isSuccessful)
                {
                    for (document in it.result) {
                        list.add(
                            Project(
                                document.data.getValue("Projet") as String,
                                document.data.getValue("Description1") as String,
                                document.data.getValue("datedeb") as String,
                                document.data.getValue("datefin") as String,
                                document.data.getValue("Status") as String,
                                document.data.getValue("USERID") as String,
                                document.data.getValue("Avancement").toString().toInt()
                            )
                        )
                    }
                }
        val listView = findViewById<ListView>(R.id.listeView) as ListView
        val myListAdapter = listeAdapterSuivi(this@SuiviActivity, list)
        listView.adapter = myListAdapter
        listView.setOnItemClickListener()
        {
                adapterView, view, position, id ->
                var intent : Intent = Intent(applicationContext, SuiviTacheActivity::class.java)
                intent.putExtra("projet", list[position].PROJET)
                intent.putExtra("description1", list[position].DESCRIPTION1)
                intent.putExtra("dated", list[position].DATEDEB)
                intent.putExtra("datef", list[position].DATEFIN)
                intent.putExtra("status", list[position].STATUS)
                intent.putExtra("avancement", list[position].AVANCEMENT.toString())
                intent.putExtra("id", list[position].USERID)
                startActivity(intent)
        }
    }
    fun onCancelled(error: DatabaseError) {
        TODO("Not yet implemented")
    }
}
public fun retourMenu(view: View)
{
    startActivity(Intent(this, menuSuiviActivity::class.java))
}
}
