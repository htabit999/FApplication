package com.example.fbapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.fbapplication.models.Tache
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.HashMap

class majTacheActivity : AppCompatActivity() {
    var db = FirebaseDatabase.getInstance().getReference("projet-4f405")
    private var dbf = Firebase.firestore
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maj_tache)
        val intent = intent

        val tch = intent.getStringExtra("tache")
        val d1 = intent.getStringExtra("description1")
        val st = intent.getStringExtra("status")
        val dd = intent.getStringExtra("dated")
        val df = intent.getStringExtra("datef")
        val pr = intent.getStringExtra("projet")
        val av = intent.getStringExtra("avancement")
        val cl = intent.getStringExtra("col")
        val ntch = this.findViewById(R.id.nom_edit_text) as TextView
        val des1 = this.findViewById(R.id.description1_edit_text) as TextView
        val dtd = this.findViewById(R.id.editTextDateD) as TextView
        val dtf = this.findViewById(R.id.editTextDateF) as TextView
        val sta = this.findViewById(R.id.status_edit_text) as TextView
        val pro = this.findViewById(R.id.projet_edit_text) as TextView
        val avc = this.findViewById(R.id.editTextAvancementT) as TextView
        val col = this.findViewById(R.id.editTextCollab) as TextView
        val nom = intent.getStringExtra("nom")

        ntch.text = tch
        des1.text=d1
        dtd.text=dd
        dtf.text = df
        sta.text=st
        pro.text=pr
        avc.text=av
        col.text=cl
    }

public fun modTache(view: View) {
    //var idt = intent.getStringExtra("idt").toString()
    var tache: String = findViewById<EditText>(R.id.nom_edit_text).text.toString()
    var statusp: String = findViewById<EditText>(R.id.status_edit_text).text.toString()
    var datedp: String = findViewById<EditText>(R.id.editTextDateD).text.toString()
    var datefp: String = findViewById<EditText>(R.id.editTextDateF).text.toString()
    var descr1p: String = findViewById<EditText>(R.id.description1_edit_text).text.toString()
    var projet: String = findViewById<EditText>(R.id.projet_edit_text).text.toString()
    var avance: String = findViewById<EditText>(R.id.editTextAvancementT).text.toString()
    var col: String = findViewById<EditText>(R.id.editTextCollab).text.toString()
    var user = FirebaseAuth.getInstance().currentUser!!.uid
    if (tache.isEmpty() || statusp.isEmpty() || datedp.isEmpty() || datefp.isEmpty() || descr1p.isEmpty() || projet.isEmpty() || avance.isEmpty() || col.isEmpty()) {
        Toast.makeText(this, "Il faut remplir tous les champs", Toast.LENGTH_SHORT).show()
    } else {
        val Tache = HashMap<String, Any>()
        Tache["Tache"] = tache
        Tache["Projet"] = projet
        Tache["Description1"] = descr1p
        Tache["datedeb"] = datedp
        Tache["datefin"] = datefp
        Tache["Status"] = statusp
        Tache["USERID"] = user
        Tache["Avancement"] = avance
        Tache["Collaborateur"] = col
        dbf.collection("Tache")
            .document(tache).set(Tache)
            .addOnSuccessListener {
                Toast.makeText(this, "Data updated ", Toast.LENGTH_LONG).show()
                val nom = intent.getStringExtra("nom")
                val user = intent.getStringExtra("user")
                val role = intent.getStringExtra("role")
                val intent = Intent(this, listeTacheActivity::class.java)
                intent.putExtra("user", user)
                intent.putExtra("role", role)
                intent.putExtra("nom", nom)
                startActivity(intent)
                //startActivity(Intent(this, listeTacheActivity::class.java))
            }
            .addOnFailureListener {
                Toast.makeText(this, " Data not updated ", Toast.LENGTH_LONG).show()
            }
        return
        }
    }

    public fun supTache(view: View) {
        var tache: String = findViewById<EditText>(R.id.nom_edit_text).text.toString()
        dbf.collection("Tache").document(tache).delete()
        Toast.makeText(this, "Data updated ", Toast.LENGTH_LONG).show()
        val nom = intent.getStringExtra("nom")
        val user = intent.getStringExtra("user")
        val role = intent.getStringExtra("role")
        val intent = Intent(this, listeTacheActivity::class.java)
        intent.putExtra("user", user)
        intent.putExtra("role", role)
        intent.putExtra("nom", nom)
        startActivity(intent)
        //startActivity(Intent(this, listeTacheActivity::class.java))
        return
    }
    public fun listeStatus(view: View) {
        var tache: String = findViewById<EditText>(R.id.nom_edit_text).text.toString()
        var namep: String = findViewById<EditText>(R.id.projet_edit_text).text.toString()
        var statusp: String = findViewById<EditText>(R.id.status_edit_text).text.toString()
        var datedp: String = findViewById<EditText>(R.id.editTextDateD).text.toString()
        var datefp: String = findViewById<EditText>(R.id.editTextDateF).text.toString()
        var descr1p: String = findViewById<EditText>(R.id.description1_edit_text).text.toString()
        var avance: String = findViewById<EditText>(R.id.editTextAvancementT).text.toString()
        var col: String = findViewById<EditText>(R.id.editTextCollab).text.toString()
        val nom = intent.getStringExtra("nom")
        val user = intent.getStringExtra("user")
        val role = intent.getStringExtra("role")
        val intent = Intent(this, listesttActivity::class.java)
        intent.putExtra("projet", namep)
        intent.putExtra("description1", descr1p)
        intent.putExtra("dated", datedp)
        intent.putExtra("datef", datefp)
        intent.putExtra("status", statusp)
        intent.putExtra("avancement", avance)
        intent.putExtra("tache", tache)
        intent.putExtra("activity", "majtacheactivity")
        intent.putExtra("col", col)
        intent.putExtra("user", user)
        intent.putExtra("role", role)
        intent.putExtra("nom", nom)
        startActivity(intent)
    }

    public fun listeAvance(view: View) {
        var tache: String = findViewById<EditText>(R.id.nom_edit_text).text.toString()
        var namep: String = findViewById<EditText>(R.id.projet_edit_text).text.toString()
        var statusp: String = findViewById<EditText>(R.id.status_edit_text).text.toString()
        var datedp: String = findViewById<EditText>(R.id.editTextDateD).text.toString()
        var datefp: String = findViewById<EditText>(R.id.editTextDateF).text.toString()
        var descr1p: String = findViewById<EditText>(R.id.description1_edit_text).text.toString()
        var avance: String = findViewById<EditText>(R.id.editTextAvancementT).text.toString()
        var col: String = findViewById<EditText>(R.id.editTextCollab).text.toString()
        val nom = intent.getStringExtra("nom")
        val user = intent.getStringExtra("user")
        val role = intent.getStringExtra("role")
        val intent = Intent(this, listeavtActivity::class.java)
        intent.putExtra("projet", namep)
        intent.putExtra("tache", tache)
        intent.putExtra("description1", descr1p)
        intent.putExtra("dated", datedp)
        intent.putExtra("datef", datefp)
        intent.putExtra("status", statusp)
        intent.putExtra("avancement", avance)
        intent.putExtra("activity", "majtacheactivity")
        intent.putExtra("col", col)
        intent.putExtra("user", user)
        intent.putExtra("role", role)
        intent.putExtra("nom", nom)
        startActivity(intent)
    }
    public fun listeProjet(view: View) {
        var tache: String = findViewById<EditText>(R.id.nom_edit_text).text.toString()
        var namep: String = findViewById<EditText>(R.id.projet_edit_text).text.toString()
        var statusp: String = findViewById<EditText>(R.id.status_edit_text).text.toString()
        var datedp: String = findViewById<EditText>(R.id.editTextDateD).text.toString()
        var datefp: String = findViewById<EditText>(R.id.editTextDateF).text.toString()
        var descr1p: String = findViewById<EditText>(R.id.description1_edit_text).text.toString()
        var avance: String = findViewById<EditText>(R.id.editTextAvancementT).text.toString()
        var col: String = findViewById<EditText>(R.id.editTextCollab).text.toString()
        val nom = intent.getStringExtra("nom")
        val user = intent.getStringExtra("user")
        val role = intent.getStringExtra("role")

        val intent = Intent(this, listeprtActivity::class.java)
        intent.putExtra("projet", namep)
        intent.putExtra("tache", tache)
        intent.putExtra("description1", descr1p)
        intent.putExtra("dated", datedp)
        intent.putExtra("datef", datefp)
        intent.putExtra("status", statusp)
        intent.putExtra("avancement", avance)
        intent.putExtra("activity", "majtacheactivity")
        intent.putExtra("col", col)
        intent.putExtra("user", user)
        intent.putExtra("role", role)
        intent.putExtra("nom", nom)
        startActivity(intent)
    }
    public fun listecol(view: View) {
        var tache: String = findViewById<EditText>(R.id.nom_edit_text).text.toString()
        var namep: String = findViewById<EditText>(R.id.projet_edit_text).text.toString()
        var status: String = findViewById<EditText>(R.id.status_edit_text).text.toString()
        var datedp: String = findViewById<EditText>(R.id.editTextDateD).text.toString()
        var datefp: String = findViewById<EditText>(R.id.editTextDateF).text.toString()
        var descr1p: String = findViewById<EditText>(R.id.description1_edit_text).text.toString()
        var avance: String = findViewById<EditText>(R.id.editTextAvancementT).text.toString()
        var col: String = findViewById<EditText>(R.id.editTextCollab).text.toString()
        val nom = intent.getStringExtra("nom")
        val user = intent.getStringExtra("user")
        val role = intent.getStringExtra("role")
        val intent = Intent(this, listecolActivity::class.java)
        intent.putExtra("projet", namep)
        intent.putExtra("tache", tache)
        intent.putExtra("description1", descr1p)
        intent.putExtra("dated", datedp)
        intent.putExtra("datef", datefp)
        intent.putExtra("status", status)
        intent.putExtra("avancement", avance)
        intent.putExtra("col", col)
        intent.putExtra("activity", "majtacheactivity")
        intent.putExtra("user", user)
        intent.putExtra("role", role)
        intent.putExtra("nom", nom)
        startActivity(intent)
    }
    public fun retourMenu(view:View)
    {
        var intent1 :Intent= getIntent()
        var user = intent1.getStringExtra("user").toString()
        var nom = intent1.getStringExtra("nom").toString()
        var role = intent1.getStringExtra("role").toString()
        val intent: Intent =  Intent(applicationContext, MenuTacheActivity::class.java)
        intent.putExtra("user", user)
        intent.putExtra("role", role)
        intent.putExtra("nom", nom)
        startActivity(intent)
        //startActivity(Intent(this, MenuTacheActivity::class.java))
    }
}