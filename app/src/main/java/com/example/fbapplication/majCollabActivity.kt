package com.example.fbapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.fbapplication.models.Collaborateur
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class majCollabActivity : AppCompatActivity() {
    private var db = Firebase.firestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maj_collab)
        //auth = FirebaseAuth.getInstance()
        val role=intent.getStringExtra("role")
        val nom =intent.getStringExtra("nomc")
        val prenom=intent.getStringExtra("prenom")
        val email=intent.getStringExtra("email")
        val psw = intent.getStringExtra("psw")
        val rpsw=intent.getStringExtra("rpsw")
        val rl = this.findViewById(R.id.role_edit_text) as TextView
        val nm = this.findViewById(R.id.editTextNom) as TextView
        val pr = this.findViewById(R.id.editTextPrenom) as TextView
        val em = this.findViewById(R.id.email_edit_text) as TextView
        val ps = this.findViewById(R.id.password_edit_text) as TextView
        val rp = this.findViewById(R.id.re_password_edit_text) as TextView
        rl.text = "Collaborateur"
        nm.text=nom
        pr.text=prenom
        em.text = email
        ps.text=psw
        rp.text=rpsw
    }

    public fun majCollab(view: View){
        val uid = FirebaseAuth.getInstance().currentUser!!.uid
        var email: String = findViewById<EditText>(R.id.email_edit_text).text.toString()
        var password: String = findViewById<EditText>(R.id.password_edit_text).text.toString()
        var nom: String = findViewById<EditText>(R.id.editTextNom).text.toString()
        var prenom: String = findViewById<EditText>(R.id.editTextPrenom).text.toString()
        //var role: String = findViewById<EditText>(R.id.role_edit_text).text.toString()
        if (nom.isEmpty() ||  email.isEmpty() || prenom.isEmpty() )
        {
            Toast.makeText(this, "Merci de remplir les champs", Toast.LENGTH_LONG).show()

        }
        else {
            val add = HashMap<String, Any>()
            add["UID"] = uid
            add["Nom"] = nom
            add["Prenom"] = prenom
            add["Email"] = email
            add["Role"] = "Collaborateur"
            var idp: String = db.collection("Projet").id
            db.collection("Users")
                .document(nom).set(add)
                .addOnSuccessListener {
                    Toast.makeText(this, "Data added ", Toast.LENGTH_LONG).show()
                    var intent1 :Intent= getIntent()
                    var user = intent1.getStringExtra("user").toString()
                    var nom = intent1.getStringExtra("nom").toString()
                    var role = intent1.getStringExtra("role").toString()
                    val intent: Intent =  Intent(applicationContext, MenuCollabActivity::class.java)
                    intent.putExtra("user", user)
                    intent.putExtra("role", role)
                    intent.putExtra("nom", nom)
                    startActivity(intent)
                }
                .addOnFailureListener {
                    Toast.makeText(this, " Data not added ", Toast.LENGTH_LONG).show()
                }
            return
        }
    }

    public fun retour(view: View)
    {
        var intent1 :Intent= getIntent()
        var user = intent1.getStringExtra("user").toString()
        var nom = intent1.getStringExtra("nom").toString()
        var role = intent1.getStringExtra("role").toString()
        val intent: Intent =  Intent(applicationContext, MenuCollabActivity::class.java)
        intent.putExtra("user", user)
        intent.putExtra("role", role)
        intent.putExtra("nom", nom)
        startActivity(intent)
    }
}