package com.example.fbapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.fbapplication.models.Tache
import com.google.firebase.database.FirebaseDatabase

class TacheActivity : AppCompatActivity() {
    var db = FirebaseDatabase.getInstance().getReference("projet-4f405")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tache).toString()
    }

    public fun addTache(view: View)
    {
        var idt : String = db.push().key!!
        var name: String = findViewById<EditText>(R.id.nom_edit_text).text.toString()
        var collab: String = findViewById<EditText>(R.id.collaborateur_edit_text).text.toString()
        var status: String = findViewById<EditText>(R.id.status_edit_text).text.toString()
        var dated: String = findViewById<EditText>(R.id.editTextDateD).text.toString()
        var datef: String = findViewById<EditText>(R.id.editTextDateF).text.toString()
        var descr1: String = findViewById<EditText>(R.id.description1_edit_text).text.toString()
        var descr2: String = findViewById<EditText>(R.id.description2_edit_text).text.toString()
        var avance: String = findViewById<EditText>(R.id.editTextAvancementT).text.toString()
        var projet: String = findViewById<EditText>(R.id.editTextProjet).text.toString()

        if (name.isEmpty() || collab.isEmpty() || status.isEmpty() || dated.isEmpty() || datef.isEmpty() || descr1.isEmpty() || descr2.isEmpty() )
        {
            Toast.makeText(this, "Il faut remplir tous les champs", Toast.LENGTH_SHORT).show()
        }
        else
        {
            val avc=avance.toInt()
            val tache = Tache(idt,name, descr1, descr2, collab, dated, datef, projet,avc,status)
            //db.child("Base").child("Tache").child(UUID.randomUUID().toString()).push().setValue(tache)
            db.child("Base").child("Tache").child(idt).setValue(tache)
                .addOnCompleteListener {
                    Toast.makeText(this, "Data inserted successfully", Toast.LENGTH_LONG).show()
                }.addOnFailureListener { err ->
                    Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
                }
            startActivity(Intent(this, SuiviActivity::class.java))
        }
    }
}