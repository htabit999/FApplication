package com.example.fbapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.fbapplication.models.Projet
import com.google.firebase.database.FirebaseDatabase
import java.util.*

class majProjetActivity : AppCompatActivity() {
    var db = FirebaseDatabase.getInstance().getReference("projet-4f405")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maj_projet)
        val intent = intent
        val nprj = intent.getStringExtra("projet")
        val d1 = intent.getStringExtra("description1")
        val d2 = intent.getStringExtra("description2")
        val ch = intent.getStringExtra("chef")
        val st = intent.getStringExtra("status")
        val dd = intent.getStringExtra("dated")
        val df = intent.getStringExtra("datef")
        val av= intent.getStringExtra("av")

        //val id = intent.getStringExtra("id")
        //Toast.makeText(this, ch, Toast.LENGTH_LONG).show()
        val prj = this.findViewById(R.id.nomp_edit_text) as TextView
        val des1 = this.findViewById(R.id.descriptionp1_edit_text) as TextView
        val des2 = this.findViewById(R.id.descriptionp2_edit_text) as TextView
        val che = this.findViewById(R.id.chef_edit_text) as TextView
        val dtd = this.findViewById(R.id.editTextPDateD) as TextView
        val dtf = this.findViewById(R.id.editTextPDateF) as TextView
        val sta = this.findViewById(R.id.statusP_edit_text) as TextView
        val avc = this.findViewById(R.id.editTextAv) as TextView

        prj.text = nprj
        des1.text=d1
        des2.text=d2
        che.text = ch
        dtd.text=dd
        dtf.text = df
        sta.text=st
        avc.text=av
    }

    public fun modProjet(view: View) {
        var id = intent.getStringExtra("id").toString()
        var namep: String = findViewById<EditText>(R.id.nomp_edit_text).text.toString()
        var chef: String = findViewById<EditText>(R.id.chef_edit_text).text.toString()
        var statusp: String = findViewById<EditText>(R.id.statusP_edit_text).text.toString()
        var datedp: String = findViewById<EditText>(R.id.editTextPDateD).text.toString()
        var datefp: String = findViewById<EditText>(R.id.editTextPDateF).text.toString()
        var descr1p: String = findViewById<EditText>(R.id.descriptionp1_edit_text).text.toString()
        var descr2p: String = findViewById<EditText>(R.id.descriptionp2_edit_text).text.toString()
        var avance: String = findViewById<EditText>(R.id.editTextAv).text.toString()
        var iav=avance.toInt()

        if (namep.isEmpty() || chef.isEmpty() || statusp.isEmpty() || datedp.isEmpty() || datefp.isEmpty() || descr1p.isEmpty() || descr2p.isEmpty() || avance.isEmpty()) {
            Toast.makeText(this, "Il faut remplir tous les champs", Toast.LENGTH_SHORT).show()
        } else {
            val projet = Projet(id,namep, descr1p, descr2p, chef, datedp, datefp, statusp,iav)
            //Toast.makeText(this, id.toString(), Toast.LENGTH_SHORT).show()
            //
            db.child("Base").child("Projet").child(id).setValue(projet)
                .addOnCompleteListener {
                    Toast.makeText(this, "Data updated successfully", Toast.LENGTH_LONG).show()
                }.addOnFailureListener { err ->
                    Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
                }
            startActivity(Intent(this, DataActivity::class.java))
        }
    }

    public fun supProjet(view: View) {
        var id = intent.getStringExtra("id").toString()
        //db.child("Base").child("Projet").orderByChild("UUID").equalTo(id.toString())
        db.child("Base").child("Projet").child(id).removeValue();
        startActivity(Intent(this, DataActivity::class.java))
    }
}
