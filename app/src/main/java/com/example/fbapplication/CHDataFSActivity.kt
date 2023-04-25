package com.example.fbapplication

import android.content.ContentValues.TAG
import android.content.Intent
import android.content.Intent.getIntent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ListView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.fbapplication.models.*
import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.Tasks
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import org.checkerframework.checker.nullness.qual.NonNull
import kotlin.collections.ArrayList

class CHDataFSActivity : AppCompatActivity() {

    lateinit var listView: ListView
    private var db = Firebase.firestore
    override fun onCreate(savedInstanceState: Bundle?) {
        android.util.Log.d(TAG, "onCreate")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chdata_fsactivity)
        val user = FirebaseAuth.getInstance().currentUser!!.uid
        var list = ArrayList<Project>()
        var liste = ArrayList<ProjectView>()
        var intent1: Intent = getIntent()
        var nom = intent1.getStringExtra("nom").toString()
        var role = intent1.getStringExtra("role").toString()
        listView = findViewById(R.id.listeView)
        //
        val collection1 = db.collection("Projet")
        val collection2 = db.collection("Users")
        val query = collection1
        //.whereArrayContains("Id", "Projet")
        db.collection("Projet")
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    val id = document.get("Chef").toString()
                    val data = document.data
                    collection2.document(id).get().addOnSuccessListener { secondDocument ->
                        val secondData = secondDocument.data
                        if (secondData != null) {
                            liste.add(
                                ProjectView(
                                    data.getValue("Projet") as String,
                                    data.getValue("Description1") as String,
                                    data.getValue("datedeb") as String,
                                    data.getValue("datefin") as String,
                                    data.getValue("Status") as String,
                                    data.getValue("USERID") as String,
                                    data.getValue("Avancement").toString().toInt(),
                                    data.getValue("Chef").toString(),
                                    secondData.getValue("Url").toString()
                                )
                            )
                        }
                        val listView = findViewById<ListView>(R.id.listeView) as ListView
                        val myListAdapter = listeFSAdapter(this@CHDataFSActivity, liste)
                        listView.adapter = myListAdapter
                        listView.setOnItemClickListener()
                        { adapterView, view, position, id ->
                            val role = intent.getStringExtra("role")
                            val nom = intent.getStringExtra("nom")
                            val user = intent.getStringExtra("user")
                            var intent: Intent =
                                Intent(applicationContext, majCHProjetActivity::class.java)
                            intent.putExtra("projet", liste[position].PROJET)
                            intent.putExtra("description1", liste[position].DESCRIPTION1)
                            intent.putExtra("dated", liste[position].DATEDEB)
                            intent.putExtra("datef", liste[position].DATEFIN)
                            intent.putExtra("status", liste[position].STATUS)
                            intent.putExtra("av", liste[position].AVANCEMENT.toString())
                            intent.putExtra("id", liste[position].USERID)
                            intent.putExtra("url", liste[position].URL)
                            intent.putExtra("ch", liste[position].CHEF)
                            intent.putExtra("role", role)
                            intent.putExtra("nom", nom)
                            intent.putExtra("user", user)
                            startActivity(intent)
                            Toast.makeText(this, "UID :"+liste[position].USERID, Toast.LENGTH_SHORT).show()
                    }
                }
                       //
                    //db.collection("Projet")
                    //    .get()
                    //    .addOnCompleteListener {
                    //       if (it.isSuccessful) {
                    //          for (document in it.result) {
                    //              list.add(
                    //                  Project(
                    //                      document.data.getValue("Projet") as String,
                    //                      document.data.getValue("Description1") as String,
                    //                      document.data.getValue("datedeb") as String,
                    //                      document.data.getValue("datefin") as String,
                    //                      document.data.getValue("Status") as String,
                    //                      document.data.getValue("USERID") as String,
                    //                      document.data.getValue("Avancement").toString().toInt(),
                    //                      document.data.getValue("Chef").toString()
                    //                  )
                    //              )
                    //          }
                    //      }

                    }
                }
    }

    fun getUrl(nom : String): ArrayList<Usr>
    {
            var dbb = Firebase.firestore
            var lista = ArrayList<Usr>()
            dbb.collection("Users").whereEqualTo("Nom",nom)
                .get()
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        for (document in it.result) {
                            lista.add(
                                Usr(
                                    document.data.getValue("Nom") as String,
                                    document.data.getValue("Prenom") as String,
                                    document.data.getValue("Email") as String,
                                    document.data.getValue("UID") as String,
                                    document.data.getValue("Role") as String,
                                    document.data.getValue("Url") as String
                                )
                            )
                         }
                    }
                    else
                    {
                        Toast.makeText(this, "Non Trouve :", Toast.LENGTH_SHORT).show()
                    }
                }
                //Toast.makeText(this, "lISTE:"+lista, Toast.LENGTH_SHORT).show()
                return lista
    }

    fun retourMenu(view: View)
    {
        var intent1 :Intent= getIntent()
        var nom = intent1.getStringExtra("nom").toString()
        var role = intent1.getStringExtra("role").toString()
        var user = intent1.getStringExtra("user").toString()
        val intent: Intent =  Intent(applicationContext, MenuCHProjetActivity::class.java)
        intent.putExtra("role", role)
        intent.putExtra("nom", nom)
        intent.putExtra("user", user)
        startActivity(intent)
    }

    fun getPublicProfile(userId: String): Users? {
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
    fun readData(user:String):ArrayList<Users> {
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