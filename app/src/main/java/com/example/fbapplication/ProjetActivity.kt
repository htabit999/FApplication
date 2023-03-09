package com.example.fbapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.fbapplication.models.Projet
import com.google.firebase.database.FirebaseDatabase
import java.util.*

class ProjetActivity : AppCompatActivity() {
    var db = FirebaseDatabase.getInstance().getReference("projet-4f405")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_projet)
    }

    public fun addProjet(view: View) {
        var namep: String = findViewById<EditText>(R.id.nomp_edit_text).text.toString()
        var chef : String = findViewById<EditText>(R.id.chef_edit_text).text.toString()
        var statusp : String = findViewById<EditText>(R.id.statusP_edit_text).text.toString()
        var datedp : String = findViewById<EditText>(R.id.editTextPDateD).text.toString()
        var datefp : String = findViewById<EditText>(R.id.editTextPDateF).text.toString()
        var descr1p : String = findViewById<EditText>(R.id.descriptionp1_edit_text).text.toString()
        var descr2p : String = findViewById<EditText>(R.id.descriptionp2_edit_text).text.toString()
        if (namep.isEmpty() || chef.isEmpty() || statusp.isEmpty() || datedp.isEmpty() || datefp.isEmpty() || descr1p.isEmpty() || descr2p.isEmpty()  )
        {
            Toast.makeText(this, "IL faut remplir tous les champs", Toast.LENGTH_SHORT).show()
        }
        else
        {
            val projet = Projet(namep, descr1p, descr2p, chef, datedp, datefp, statusp)
            db.child("Base").child("Projet").child(UUID.randomUUID().toString()).push()
                .setValue(projet)
            startActivity(Intent(this, MenuActivity::class.java))
        }
    }
}