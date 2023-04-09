package com.example.fbapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

class MenuProjetActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_projet)
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
            var intent1 :Intent= getIntent()
            var user = intent1.getStringExtra("user").toString()
            var nom = intent1.getStringExtra("nom").toString()
            var role = intent1.getStringExtra("role").toString()
            val intent: Intent =  Intent(applicationContext, DataFSActivity::class.java)
            intent.putExtra("user", user)
            intent.putExtra("role", role)
            intent.putExtra("nom", nom)
            startActivity(intent)
        }

        public fun Retour(view: View) {
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