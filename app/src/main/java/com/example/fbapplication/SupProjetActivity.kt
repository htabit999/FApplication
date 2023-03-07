package com.example.fbapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class SupProjetActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sup_projet)
    }
    public fun suppProjet(view: View) {
        //var namep: String = findViewById<EditText>(R.id.nomp_edit_text).text.toString()
        // projet = Projet(namep, "","","","","","")
        //db.child("Base").child("Projet").child(UUID.randomUUID().toString()).push().setValue(projet)
        //startActivity(Intent(this, MenuActivity::class.java))
    }
}