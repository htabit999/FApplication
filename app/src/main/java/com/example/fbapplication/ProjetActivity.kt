package com.example.fbapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.fbapplication.BuildConfig.DEBUG
import com.example.fbapplication.models.Projet
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.firestore.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class ProjetActivity : AppCompatActivity() {
    private lateinit var db: DatabaseReference
    private lateinit var spinner: Spinner
    private var dbf = Firebase.firestore
    var auth: FirebaseAuth = FirebaseAuth.getInstance()
    lateinit var nomp: String

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_projet)
        val nprj = intent.getStringExtra("projet")
        val d1 = intent.getStringExtra("description1")
        val st = intent.getStringExtra("status")
        val dd = intent.getStringExtra("dated")
        val df = intent.getStringExtra("datef")
        val av = intent.getStringExtra("av")
        val ch = intent.getStringExtra("ch")
        val role = intent.getStringExtra("role")
        val nom = intent.getStringExtra("nom")
        val user = intent.getStringExtra("user")

        val prj = this.findViewById(R.id.nom_edit_text) as TextView
        val des1 = this.findViewById(R.id.description1_edit_text) as TextView
        val dtd = this.findViewById(R.id.editTextDateD) as TextView
        val dtf = this.findViewById(R.id.editTextDateF) as TextView
        val sta = this.findViewById(R.id.statusP_edit_text) as TextView
        val avc = this.findViewById(R.id.editTextAvc) as TextView
        val chef = this.findViewById(R.id.editTextChef) as TextView

        prj.text = nprj
        des1.text = d1
        dtd.text = dd
        dtf.text = df
        sta.text = st
        avc.text = av
        chef.text=ch
    }

    public fun addProjet(view: View) {
        val user = FirebaseAuth.getInstance().currentUser!!.uid
        var namep: String = findViewById<EditText>(R.id.nom_edit_text).text.toString()
        var statusp: String = findViewById<EditText>(R.id.statusP_edit_text).text.toString()
        var datedp: String = findViewById<EditText>(R.id.editTextDateD).text.toString()
        var datefp: String = findViewById<EditText>(R.id.editTextDateF).text.toString()
        var descr1p: String = findViewById<EditText>(R.id.description1_edit_text).text.toString()
        var avance: String = findViewById<EditText>(R.id.editTextAvc).text.toString()
        var chef: String = findViewById<EditText>(R.id.editTextChef).text.toString()

        if (namep.isEmpty() || statusp.isEmpty() || datedp.isEmpty() || datefp.isEmpty() || descr1p.isEmpty() || avance.isEmpty() || chef.isEmpty()) {
            Toast.makeText(this, "Il faut remplir tous les champs", Toast.LENGTH_SHORT).show()
        } else {
            if (legalDoB(datedp) && legalDoB(datefp)) {
                val add = HashMap<String, Any>()
                add["Projet"] = namep
                add["Description1"] = descr1p
                add["Status"] = statusp
                add["datedeb"] = datedp
                add["Avancement"] = avance
                add["datefin"] = datefp
                add["USERID"] = user
                add["Chef"]=chef
                var idp: String = dbf.collection("Projet").id
                dbf.collection("Projet")
                    .document(namep).set(add)
                    .addOnSuccessListener {
                        val role = intent.getStringExtra("role")
                        val nom = intent.getStringExtra("nom")
                        val user = intent.getStringExtra("user")
                        Toast.makeText(this, "Data added ", Toast.LENGTH_LONG).show()
                        val intent: Intent =  Intent(applicationContext, MenuCHProjetActivity::class.java)
                        intent.putExtra("role", role)
                        intent.putExtra("nom", nom)
                        intent.putExtra("user", user)
                        startActivity(intent)
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

    fun legalDoB(dobTextId: String): Boolean {
        val dobString = dobTextId.toString()
        val df = SimpleDateFormat("MM/dd/yy")
        try {
            val date: Date = df.parse(dobString)
            Log.d(DEBUG.toString(), "Legal Date $date")
            return true
        } catch (e: ParseException) {
            Log.d(DEBUG.toString(), "NOT Legal Date")
            return false
        }
        return false
    }

    public fun listeStatus(view: View) {
        var namep: String = findViewById<EditText>(R.id.nom_edit_text).text.toString()
        var statusp: String = findViewById<EditText>(R.id.statusP_edit_text).text.toString()
        var datedp: String = findViewById<EditText>(R.id.editTextDateD).text.toString()
        var datefp: String = findViewById<EditText>(R.id.editTextDateF).text.toString()
        var descr1p: String = findViewById<EditText>(R.id.description1_edit_text).text.toString()
        var avance: String = findViewById<EditText>(R.id.editTextAvc).text.toString()
        var chef: String = findViewById<EditText>(R.id.editTextChef).text.toString()
        val role = intent.getStringExtra("role")
        val nom = intent.getStringExtra("nom")
        val user = intent.getStringExtra("user")
        val intent = Intent(this, listeActivity::class.java)
        intent.putExtra("activity", "projet")
        intent.putExtra("projet", namep)
        intent.putExtra("description1", descr1p)
        intent.putExtra("dated", datedp)
        intent.putExtra("datef", datefp)
        intent.putExtra("status", statusp)
        intent.putExtra("av", avance)
        intent.putExtra("ch", chef)
        intent.putExtra("role", role)
        intent.putExtra("nom", nom)
        intent.putExtra("user", user)
        startActivity(intent)
    }

    public fun listeAvance(view: View) {
        var namep: String = findViewById<EditText>(R.id.nom_edit_text).text.toString()
        var statusp: String = findViewById<EditText>(R.id.statusP_edit_text).text.toString()
        var datedp: String = findViewById<EditText>(R.id.editTextDateD).text.toString()
        var datefp: String = findViewById<EditText>(R.id.editTextDateF).text.toString()
        var descr1p: String = findViewById<EditText>(R.id.description1_edit_text).text.toString()
        var avance: String = findViewById<EditText>(R.id.editTextAvc).text.toString()
        var chef: String = findViewById<EditText>(R.id.editTextChef).text.toString()
        val role = intent.getStringExtra("role")
        val nom = intent.getStringExtra("nom")
        val user = intent.getStringExtra("user")
        val intent = Intent(this, listeavActivity::class.java)
        intent.putExtra("activity", "projet")
        intent.putExtra("projet", namep)
        intent.putExtra("description1", descr1p)
        intent.putExtra("dated", datedp)
        intent.putExtra("datef", datefp)
        intent.putExtra("status", statusp)
        intent.putExtra("av", avance)
        intent.putExtra("ch", chef)
        intent.putExtra("role", role)
        intent.putExtra("nom", nom)
        intent.putExtra("user", user)
        startActivity(intent)
    }

    public fun listeChef(view: View) {
        var namep: String = findViewById<EditText>(R.id.nom_edit_text).text.toString()
        var statusp: String = findViewById<EditText>(R.id.statusP_edit_text).text.toString()
        var datedp: String = findViewById<EditText>(R.id.editTextDateD).text.toString()
        var datefp: String = findViewById<EditText>(R.id.editTextDateF).text.toString()
        var descr1p: String = findViewById<EditText>(R.id.description1_edit_text).text.toString()
        var avance: String = findViewById<EditText>(R.id.editTextAvc).text.toString()
        var chef: String = findViewById<EditText>(R.id.editTextChef).text.toString()
        val role = intent.getStringExtra("role")
        val nom = intent.getStringExtra("nom")
        val user = intent.getStringExtra("user")
        val intent = Intent(this, listeChefActivity::class.java)
        intent.putExtra("activity", "projet")
        intent.putExtra("projet", namep)
        intent.putExtra("description1", descr1p)
        intent.putExtra("dated", datedp)
        intent.putExtra("datef", datefp)
        intent.putExtra("status", statusp)
        intent.putExtra("av", avance)
        intent.putExtra("ch", chef)
        intent.putExtra("role", role)
        intent.putExtra("nom", nom)
        intent.putExtra("user", user)
        startActivity(intent)
    }

    public fun retourMenu(view:View)
    {
        val role = intent.getStringExtra("role")
        val nom = intent.getStringExtra("nom")
        val user = intent.getStringExtra("user")
        val intent: Intent =  Intent(applicationContext, MenuCHProjetActivity::class.java)
        intent.putExtra("role", role)
        intent.putExtra("nom", nom)
        intent.putExtra("user", user)
        startActivity(intent)
        //startActivity(Intent(this, MenuCHProjetActivity::class.java))
    }
}