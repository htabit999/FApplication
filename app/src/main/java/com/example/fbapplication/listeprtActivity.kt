package com.example.fbapplication

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.fbapplication.databinding.ActivityListeprtBinding
import com.example.fbapplication.databinding.ActivityListesttBinding
import com.example.fbapplication.models.NomProjet
import com.example.fbapplication.models.Project
import com.example.fbapplication.models.Projet
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class listeprtActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListeprtBinding
    private var db = Firebase.firestore
    var liste  = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listeprt)
        val user = FirebaseAuth.getInstance().currentUser!!.uid
        val list = ArrayList<NomProjet>()
        readDataFromFirestore(10)
        //db.collection("Projet")
        //    .get()
        //    .addOnCompleteListener{
        //        if (it.isSuccessful) {
        //          for (document in it.result) {
        //              //namesList.add("document")
        //                list.add(
        //                    NomProjet(
        //                        document.data.getValue("Projet") as String
        //                    )
        //                )
        //            }
        //        }
        //    }
            val intent = intent
            val tache = intent.getStringExtra("tache")
            val projet = intent.getStringExtra("projet")
            val d1 = intent.getStringExtra("description1")
            val st = intent.getStringExtra("status")
            val dd = intent.getStringExtra("dated")
            val df = intent.getStringExtra("datef")
            val av = intent.getStringExtra("avancement")
            val act=intent.getStringExtra("activity")
            binding = ActivityListeprtBinding.inflate(layoutInflater)
            setContentView(binding.root)
            val arrayAdapter = ArrayAdapter(this, R.layout.dropdown_item, liste)
            binding.autoCompleteTextView.setAdapter(arrayAdapter)
            binding.autoCompleteTextView.setOnItemClickListener { _, _, position, _ ->
                val value = arrayAdapter.getItem(position) ?: ""
                val act=intent.getStringExtra("activity")
                if (act=="majtacheactivity") {
                    var intent : Intent= Intent(applicationContext, majTacheActivity::class.java)
                    intent.putExtra("projet", value.toString())
                    intent.putExtra("tache", tache)
                    intent.putExtra("description1", d1)
                    intent.putExtra("dated", dd)
                    intent.putExtra("datef",df)
                    intent.putExtra("status", st)
                    intent.putExtra("avancement", av)
                    startActivity(intent)
                }
                else
                {
                    var intent : Intent= Intent(applicationContext, TacheActivity::class.java)
                    intent.putExtra("projet", value.toString())
                    intent.putExtra("tache", tache)
                    intent.putExtra("description1", d1)
                    intent.putExtra("dated", dd)
                    intent.putExtra("datef",df)
                    intent.putExtra("status", st)
                    intent.putExtra("avancement", av)
                    startActivity(intent)
                }
        }
    }

    private fun readDataFromFirestore(ageCondition: Int){
        var mFirestore = FirebaseFirestore.getInstance()
        mFirestore.firestoreSettings = FirebaseFirestoreSettings.Builder().build()
        mFirestore
            .collection("Projet")
            //.whereLessThan("age", ageCondition)
            .get()
            .addOnSuccessListener { documents ->
                try {
                    if (documents != null) {
                        for (document in documents) {
                            Log.d(TAG, "${document.id} => ${document.data}")
                            liste.add(document.id as String)
                        }
                        //Log.e(TAG, je.size.toString())
                   } else {
                        Toast.makeText(this, "No such document!", Toast.LENGTH_LONG).show()
                    }
                }catch (ex: Exception)
                {
                    Log.e(TAG, ex.message.toString())
                }
            }.addOnFailureListener{
                    e -> Log.e(TAG, "Error writing document", e)
            }
    }
}
