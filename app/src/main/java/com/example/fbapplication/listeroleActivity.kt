package com.example.fbapplication

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.fbapplication.databinding.ActivityListeprtBinding
import com.example.fbapplication.databinding.ActivityListeroleBinding
import com.example.fbapplication.databinding.ActivityListesttBinding
import com.example.fbapplication.models.NomProjet
import com.example.fbapplication.models.Project
import com.example.fbapplication.models.Projet
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class listeroleActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListeroleBinding
    private var db = Firebase.firestore
    var liste  = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listeprt)
        val intent = intent
        val nom = intent.getStringExtra("nom")
        val prenom = intent.getStringExtra("prenom")
        val email = intent.getStringExtra("email")
        val psw = intent.getStringExtra("psw")
        val rpsw = intent.getStringExtra("rpsw")

        binding = ActivityListeroleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val feelings = resources.getStringArray(R.array.role)
        val arrayAdapter = ArrayAdapter(this, R.layout.dropdown_item, feelings)
        binding.autoCompleteTextView.setAdapter(arrayAdapter)
        binding.autoCompleteTextView.setOnItemClickListener { _, _, position, _ ->
            val value = arrayAdapter.getItem(position) ?: ""
            val act=intent.getStringExtra("activity")
            if (act=="registeractivity") {
                var intent : Intent= Intent(applicationContext,RegisterActivity::class.java)
                intent.putExtra("role", value.toString())
                intent.putExtra("nom", nom)
                intent.putExtra("prenom", prenom)
                intent.putExtra("email", email)
                intent.putExtra("psw",psw)
                intent.putExtra("rpsw", rpsw)
                startActivity(intent)
            }
            else
            {
                var intent : Intent= Intent(applicationContext,CollabActivity::class.java)
                intent.putExtra("role", value.toString())
                intent.putExtra("nom", nom)
                intent.putExtra("prenom", prenom)
                intent.putExtra("email", email)
                intent.putExtra("psw",psw)
                intent.putExtra("rpsw", rpsw)
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
