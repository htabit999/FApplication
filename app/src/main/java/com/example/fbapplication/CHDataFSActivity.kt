package com.example.fbapplication

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import com.example.fbapplication.databinding.ActivityDataFsactivityBinding
import com.example.fbapplication.models.Project
import com.example.fbapplication.models.Users
import com.google.android.gms.tasks.Tasks
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlin.collections.ArrayList

class CHDataFSActivity : AppCompatActivity() {

    lateinit var listView: ListView
    private var db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        android.util.Log.d(TAG, "onCreate")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chdata_fsactivity)
        val user = FirebaseAuth.getInstance().currentUser!!.uid
        listView = findViewById(R.id.listeView)
        var list = ArrayList<Project>()
        var intent1 :Intent= getIntent()
        var nom = intent1.getStringExtra("nom").toString()
        var role = intent1.getStringExtra("role").toString()
        //Toast.makeText(this, "CH data ", Toast.LENGTH_LONG).show()
        //Toast.makeText(this, "Nom Chef FS: "+nom, Toast.LENGTH_LONG).show()
        //Toast.makeText(this, "4 Nom Chef  "+nom, Toast.LENGTH_LONG).show()
        //.whereEqualTo("Chef", nom)
        db.collection("Projet")
            .get()
            .addOnCompleteListener {
                if (it.isSuccessful)
                {
                    //Toast.makeText(this, "chdata ", Toast.LENGTH_LONG).show()
                    for (document in it.result) {
                        list.add(
                            Project(
                                document.data.getValue("Projet") as String,
                                document.data.getValue("Description1") as String,
                                document.data.getValue("datedeb") as String,
                                document.data.getValue("datefin") as String,
                                document.data.getValue("Status") as String,
                                document.data.getValue("USERID") as String,
                                document.data.getValue("Avancement").toString().toInt(),
                                document.data.getValue("Chef").toString()
                            )
                        )
                    }
                }
                val listView = findViewById<ListView>(R.id.listeView) as ListView
                val myListAdapter = listeFSAdapter(this@CHDataFSActivity, list)
                listView.adapter = myListAdapter
                //Toast.makeText(this, "List : "+list, Toast.LENGTH_LONG).show()
                listView.setOnItemClickListener()
                { adapterView, view, position, id ->
                    val role = intent.getStringExtra("role")
                    val nom = intent.getStringExtra("nom")
                    val user = intent.getStringExtra("user")
                    var intent: Intent = Intent(applicationContext, majCHProjetActivity::class.java)
                    intent.putExtra("projet", list[position].PROJET)
                    intent.putExtra("description1", list[position].DESCRIPTION1)
                    intent.putExtra("dated", list[position].DATEDEB)
                    intent.putExtra("datef", list[position].DATEFIN)
                    intent.putExtra("status", list[position].STATUS)
                    intent.putExtra("av", list[position].AVANCEMENT.toString())
                    intent.putExtra("id", list[position].USERID)
                    intent.putExtra("ch", list[position].CHEF)
                    intent.putExtra("role", role)
                    intent.putExtra("nom", nom)
                    intent.putExtra("user", user)
                    startActivity(intent)
                }
                //Toast.makeText(this, "6 ", Toast.LENGTH_LONG).show()
            }
    }

    public fun retourMenu(view: View)
    {
        val role = intent.getStringExtra("role")
        val nom = intent.getStringExtra("nom")
        val user = intent.getStringExtra("user")
        val intent: Intent =  Intent(applicationContext, MenuCHProjetActivity::class.java)
        intent.putExtra("role", role)
        intent.putExtra("nom", nom)
        intent.putExtra("user", user)
        startActivity(intent)
        //startActivity(Intent(this, MenuCHProjetActivity::class.java))
    }

    private fun getPublicProfile(userId: String): Users? {
        return try {
            val privateDataRef = db.collection("Users").document(userId)
            val document = Tasks.await(privateDataRef.get())
            if (document.exists()) {
                val publicProfile = document.toObject(Users::class.java)
                publicProfile
            } else null
        } catch (e: Throwable) {
            null
        }
    }
    private fun readData(user:String):ArrayList<Users> {
        var liste = ArrayList<Users>()
        db.collection("Users").whereEqualTo("UID", user)
            .get()
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    for (document in it.result) {
                        liste.add(
                            Users(
                                document.data.getValue("Nom") as String,
                                document.data.getValue("Prenom") as String,
                                document.data.getValue("Email") as String,
                                document.data.getValue("UID") as String,
                                document.data.getValue("Role") as String
                            )
                        )
                    }
                }
            }
        return liste
    }
}