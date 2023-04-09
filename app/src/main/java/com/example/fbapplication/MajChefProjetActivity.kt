package com.example.fbapplication
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MajChefProjetActivity : AppCompatActivity() {
    lateinit var auth: FirebaseAuth
    private var dbf = Firebase.firestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maj_chef_projet)
        auth = FirebaseAuth.getInstance()
        val rl=intent.getStringExtra("rl")
        val name =intent.getStringExtra("name")
        val prenom=intent.getStringExtra("prenom")
        val email=intent.getStringExtra("email")
        val psw = intent.getStringExtra("psw")
        val rpsw=intent.getStringExtra("rpsw")
        //val rl = this.findViewById(R.id.role_edit_text) as TextView
        val nm = this.findViewById(R.id.editTextNom) as TextView
        val pr = this.findViewById(R.id.editTextPrenom) as TextView
        val em = this.findViewById(R.id.email_edit_text) as TextView
        nm.text=name
        pr.text=prenom
        em.text = email
    }

    public fun supUser(view: View) {
        var user: String = findViewById<EditText>(R.id.editTextNom).text.toString()
        dbf.collection("Users").document(user).delete()
        Toast.makeText(this, "Data updated ", Toast.LENGTH_LONG).show()
        val role = intent.getStringExtra("role")
        val nom = intent.getStringExtra("nom")
        //val user = intent.getStringExtra("user")
        val intent: Intent =  Intent(applicationContext, ListeChefProjetActivity::class.java)
        intent.putExtra("role", role)
        intent.putExtra("nom", nom)
        intent.putExtra("user", user)
        startActivity(intent)
        //startActivity(Intent(this, ListeChefProjetActivity::class.java))
        return
    }

    public fun majUser(view: View){
        val uid = FirebaseAuth.getInstance().currentUser!!.uid
        var email: String = findViewById<EditText>(R.id.email_edit_text).text.toString()
        var nom: String = findViewById<EditText>(R.id.editTextNom).text.toString()
        var prenom: String = findViewById<EditText>(R.id.editTextPrenom).text.toString()
        if (email.isEmpty() ||  nom.isEmpty() ||  prenom.isEmpty() )
        {
            Toast.makeText(this, "Il faut remplir tous les champs", Toast.LENGTH_SHORT).show()
        }
        else {
            val add = HashMap<String, Any>()
            add["UID"] = uid
            add["Nom"] = nom
            add["Prenom"] = prenom
            add["Email"] = email
            add["Role"] = "Chef de projet"
            var idp: String = dbf.collection("Projet").id
            dbf.collection("Users")
                .document(nom).set(add)
                .addOnSuccessListener {
                    Toast.makeText(this, "Data updated ", Toast.LENGTH_LONG).show()
                    //val intent = intent
                    val role = intent.getStringExtra("role")
                    val nom = intent.getStringExtra("nom")
                    val user = intent.getStringExtra("user")
                    val intent: Intent =  Intent(applicationContext, MenuChefProjetActivity::class.java)
                    intent.putExtra("role", role)
                    intent.putExtra("nom", nom)
                    intent.putExtra("user", user)
                    startActivity(intent)
                   // startActivity(Intent(this, MenuChefProjetActivity::class.java))
                }
                .addOnFailureListener {
                    Toast.makeText(this, " Data not updated ", Toast.LENGTH_LONG).show()
                }
            return
        }
    }
    public fun retour(view: View)
    {
        //val intent = intent
        val role = intent.getStringExtra("role")
        val nom = intent.getStringExtra("nom")
        val user = intent.getStringExtra("user")
        val intent: Intent =  Intent(applicationContext, ListeChefProjetActivity::class.java)
        intent.putExtra("role", role)
        intent.putExtra("nom", nom)
        intent.putExtra("user", user)
        startActivity(intent)
        //startActivity(Intent(this, ListeChefProjetActivity::class.java))
    }
}