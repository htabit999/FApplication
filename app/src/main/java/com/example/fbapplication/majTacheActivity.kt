package com.example.fbapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.fbapplication.models.Projet
import com.example.fbapplication.models.Tache
import com.google.firebase.database.FirebaseDatabase

class majTacheActivity : AppCompatActivity() {
    var db = FirebaseDatabase.getInstance().getReference("projet-4f405")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maj_tache)
    val intent = intent
    val tch = intent.getStringExtra("tache")
    val d1 = intent.getStringExtra("description1")
    val d2 = intent.getStringExtra("description2")
    val cl = intent.getStringExtra("col")
    val st = intent.getStringExtra("status")
    val dd = intent.getStringExtra("dated")
    val df = intent.getStringExtra("datef")
    val pr = intent.getStringExtra("projet")
    val av = intent.getStringExtra("avancement")

    val ntch = this.findViewById(R.id.nomt_edit_text) as TextView
    val des1 = this.findViewById(R.id.descriptionp1_edit_text) as TextView
    val des2 = this.findViewById(R.id.descriptionp2_edit_text) as TextView
    val col = this.findViewById(R.id.col_edit_text) as TextView
    val dtd = this.findViewById(R.id.editTextTDateD) as TextView
    val dtf = this.findViewById(R.id.editTextTDateF) as TextView
    val sta = this.findViewById(R.id.statusT_edit_text) as TextView
    val pro = this.findViewById(R.id.editTextProjet) as TextView
    val avc = this.findViewById(R.id.editTextAvancement) as TextView
    ntch.text = tch
    des1.text=d1
    des2.text=d2
    col.text = cl
    dtd.text=dd
    dtf.text = df
    sta.text=st
    pro.text=pr
    avc.text=av
}

public fun modTache(view: View) {
    var idt = intent.getStringExtra("idt").toString()
    var ntch: String = findViewById<EditText>(R.id.nomt_edit_text).text.toString()
    var col: String = findViewById<EditText>(R.id.col_edit_text).text.toString()
    var statusp: String = findViewById<EditText>(R.id.statusT_edit_text).text.toString()
    var datedp: String = findViewById<EditText>(R.id.editTextTDateD).text.toString()
    var datefp: String = findViewById<EditText>(R.id.editTextTDateF).text.toString()
    var descr1p: String = findViewById<EditText>(R.id.descriptionp1_edit_text).text.toString()
    var descr2p: String = findViewById<EditText>(R.id.descriptionp2_edit_text).text.toString()
    var projet: String = findViewById<EditText>(R.id.editTextProjet).text.toString()
    var avance: String = findViewById<EditText>(R.id.editTextAvancement).text.toString()
    if (ntch.isEmpty() || col.isEmpty() || statusp.isEmpty() || datedp.isEmpty() || datefp.isEmpty() || descr1p.isEmpty() || descr2p.isEmpty() || projet.isEmpty() || avance.isEmpty()) {
        Toast.makeText(this, "Il faut remplir tous les champs", Toast.LENGTH_SHORT).show()
    } else {
        var av=avance.toInt()
        val tache = Tache(idt,ntch, descr1p, descr2p, col, datedp, datefp, projet,av,statusp)
        //Toast.makeText(this, id.toString(), Toast.LENGTH_SHORT).show()
        //
        db.child("Base").child("Tache").child(idt).setValue(tache)
            .addOnCompleteListener {
                Toast.makeText(this, "Data updated successfully", Toast.LENGTH_LONG).show()
            }.addOnFailureListener { err ->
                Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
            }
        startActivity(Intent(this, listeTacheActivity::class.java))
    }
}

public fun supTache(view: View) {
    var idt = intent.getStringExtra("idt").toString()
    db.child("Base").child("Tache").child(idt).removeValue();
    startActivity(Intent(this, listeTacheActivity::class.java))
}
}