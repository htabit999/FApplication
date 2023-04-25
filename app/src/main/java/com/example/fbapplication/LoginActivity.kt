package com.example.fbapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.fbapplication.models.Project
import com.example.fbapplication.models.Users
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {
    lateinit var auth: FirebaseAuth
    private var db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        auth = FirebaseAuth.getInstance()
    }

    public fun loginUser(view: View) {
        var email: String = findViewById<EditText>(R.id.login_email_edit_text).text.toString()
        var password: String = findViewById<EditText>(R.id.login_password_edit_text).text.toString()
        if (!email.isEmpty() || !password.isEmpty() )
        {
            var intent: Intent = Intent(applicationContext, MenuActivity::class.java)
            auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    intent.putExtra("user", email)
                    val user = FirebaseAuth.getInstance().currentUser!!.uid
                    val list=returnRole(user)
                } else {
                    Toast.makeText(this, "Unable to login. Check your input or try again later", Toast.LENGTH_SHORT).show()
                }
            }
        }
        else
        {
            Toast.makeText(this, "Merci de remplir les champs", Toast.LENGTH_SHORT).show()
        }
    }
    public fun gotoReg(view: View) {
        startActivity(Intent(this, RegisterActivity::class.java))
    }
    public fun returnRole(uid: String): ArrayList<Users> {
        var liste = ArrayList<Users>()
        db.collection("Users").whereEqualTo("UID", uid)
            .get()
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    for (document in it.result) {
                        liste.add(
                            Users(
                                document.data.getValue("Nom") as String,
                                document.data.getValue("Prenom") as String,
                                document.data.getValue("Email") as String,
                                document.data.getValue("UID") as String,
                                document.data.getValue("Role") as String
                            )
                        )
                        //Toast.makeText(this, document.data.getValue("Role") as String, Toast.LENGTH_SHORT).show()
                    }
                }
                else
                {
                    Toast.makeText(this, "User not found", Toast.LENGTH_SHORT).show()
                }
                //Toast.makeText(this, "Role =" + liste[0].ROLE , Toast.LENGTH_SHORT).show()
                if(liste[0].ROLE == "Administrateur") {
                    val intent: Intent = Intent(applicationContext, MenuChefActivity::class.java)
                        intent.putExtra("role", liste[0].ROLE)
                        intent.putExtra("nom", liste[0].NOM)
                        intent.putExtra("user", liste[0].UID)
                    startActivity(intent)
                }
                else {
                    if (liste[0].ROLE == "Chef de projet") {
                        val intent: Intent = Intent(applicationContext, MenuActivity::class.java)
                            intent.putExtra("role", liste[0].ROLE)
                            intent.putExtra("nom", liste[0].NOM)
                            intent.putExtra("user", liste[0].UID)
                        startActivity(intent)
                    } else {
                        if (liste[0].ROLE == "Collaborateur") {
                            val intent: Intent =
                                Intent(applicationContext, MenuColActivity::class.java)
                                intent.putExtra("role", liste[0].ROLE)
                                intent.putExtra("nom", liste[0].NOM)
                                intent.putExtra("user", liste[0].UID)
                            startActivity(intent)
                        }
                    }
                }
            }
            return liste
    }
}