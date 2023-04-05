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

class CollabActivity : AppCompatActivity() {
    lateinit var auth: FirebaseAuth
    private var dbf = Firebase.firestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_collab)
        auth = FirebaseAuth.getInstance()
        val role=intent.getStringExtra("role")
        val nom =intent.getStringExtra("nom")
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
        rl.text = role
        nm.text=nom
        pr.text=prenom
        em.text = email
        ps.text=psw
        rp.text=rpsw
    }
    public fun registerUser(view: View) {
        var email: String = findViewById<EditText>(R.id.email_edit_text).text.toString()
        var password: String = findViewById<EditText>(R.id.password_edit_text).text.toString()
        var nom: String = findViewById<EditText>(R.id.editTextNom).text.toString()
        var prenom: String = findViewById<EditText>(R.id.editTextPrenom).text.toString()
        var role: String = findViewById<EditText>(R.id.role_edit_text).text.toString()
        val user = HashMap<String, Any>()
        user["Email"] = email
        user["Nom"] = nom
        user["Prenom"] = prenom
        user["Role"] = role
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "User added ", Toast.LENGTH_LONG).show()
                    addUser()
                }
                else
                {
                    Toast.makeText(this, "User not added ", Toast.LENGTH_LONG).show()
                }
            }
    }
    public fun addUser(){
        val uid = FirebaseAuth.getInstance().currentUser!!.uid
        var email: String = findViewById<EditText>(R.id.email_edit_text).text.toString()
        var password: String = findViewById<EditText>(R.id.password_edit_text).text.toString()
        var nom: String = findViewById<EditText>(R.id.editTextNom).text.toString()
        var prenom: String = findViewById<EditText>(R.id.editTextPrenom).text.toString()
        var role: String = findViewById<EditText>(R.id.role_edit_text).text.toString()
        val add = HashMap<String, Any>()
        add["UID"] = uid
        add["Nom"] = nom
        add["Prenom"] = prenom
        add["Email"] = email
        add["Role"] = role
        var idp : String = dbf.collection("Projet").id
        dbf.collection("Users")
            .document(nom).set(add)
            .addOnSuccessListener {
                Toast.makeText(this, "Data added ", Toast.LENGTH_LONG).show()
                startActivity(Intent(this, MenuCollabActivity::class.java))
            }
            .addOnFailureListener {
                Toast.makeText(this, " Data not added ", Toast.LENGTH_LONG).show()
            }
        return
    }

    public fun listeRole(view: View) {
        var nom: String = findViewById<EditText>(R.id.editTextNom).text.toString()
        var prenom: String = findViewById<EditText>(R.id.editTextPrenom).text.toString()
        var email: String = findViewById<EditText>(R.id.email_edit_text).text.toString()
        var psw: String = findViewById<EditText>(R.id.password_edit_text).text.toString()
        var rpsw: String = findViewById<EditText>(R.id.re_password_edit_text).text.toString()
        val intent = Intent(this, listeroleActivity::class.java)
        intent.putExtra("nom", nom)
        intent.putExtra("prenom", prenom)
        intent.putExtra("email", email)
        intent.putExtra("psw", psw)
        intent.putExtra("rpsw", rpsw)
        intent.putExtra("activity", "collabactivity")
        startActivity(intent)
    }
}