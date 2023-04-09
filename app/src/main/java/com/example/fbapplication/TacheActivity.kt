package com.example.fbapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.fbapplication.models.Tache
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class TacheActivity : AppCompatActivity() {
    var db = FirebaseDatabase.getInstance().getReference("projet-4f405")
    private var dbf = Firebase.firestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tache).toString()
        val projet = intent.getStringExtra("projet")
        val tache = intent.getStringExtra("tache")
        val d1 = intent.getStringExtra("description1")
        val st = intent.getStringExtra("status")
        val dd = intent.getStringExtra("dated")
        val df = intent.getStringExtra("datef")
        val av = intent.getStringExtra("avancement")
        val cl = intent.getStringExtra("col")
        val nom = intent.getStringExtra("nom")
        val tch = this.findViewById(R.id.nom_edit_text) as TextView
        val prj = this.findViewById(R.id.projet_edit_text) as TextView
        val des1 = this.findViewById(R.id.description1_edit_text) as TextView
        val dtd = this.findViewById(R.id.editTextDateD) as TextView
        val dtf = this.findViewById(R.id.editTextDateF) as TextView
        val sta = this.findViewById(R.id.status_edit_text) as TextView
        val avc = this.findViewById(R.id.editTextAvancementT) as TextView
        val col = this.findViewById(R.id.editTextCollab) as TextView

        prj.text = projet
        des1.text = d1
        dtd.text = dd
        dtf.text = df
        sta.text = st
        avc.text = av
        tch.text=tache
        col.text=cl
    }

    public fun addTache(view: View)
    {
        var idt : String = db.push().key!!
        var name: String = findViewById<EditText>(R.id.nom_edit_text).text.toString()
        var user = FirebaseAuth.getInstance().currentUser!!.uid
        var status: String = findViewById<EditText>(R.id.status_edit_text).text.toString()
        var dated: String = findViewById<EditText>(R.id.editTextDateD).text.toString()
        var datef: String = findViewById<EditText>(R.id.editTextDateF).text.toString()
        var descr1: String = findViewById<EditText>(R.id.description1_edit_text).text.toString()
        var avance: String = findViewById<EditText>(R.id.editTextAvancementT).text.toString()
        var projet: String = findViewById<EditText>(R.id.projet_edit_text).text.toString()
        var collab: String = findViewById<EditText>(R.id.editTextCollab).text.toString()

        if (name.isEmpty() ||  status.isEmpty() || dated.isEmpty() || datef.isEmpty() || descr1.isEmpty() || status.isEmpty() || avance.isEmpty() || projet.isEmpty() || collab.isEmpty() )
        {
            Toast.makeText(this, "Il faut remplir tous les champs", Toast.LENGTH_SHORT).show()
        }
        else
        {
            if (legalDoB(dated) && legalDoB(datef)) {
                val add = HashMap<String, Any>()
                add["Tache"] = name
                add["Description1"] = descr1
                add["Status"] = status
                add["datedeb"] = dated
                add["Avancement"] = avance
                add["datefin"] = datef
                add["USERID"] = user
                add["Projet"] = projet
                add["Collaborateur"] = collab

                var idp: String = dbf.collection("Projet").id
                dbf.collection("Tache")
                    .document(name).set(add)
                    .addOnSuccessListener {
                        Toast.makeText(this, "Data added ", Toast.LENGTH_LONG).show()
                        var nom = intent.getStringExtra("nom").toString()
                        val intent: Intent =  Intent(applicationContext, MenuTacheActivity::class.java)
                        intent.putExtra("nom", nom)
                        startActivity(intent)
                        //startActivity(Intent(this, MenuTacheActivity::class.java))
                    }
                    .addOnFailureListener {
                        Toast.makeText(this, " Data not added ", Toast.LENGTH_LONG).show()
                    }
                return
            }
            else
            {
                Toast.makeText(this, "Date incorrecte ", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun legalDoB(dobTextId: String): Boolean
    {
        val dobString = dobTextId.toString()
        val df = SimpleDateFormat("MM/dd/yy")
        try
        {
            val date: Date = df.parse(dobString)
            Log.d(BuildConfig.DEBUG.toString(), "Legal Date $date")
            return true
        } catch (e: ParseException) {
            Log.d(BuildConfig.DEBUG.toString(), "NOT Legal Date")
            return false
        }
        return false
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
        val intent = Intent(this, listesttActivity::class.java)
        intent.putExtra("projet", namep)
        intent.putExtra("description1", descr1p)
        intent.putExtra("dated", datedp)
        intent.putExtra("datef", datefp)
        intent.putExtra("status", statusp)
        intent.putExtra("avancement", avance)
        intent.putExtra("tache", tache)
        intent.putExtra("col", col)
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
        val intent = Intent(this, listeavtActivity::class.java)
        intent.putExtra("projet", namep)
        intent.putExtra("tache", tache)
        intent.putExtra("description1", descr1p)
        intent.putExtra("dated", datedp)
        intent.putExtra("datef", datefp)
        intent.putExtra("status", statusp)
        intent.putExtra("avancement", avance)
        intent.putExtra("col", col)
        intent.putExtra("nom", nom)
        startActivity(intent)
    }
    public fun listeProjet(view: View) {
        val nom = intent.getStringExtra("nom")
        var tache: String = findViewById<EditText>(R.id.nom_edit_text).text.toString()
        var namep: String = findViewById<EditText>(R.id.projet_edit_text).text.toString()
        var status: String = findViewById<EditText>(R.id.status_edit_text).text.toString()
        var datedp: String = findViewById<EditText>(R.id.editTextDateD).text.toString()
        var datefp: String = findViewById<EditText>(R.id.editTextDateF).text.toString()
        var descr1p: String = findViewById<EditText>(R.id.description1_edit_text).text.toString()
        var avance: String = findViewById<EditText>(R.id.editTextAvancementT).text.toString()
        var col: String = findViewById<EditText>(R.id.editTextCollab).text.toString()
        val intent = Intent(this, listeprtActivity::class.java)
        intent.putExtra("projet", namep)
        intent.putExtra("tache", tache)
        intent.putExtra("description1", descr1p)
        intent.putExtra("dated", datedp)
        intent.putExtra("datef", datefp)
        intent.putExtra("status", status)
        intent.putExtra("avancement", avance)
        intent.putExtra("col", col)
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
    public fun listeCollab(view: View) {
        val nom = intent.getStringExtra("nom")
        var tache: String = findViewById<EditText>(R.id.nom_edit_text).text.toString()
        var namep: String = findViewById<EditText>(R.id.projet_edit_text).text.toString()
        var status: String = findViewById<EditText>(R.id.status_edit_text).text.toString()
        var datedp: String = findViewById<EditText>(R.id.editTextDateD).text.toString()
        var datefp: String = findViewById<EditText>(R.id.editTextDateF).text.toString()
        var descr1p: String = findViewById<EditText>(R.id.description1_edit_text).text.toString()
        var avance: String = findViewById<EditText>(R.id.editTextAvancementT).text.toString()
        var col: String = findViewById<EditText>(R.id.editTextCollab).text.toString()
        val intent = Intent(this, listecolActivity::class.java)
        intent.putExtra("projet", namep)
        intent.putExtra("tache", tache)
        intent.putExtra("description1", descr1p)
        intent.putExtra("dated", datedp)
        intent.putExtra("datef", datefp)
        intent.putExtra("status", status)
        intent.putExtra("avancement", avance)
        intent.putExtra("col", col)
        intent.putExtra("activity", "tache")
        intent.putExtra("nom", nom)
        startActivity(intent)
    }
}