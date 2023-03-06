package com.example.fbapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
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
        var collab: String = findViewById<EditText>(R.id.collaborateur_edit_text).text.toString()
        var status: String = findViewById<EditText>(R.id.status_edit_text).text.toString()
        var dated: String = findViewById<EditText>(R.id.editTextDateD).text.toString()
        var datef: String = findViewById<EditText>(R.id.editTextDateF).text.toString()
        var descr1: String = findViewById<EditText>(R.id.description1_edit_text).text.toString()
        var descr2: String = findViewById<EditText>(R.id.description2_edit_text).text.toString()
        if (name.isEmpty() || collab.isEmpty() || status.isEmpty() || dated.isEmpty() || datef.isEmpty() || descr1.isEmpty() || descr2.isEmpty() )
        {
            Toast.makeText(this, "Il faut remplir tous les champs", Toast.LENGTH_SHORT).show()
        }
        else
        {
            val tache = Tache(name, descr1, descr2, collab, dated, datef, status)
            db.child("Base").child("Tache").child(UUID.randomUUID().toString()).push().setValue(tache)
            startActivity(Intent(this, MenuActivity::class.java))
        }
    }
    }