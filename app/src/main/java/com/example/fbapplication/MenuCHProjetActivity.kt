package com.example.fbapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

class MenuCHProjetActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_chprojet)
    }
    public fun addProjet(view: View) {
        var intent1 :Intent= getIntent()
        var user = intent1.getStringExtra("user").toString()
        var nom = intent1.getStringExtra("nom").toString()
        var role = intent1.getStringExtra("role").toString()
        val intent: Intent =  Intent(applicationContext, ProjetActivity::class.java)
        intent.putExtra("user", user)
        intent.putExtra("role", role)
        intent.putExtra("nom", nom)
        startActivity(intent)
        //startActivity(Intent(this, ProjetActivity::class.java))
    }
    public fun listeProjet(view: View) {
        //Toast.makeText(this, "liste projet ", Toast.LENGTH_LONG).show()
        var intent1 :Intent= getIntent()
        var user = intent1.getStringExtra("user").toString()
        var nom = intent1.getStringExtra("nom").toString()
        var role = intent1.getStringExtra("role").toString()
        val intent: Intent =  Intent(applicationContext, CHDataFSActivity::class.java)
        intent.putExtra("user", user)
        intent.putExtra("role", role)
        intent.putExtra("nom", nom)
        startActivity(intent)
    }
    public fun suppProjet(view: View) {
        var intent1 :Intent= getIntent()
        var user = intent1.getStringExtra("user").toString()
        var nom = intent1.getStringExtra("nom").toString()
        var role = intent1.getStringExtra("role").toString()
        val intent: Intent =  Intent(applicationContext, CHDataFSActivity::class.java)
        intent.putExtra("user", user)
        intent.putExtra("role", role)
        intent.putExtra("nom", nom)
        startActivity(intent)

        startActivity(Intent(this, DataActivity::class.java))
    }
    public fun modProjet(view: View) {
        var intent1 :Intent= getIntent()
        var user = intent1.getStringExtra("user").toString()
        var nom = intent1.getStringExtra("nom").toString()
        var role = intent1.getStringExtra("role").toString()
        val intent: Intent =  Intent(applicationContext, DataActivity::class.java)
        intent.putExtra("user", user)
        intent.putExtra("role", role)
        intent.putExtra("nom", nom)
        startActivity(intent)
        //startActivity(Intent(this, DataActivity::class.java))
    }
    public fun Retour(view: View) {
        var intent1 :Intent= getIntent()
        var user = intent1.getStringExtra("user").toString()
        var nom = intent1.getStringExtra("nom").toString()
        var role = intent1.getStringExtra("role").toString()
        val intent: Intent =  Intent(applicationContext, MenuChefActivity::class.java)
        intent.putExtra("user", user)
        intent.putExtra("role", role)
        intent.putExtra("nom", nom)
        startActivity(intent)
        //startActivity(Intent(this, MenuActivity::class.java))
    }
}