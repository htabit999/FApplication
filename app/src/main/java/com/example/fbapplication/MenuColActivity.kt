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

class MenuColActivity : AppCompatActivity() {
    private var db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_col)
        val intent = intent
        val role = intent.getStringExtra("role")
        val nom = intent.getStringExtra("nom")
        val user = intent.getStringExtra("user")
    }

    public fun mestaches(view: View) {
        // var intent1 :Intent= getIntent()
        val role = intent.getStringExtra("role")
        val nom = intent.getStringExtra("nom")
        val user = intent.getStringExtra("user")
        val intent: Intent =  Intent(applicationContext, listeTacheColActivity::class.java)
        intent.putExtra("role", role)
        intent.putExtra("nom", nom)
        intent.putExtra("user", user)
        startActivity(intent)
    }

    public fun suivitache(view: View) {
        //var intent1 :Intent= getIntent()
        val role = intent.getStringExtra("role")
        val nom = intent.getStringExtra("nom")
        val user = intent.getStringExtra("user")
        val intent: Intent =  Intent(applicationContext, SuiviTacheColActivity::class.java)
        intent.putExtra("role", role)
        intent.putExtra("nom", nom)
        intent.putExtra("user", user)
        startActivity(intent)
    }

    public fun Retour(view: View) {
        val role = intent.getStringExtra("role")
        val nom = intent.getStringExtra("nom")
        val user = intent.getStringExtra("user")
        val intent: Intent =  Intent(applicationContext, LoginActivity::class.java)
        //startActivity(Intent(this, MainActivity::class.java))
        intent.putExtra("role", role)
        intent.putExtra("nom", nom)
        intent.putExtra("user", user)
        startActivity(intent)
    }
    public fun Chat(view: View) {
        //Toast.makeText(this, "chat", Toast.LENGTH_LONG).show()
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