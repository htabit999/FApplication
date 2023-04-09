package com.example.fbapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MenuTacheActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_tache)
    }
public fun addTache(view: View) {

    val role = intent.getStringExtra("role")
    val nom = intent.getStringExtra("nom")
    val user = intent.getStringExtra("user")
    val intent: Intent =  Intent(applicationContext, TacheActivity::class.java)
    intent.putExtra("role", role)
    intent.putExtra("nom", nom)
    intent.putExtra("user", user)
    startActivity(intent)
}
public fun listeTache(view: View) {
    val role = intent.getStringExtra("role")
    val nom = intent.getStringExtra("nom")
    val user = intent.getStringExtra("user")
    val intent: Intent =  Intent(applicationContext, listeTacheActivity::class.java)
    intent.putExtra("role", role)
    intent.putExtra("nom", nom)
    intent.putExtra("user", user)
    startActivity(intent)

}
 public fun retourMenu(view: View) {
     val role = intent.getStringExtra("role")
     val nom = intent.getStringExtra("nom")
     val user = intent.getStringExtra("user")
     val intent: Intent =  Intent(applicationContext, MenuActivity::class.java)
     intent.putExtra("role", role)
     intent.putExtra("nom", nom)
     intent.putExtra("user", user)
     startActivity(intent)
 }
}