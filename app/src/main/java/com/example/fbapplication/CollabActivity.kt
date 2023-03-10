package com.example.fbapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import java.util.*

class CollabActivity : AppCompatActivity() {
    var db = FirebaseDatabase.getInstance().getReference("projet-4f405")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_collab)
    }
    public fun addCollab(view: View)
        {
            var nom: String = findViewById<EditText>(R.id.nomcol_edit_text).text.toString()
            var prenom: String = findViewById<EditText>(R.id.prenomcol_edit_text).text.toString()
            var fonction: String = findViewById<EditText>(R.id.emailcol_edit_text).text.toString()
            var email: String = findViewById<EditText>(R.id.fonctioncol_edit_text).text.toString()
            var role: String = findViewById<EditText>(R.id.rolecol_edit_text).text.toString()
            if (nom.isEmpty() || prenom.isEmpty() || fonction.isEmpty() || email.isEmpty() || role.isEmpty()) {
                Toast.makeText(this, "Il faut remplir tous les champs", Toast.LENGTH_SHORT).show()
            } else {
                val tache = Collaborateur(nom, prenom, fonction, email, role)
                db.child("Base").child("Collaborateur").child(UUID.randomUUID().toString()).push()
                    .setValue(tache)
                startActivity(Intent(this, MenuActivity::class.java))
            }
        }
    }
