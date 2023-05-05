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

class MenuActivity: AppCompatActivity() {
    private var db = Firebase.firestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        val intent = intent
        val role = intent.getStringExtra("role")
        val nom = intent.getStringExtra("nom")
        val user = intent.getStringExtra("user")
        //Toast.makeText(this, "Nom Chef 1: ", Toast.LENGTH_LONG).show()
    }
    public fun menuTache(view: View) {
        val intent1 :Intent= getIntent()
        val user = intent1.getStringExtra("user").toString()
        val nom = intent1.getStringExtra("nom").toString()
        val role = intent1.getStringExtra("role").toString()
        val intent: Intent =  Intent(applicationContext, MenuTacheActivity::class.java)
        intent.putExtra("role", role)
        intent.putExtra("nom", nom)
        intent.putExtra("user", user)
        startActivity(intent)
    }
    public fun menuProjet(view: View) {
        val intent1 :Intent= getIntent()
        val user = intent1.getStringExtra("user").toString()
        val nom = intent1.getStringExtra("nom").toString()
        val role = intent1.getStringExtra("role").toString()
        //Toast.makeText(this, "Nom Chef 2: "+nom, Toast.LENGTH_LONG).show()
        val intent: Intent =  Intent(applicationContext, MenuPProjetActivity::class.java)
        intent.putExtra("role", role)
        intent.putExtra("nom", nom)
        intent.putExtra("user", user)
        startActivity(intent)
    }
    public fun menuCollab(view: View) {
        val intent1 :Intent= getIntent()
        val user = intent1.getStringExtra("user").toString()
        val nom = intent1.getStringExtra("nom").toString()
        val role = intent1.getStringExtra("role").toString()
        val intent: Intent =  Intent(applicationContext, MenuCollabActivity::class.java)
        intent.putExtra("role", role)
        intent.putExtra("nom", nom)
        intent.putExtra("user", user)
        startActivity(intent)
     // startActivity(Intent(this, MenuCollabActivity::class.java))
    }
    public fun menuSuivi(view: View) {
        val intent1 :Intent= getIntent()
        val user = intent1.getStringExtra("user").toString()
        val nom = intent1.getStringExtra("nom").toString()
        val role = intent1.getStringExtra("role").toString()
        val intent: Intent =  Intent(applicationContext, menuSuiviActivity::class.java)
        intent.putExtra("role", role)
        intent.putExtra("nom", nom)
        intent.putExtra("user", user)
        startActivity(intent)
        //startActivity(Intent(this, menuSuiviActivity::class.java))
    }
    public fun Retour(view: View) {
        val intent1 :Intent= getIntent()
        val user = intent1.getStringExtra("user").toString()
        val nom = intent1.getStringExtra("nom").toString()
        val role = intent1.getStringExtra("role").toString()
        val intent: Intent =  Intent(applicationContext, LoginActivity::class.java)
        intent.putExtra("role", role)
        intent.putExtra("nom", nom)
        intent.putExtra("user", user)
        startActivity(intent)
      }

    public fun Chat(view: View) {
        val intent1 :Intent= getIntent()
        val user = intent1.getStringExtra("user").toString()
        val nom = intent1.getStringExtra("nom").toString()
        val role = intent1.getStringExtra("role").toString()
        val intent: Intent =  Intent(applicationContext, UserActivity::class.java)
        intent.putExtra("role", role)
        intent.putExtra("nom", nom)
        intent.putExtra("user", user)
        startActivity(intent)
    }
}