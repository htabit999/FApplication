package com.example.fbapplication

import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase
import java.util.*

class TacheActivity : AppCompatActivity() {
    var db = FirebaseDatabase.getInstance().getReference("projet-4f405")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tache).toString()
    }

    public fun addTache(view: View) {
        var name: String = findViewById<EditText>(R.id.nom_edit_text).text.toString()
        var collab : String = findViewById<EditText>(R.id.collaborateur_edit_text).text.toString()
        var status : String = findViewById<EditText>(R.id.status_edit_text).text.toString()
        var dated : String = findViewById<EditText>(R.id.editTextDateD).text.toString()
        var datef : String = findViewById<EditText>(R.id.editTextDateF).text.toString()
        var descr : String = findViewById<EditText>(R.id.description_edit_text).text.toString()
        val tache = Tache(name, descr,collab,dated,datef,status)

        db.child("IDTache").child("Tache").child(UUID.randomUUID().toString()).push().setValue(tache)
        }
    }