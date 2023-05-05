package com.example.fbapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MenuChefProjetActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_chef_projet)
    }
    public fun addChefProjet(view: View) {
        //val intent = intent
        val role = intent.getStringExtra("role")
        val nom = intent.getStringExtra("nom")
        val user = intent.getStringExtra("user")
        val intent: Intent =  Intent(applicationContext, ChefProjetActivity::class.java)
        intent.putExtra("role", role)
        intent.putExtra("nom", nom)
        intent.putExtra("user", user)
        startActivity(intent)
        //startActivity(Intent(this, ChefProjetActivity::class.java))
    }
    public fun listeChefProjet(view: View) {
        //val intent = intent
        val role = intent.getStringExtra("role")
        val nom = intent.getStringExtra("nom")
        val user = intent.getStringExtra("user")
        val intent: Intent =  Intent(applicationContext, ListeChefProjetActivity::class.java)
        intent.putExtra("role", role)
        intent.putExtra("nom", nom)
        intent.putExtra("user", user)
        startActivity(intent)
        //var intent1 :Intent= getIntent()
        //var user = intent1.getStringExtra("user").toString()
        //val intent: Intent =  Intent(applicationContext,ListeChefProjetActivity::class.java)
        //intent.putExtra("user", user)
        //startActivity(intent)
    }

    public fun Retour(view: View) {
        //val intent = intent
        val role = intent.getStringExtra("role")
        val nom = intent.getStringExtra("nom")
        val user = intent.getStringExtra("user")
        val intent: Intent =  Intent(applicationContext, MenuChefActivity::class.java)
        intent.putExtra("role", role)
        intent.putExtra("nom", nom)
        intent.putExtra("user", user)
        startActivity(intent)
        //startActivity(Intent(this, MenuChefActivity::class.java))
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