package com.example.fbapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.fbapplication.models.Project
import com.example.fbapplication.models.Projet
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class majCHProjetActivity : AppCompatActivity() {
    var db = FirebaseDatabase.getInstance().getReference("projet-4f405")
    private var dbf = Firebase.firestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maj_chprojet)
        val intent = intent
        val nprj = intent.getStringExtra("projet")
        val d1 = intent.getStringExtra("description1")
        val st = intent.getStringExtra("status")
        val dd = intent.getStringExtra("dated")
        val df = intent.getStringExtra("datef")
        val av = intent.getStringExtra("av")
        val ch = intent.getStringExtra("ch")

        val prj = this.findViewById(R.id.nom_edit_text) as TextView
        val des1 = this.findViewById(R.id.description1_edit_text) as TextView
        val dtd = this.findViewById(R.id.editTextDateD) as TextView
        val dtf = this.findViewById(R.id.editTextDateF) as TextView
        val sta = this.findViewById(R.id.statusP_edit_text) as TextView
        val avc = this.findViewById(R.id.editTextAvc) as TextView
        val chf = this.findViewById(R.id.editTextChef) as TextView

        prj.text = nprj
        des1.text = d1
        dtd.text = dd
        dtf.text = df
        sta.text = st
        avc.text = av
        chf.text=ch
    }

    public fun modProjet(view: View) {
        var id = intent.getStringExtra("id").toString()
        val user = FirebaseAuth.getInstance().currentUser!!.uid
        var namep: String = findViewById<EditText>(R.id.nom_edit_text).text.toString()
        var statusp: String = findViewById<EditText>(R.id.statusP_edit_text).text.toString()
        var datedp: String = findViewById<EditText>(R.id.editTextDateD).text.toString()
        var datefp: String = findViewById<EditText>(R.id.editTextDateF).text.toString()
        var descr1p: String = findViewById<EditText>(R.id.description1_edit_text).text.toString()
        var avance: String = findViewById<EditText>(R.id.editTextAvc).text.toString()
        var chef: String = findViewById<EditText>(R.id.editTextChef).text.toString()

        var iav = avance.toInt()

        if (namep.isEmpty() || statusp.isEmpty() || datedp.isEmpty() || datefp.isEmpty() || descr1p.isEmpty()  || avance.isEmpty()) {
            Toast.makeText(this, "Il faut remplir tous les champs", Toast.LENGTH_SHORT).show()
        } else {
            val projet = Project(namep, descr1p, datedp, datefp, statusp,  user,iav,chef)
            //Toast.makeText(this, id.toString(), Toast.LENGTH_SHORT).show()
            //
            db.child("Base").child("Projet").child(id).setValue(projet)
                .addOnCompleteListener {
                    Toast.makeText(this, "Data updated successfully", Toast.LENGTH_LONG).show()
                }.addOnFailureListener { err ->
                    Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
                }
            val role = intent.getStringExtra("role")
            val nom = intent.getStringExtra("nom")
            val user = intent.getStringExtra("user")
            val intent = Intent(this, DataActivity::class.java)
            intent.putExtra("role", role)
            intent.putExtra("nom", nom)
            intent.putExtra("user", user)
            startActivity(intent)//(this, DataActivity::class.java))
        }
    }

    public fun supProjet(view: View) {
        var id = intent.getStringExtra("id").toString()
        //db.child("Base").child("Projet").orderByChild("UUID").equalTo(id.toString())
        db.child("Base").child("Projet").child(id).removeValue();
        val role = intent.getStringExtra("role")
        val nom = intent.getStringExtra("nom")
        val user = intent.getStringExtra("user")
        val intent = Intent(this, DataActivity::class.java)
        intent.putExtra("role", role)
        intent.putExtra("nom", nom)
        intent.putExtra("user", user)
        startActivity(intent)

        //startActivity(Intent(this, DataActivity::class.java))
    }

    public fun modFSProjet(view: View) {
        val user = FirebaseAuth.getInstance().currentUser!!.uid
        var namep: String = findViewById<EditText>(R.id.nom_edit_text).text.toString()
        var statusp: String = findViewById<EditText>(R.id.statusP_edit_text).text.toString()
        var datedp: String = findViewById<EditText>(R.id.editTextDateD).text.toString()
        var datefp: String = findViewById<EditText>(R.id.editTextDateF).text.toString()
        var descr1p: String = findViewById<EditText>(R.id.description1_edit_text).text.toString()
        var avance: String = findViewById<EditText>(R.id.editTextAvc).text.toString()
        var chef: String = findViewById<EditText>(R.id.editTextChef).text.toString()

        if (namep.isEmpty() ||  statusp.isEmpty() || datedp.isEmpty() || datefp.isEmpty() || descr1p.isEmpty() || avance.isEmpty()|| chef.isEmpty()) {
            Toast.makeText(this, "Il faut remplir tous les champs", Toast.LENGTH_SHORT).show()
        } else {
            if (!legalDoB(datedp) || !legalDoB(datefp)) {
                Toast.makeText(this, "Date incorrecte", Toast.LENGTH_SHORT).show()
            } else {
                val projet = HashMap<String, Any>()
                projet["Projet"] = namep
                projet["Description1"] = descr1p
                projet["datedeb"] = datedp
                projet["datefin"] = datefp
                projet["Status"] = statusp
                projet["USERID"] = user
                projet["Avancement"] = avance
                projet["Chef"]=chef
                dbf.collection("Projet")
                    .document(namep).set(projet)
                    .addOnSuccessListener {
                        val role = intent.getStringExtra("role")
                        val nom = intent.getStringExtra("nom")
                        val user = intent.getStringExtra("user")
                        val intent = Intent(this, CHDataFSActivity::class.java)
                        Toast.makeText(this, "Data updated ", Toast.LENGTH_LONG).show()
                        intent.putExtra("role", role)
                        intent.putExtra("nom", nom)
                        intent.putExtra("user", user)
                        startActivity(intent)
                        //startActivity(Intent(this, CHDataFSActivity::class.java))
                    }
                    .addOnFailureListener {
                        Toast.makeText(this, " Data not updated ", Toast.LENGTH_LONG).show()
                    }
                return
            }
        }
    }
    public fun supFSProjet(view: View) {
        var namep: String = findViewById<EditText>(R.id.nom_edit_text).text.toString()
        dbf.collection("Projet").document(namep).delete()
        Toast.makeText(this, "Data updated ", Toast.LENGTH_LONG).show()
        val role = intent.getStringExtra("role")
        val nom = intent.getStringExtra("nom")
        val user = intent.getStringExtra("user")
        val intent = Intent(this, DataFSActivity::class.java)
        intent.putExtra("role", role)
        intent.putExtra("nom", nom)
        intent.putExtra("user", user)
        startActivity(intent)
        //startActivity(Intent(this, DataFSActivity::class.java))
        return
    }

    fun legalDoB(dobTextId: String): Boolean {
        val dobString = dobTextId.toString()
        val df = SimpleDateFormat("MM/dd/yy")
        try {
            val date: Date = df.parse(dobString)
            Log.d(BuildConfig.DEBUG.toString(), "Legal Date $date")
            return true
        } catch (e: ParseException) {
            Log.d(BuildConfig.DEBUG.toString(), "Not Legal Date")
            return false
        }
        return false
    }
    public fun retourMenu(view:View)
    {
        val role = intent.getStringExtra("role")
        val nom = intent.getStringExtra("nom")
        val user = intent.getStringExtra("user")
        val intent = Intent(this, CHDataFSActivity::class.java)
        intent.putExtra("role", role)
        intent.putExtra("nom", nom)
        intent.putExtra("user", user)
        startActivity(intent)
        //startActivity(Intent(this, Menu CHProjetActivity::class.java))
    }
    public fun listeStatus(view: View) {
        val role = intent.getStringExtra("role")
        val nom = intent.getStringExtra("nom")
        val user = intent.getStringExtra("user")
        var namep: String = findViewById<EditText>(R.id.nom_edit_text).text.toString()
        var statusp: String = findViewById<EditText>(R.id.statusP_edit_text).text.toString()
        var datedp: String = findViewById<EditText>(R.id.editTextDateD).text.toString()
        var datefp: String = findViewById<EditText>(R.id.editTextDateF).text.toString()
        var descr1p: String = findViewById<EditText>(R.id.description1_edit_text).text.toString()
        var avance: String = findViewById<EditText>(R.id.editTextAvc).text.toString()
        var chef: String = findViewById<EditText>(R.id.editTextChef).text.toString()

        val intent = Intent(this, listeActivity::class.java)
        intent.putExtra("activity", "majchprojet")
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
        val role = intent.getStringExtra("role")
        val nom = intent.getStringExtra("nom")
        val user = intent.getStringExtra("user")
        var namep: String = findViewById<EditText>(R.id.nom_edit_text).text.toString()
        var statusp: String = findViewById<EditText>(R.id.statusP_edit_text).text.toString()
        var datedp: String = findViewById<EditText>(R.id.editTextDateD).text.toString()
        var datefp: String = findViewById<EditText>(R.id.editTextDateF).text.toString()
        var descr1p: String = findViewById<EditText>(R.id.description1_edit_text).text.toString()
        var avance: String = findViewById<EditText>(R.id.editTextAvc).text.toString()
        var chef: String = findViewById<EditText>(R.id.editTextChef).text.toString()
        val intent = Intent(this, listeavActivity::class.java)
        intent.putExtra("activity", "majchprojet")
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
        val role = intent.getStringExtra("role")
        val nom = intent.getStringExtra("nom")
        val user = intent.getStringExtra("user")
        var namep: String = findViewById<EditText>(R.id.nom_edit_text).text.toString()
        var statusp: String = findViewById<EditText>(R.id.statusP_edit_text).text.toString()
        var datedp: String = findViewById<EditText>(R.id.editTextDateD).text.toString()
        var datefp: String = findViewById<EditText>(R.id.editTextDateF).text.toString()
        var descr1p: String = findViewById<EditText>(R.id.description1_edit_text).text.toString()
        var avance: String = findViewById<EditText>(R.id.editTextAvc).text.toString()
        var chef: String = findViewById<EditText>(R.id.editTextChef).text.toString()
        val intent = Intent(this, listeChefActivity::class.java)
        intent.putExtra("activity", "majchprojet")
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
}

