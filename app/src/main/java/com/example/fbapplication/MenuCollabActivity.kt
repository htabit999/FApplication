package com.example.fbapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.example.fbapplication.models.Users
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MenuCollabActivity : AppCompatActivity()
{
    private var db = Firebase.firestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_collab)
        var intent1 :Intent= getIntent()
        //var user = intent1.getStringExtra("user").toString()
        var nom = intent1.getStringExtra("nom").toString()
        var role = intent1.getStringExtra("role").toString()
        var list = ArrayList<Users>()
        val user = FirebaseAuth.getInstance().currentUser!!.uid
        db.collection("Users").whereEqualTo("UID", user)
            .get()
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    for (document in it.result) {
                        list.add(
                            Users(
                                document.data.getValue("Nom") as String,
                                document.data.getValue("Prenom") as String,
                                document.data.getValue("Email") as String,
                                document.data.getValue("UID") as String,
                                document.data.getValue("Role") as String
                            )
                        )
                    }
                } else {
                    Toast.makeText(this, "Utilisateur non trouve", Toast.LENGTH_SHORT).show()
                }

            }
    }
    public fun addCollab(view: View) {
        var intent1 :Intent= getIntent()
        var user = intent1.getStringExtra("user").toString()
        var nom = intent1.getStringExtra("nom").toString()
        var role = intent1.getStringExtra("role").toString()
        val intent: Intent =  Intent(applicationContext, CollabActivity::class.java)
        intent.putExtra("user", user)
        intent.putExtra("role", role)
        intent.putExtra("nom", nom)
        startActivity(intent)
        //startActivity(Intent(this, CollabActivity::class.java))
    }
    public fun listeCollab(view: View) {
        var intent1 :Intent= getIntent()
        var user = intent1.getStringExtra("user").toString()
        var nom = intent1.getStringExtra("nom").toString()
        var role = intent1.getStringExtra("role").toString()
        val intent: Intent =  Intent(applicationContext, lCollabActivity::class.java)
        intent.putExtra("user", user)
        intent.putExtra("role", role)
        intent.putExtra("nom", nom)
        startActivity(intent)
        //startActivity(Intent(this, lCollabActivity::class.java))
    }
    public fun suppCollab(view: View) {
        var intent1 :Intent= getIntent()
        var user = intent1.getStringExtra("user").toString()
        var nom = intent1.getStringExtra("nom").toString()
        var role = intent1.getStringExtra("role").toString()
        val intent: Intent =  Intent(applicationContext, CollabActivity::class.java)
        intent.putExtra("user", user)
        intent.putExtra("role", role)
        intent.putExtra("nom", nom)
        startActivity(intent)
        //startActivity(Intent(this, CollabActivity::class.java))
    }
    public fun modCollab(view: View) {
        var intent1 :Intent= getIntent()
        var user = intent1.getStringExtra("user").toString()
        var nom = intent1.getStringExtra("nom").toString()
        var role = intent1.getStringExtra("role").toString()
        val intent: Intent =  Intent(applicationContext, CollabActivity::class.java)
        intent.putExtra("user", user)
        intent.putExtra("role", role)
        intent.putExtra("nom", nom)
        startActivity(intent)
        //startActivity(Intent(this, CollabActivity::class.java))
    }
    public fun retour(view: View)
    {
        var intent1 :Intent= getIntent()
        var user = intent1.getStringExtra("user").toString()
        var nom = intent1.getStringExtra("nom").toString()
        var role = intent1.getStringExtra("role").toString()
        val intent: Intent =  Intent(applicationContext, MenuActivity::class.java)
        intent.putExtra("user", user)
        intent.putExtra("role", role)
        intent.putExtra("nom", nom)
        startActivity(intent)
        //startActivity(Intent(this, MenuActivity::class.java))
    }
}