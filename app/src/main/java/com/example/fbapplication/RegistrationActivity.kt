package com.example.fbapplication
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class RegistrationActivity : AppCompatActivity() {
    lateinit var auth: FirebaseAuth
    private var dbf = Firebase.firestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        auth = FirebaseAuth.getInstance()
    }
    public fun registerUser(view: View) {

        var email: String = findViewById<EditText>(R.id.email_edit_text).text.toString()
        var password: String = findViewById<EditText>(R.id.password_edit_text).text.toString()
        var nom: String = findViewById<EditText>(R.id.editTextNom).text.toString()
        var prenom: String = findViewById<EditText>(R.id.editTextPrenom).text.toString()

        val user = HashMap<String, Any>()
        user["Email"] = email
        user["Nom"] = nom
        user["Prenom"] = prenom

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    addUser()
                    //dbf.collection("Collaborateur")
                    //   .document(nom)
                    //   .set(user)
                    //   .addOnSuccessListener {
                    //        Toast.makeText(this, "Data added ", Toast.LENGTH_LONG).show()
                    //     startActivity(Intent(this, LoginActivity::class.java))
                    //    }
                    //    .addOnFailureListener {
                    //        Toast.makeText(this, " Data not added ", Toast.LENGTH_LONG).show()
                    //    }
                    //    Toast.makeText(this, "Registration Successful", Toast.LENGTH_SHORT).show()
                    //    startActivity(Intent(this, LoginActivity::class.java))
                //} else {
                //    Toast.makeText(this, "An error occurred", Toast.LENGTH_SHORT).show()
                }
            }
    }
    public fun addUser(){
        val uid = FirebaseAuth.getInstance().currentUser!!.uid
        var email: String = findViewById<EditText>(R.id.email_edit_text).text.toString()
        var password: String = findViewById<EditText>(R.id.password_edit_text).text.toString()
        var nom: String = findViewById<EditText>(R.id.editTextNom).text.toString()
        var prenom: String = findViewById<EditText>(R.id.editTextPrenom).text.toString()
        val add = HashMap<String, Any>()
        add["UID"] = uid
        add["Nom"] = nom
        add["Prenom"] = prenom
        add["Email"] = email
        var idp : String = dbf.collection("Projet").id
        dbf.collection("Users")
            .document(nom).set(add)
            .addOnSuccessListener {
                Toast.makeText(this, "Data added ", Toast.LENGTH_LONG).show()
                val role = intent.getStringExtra("role")
                val nom = intent.getStringExtra("nom")
                val user = intent.getStringExtra("user")
                val intent: Intent =  Intent(applicationContext, MenuProjetActivity::class.java)
                intent.putExtra("role", role)
                intent.putExtra("nom", nom)
                intent.putExtra("user", user)
                startActivity(intent)

                //startActivity(Intent(this, MenuProjetActivity::class.java))
            }
            .addOnFailureListener {
                Toast.makeText(this, " Data not added ", Toast.LENGTH_LONG).show()
            }
        return
    }
}