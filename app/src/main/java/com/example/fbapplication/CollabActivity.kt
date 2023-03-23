package com.example.fbapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.fbapplication.models.Collaborateur
import com.google.firebase.database.FirebaseDatabase

class CollabActivity : AppCompatActivity() {
    var db = FirebaseDatabase.getInstance().getReference("projet-4f405")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_collab)
    }
    public fun addCollab(view: View)
        {
            var idc : String = db.push().key!!
            var nom: String = findViewById<EditText>(R.id.nomcol_edit_text).text.toString()
            var prenom: String = findViewById<EditText>(R.id.prenomcol_edit_text).text.toString()
            var fonction: String = findViewById<EditText>(R.id.emailcol_edit_text).text.toString()
            var email: String = findViewById<EditText>(R.id.fonctioncol_edit_text).text.toString()
            var role: String = findViewById<EditText>(R.id.rolecol_edit_text).text.toString()
            if (nom.isEmpty() || prenom.isEmpty() || fonction.isEmpty() || email.isEmpty() || role.isEmpty()) {
                Toast.makeText(this, "Il faut remplir tous les champs", Toast.LENGTH_SHORT).show()
            } else {
                val collab = Collaborateur(idc,nom, prenom, fonction, email, role)
                //db.child("Base").child("Collaborateur").child(UUID.randomUUID().toString()).push()
                  //  .setValue(tache)
                db.child("Base").child("Collaborateur").child(idc).setValue(collab)
                    .addOnCompleteListener {
                        Toast.makeText(this, "Data inserted successfully", Toast.LENGTH_LONG).show()
                    }.addOnFailureListener { err ->
                        Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
                    }
                startActivity(Intent(this, MenuActivity::class.java))
            }
        }
    }
