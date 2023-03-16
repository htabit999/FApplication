package com.example.fbapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.fbapplication.models.Collaborateur
import com.example.fbapplication.models.Tache
import com.google.firebase.database.FirebaseDatabase

class majCollabActivity : AppCompatActivity() {
    var db = FirebaseDatabase.getInstance().getReference("projet-4f405")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maj_collab)
        val intent = intent
        val nom = intent.getStringExtra("nom")
        val prenom = intent.getStringExtra("prenom")
        val fonction = intent.getStringExtra("fonction")
        val role = intent.getStringExtra("role")
        val email = intent.getStringExtra("email")
        val idc = intent.getStringExtra("idc")
        val nm = this.findViewById(R.id.nomcol_edit_text) as TextView
        val pr = this.findViewById(R.id.prenomcol_edit_text) as TextView
        val em = this.findViewById(R.id.emailcol_edit_text) as TextView
        val fc = this.findViewById(R.id.fonctioncol_edit_text) as TextView
        val rl = this.findViewById(R.id.rolecol_edit_text) as TextView
        nm.text = nom
        pr.text=prenom
        em.text=email
        fc.text = fonction
        rl.text=role
    }

    public fun modCollab(view: View) {
        var idc = intent.getStringExtra("idc").toString()
        var ncol: String = findViewById<EditText>(R.id.nomcol_edit_text).text.toString()
        var pcol: String = findViewById<EditText>(R.id.prenomcol_edit_text).text.toString()
        var fcol: String = findViewById<EditText>(R.id.fonctioncol_edit_text).text.toString()
        var ecol: String = findViewById<EditText>(R.id.emailcol_edit_text).text.toString()
        var rcol: String = findViewById<EditText>(R.id.rolecol_edit_text).text.toString()

        if (ncol.isEmpty() || pcol.isEmpty() || fcol.isEmpty() || ecol.isEmpty() || rcol.isEmpty() ) {
            Toast.makeText(this, "Il faut remplir tous les champs", Toast.LENGTH_SHORT).show()
        } else {
            val colab = Collaborateur(idc,ncol, pcol, fcol, ecol, rcol)
            //Toast.makeText(this, id.toString(), Toast.LENGTH_SHORT).show()
            //
            db.child("Base").child("Collaborateur").child(idc).setValue(colab)
                .addOnCompleteListener {
                    Toast.makeText(this, "Data updated successfully", Toast.LENGTH_LONG).show()
                }.addOnFailureListener { err ->
                    Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
                }
            startActivity(Intent(this, listeCollabActivity::class.java))
        }
    }

    public fun supCollab(view: View) {
        var idt = intent.getStringExtra("idt").toString()
        db.child("Base").child("Collaborateur").child(idt).removeValue();
        startActivity(Intent(this, listeCollabActivity::class.java))
    }
}