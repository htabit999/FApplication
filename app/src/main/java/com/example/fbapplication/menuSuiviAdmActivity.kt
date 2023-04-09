package com.example.fbapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class menuSuiviAdmActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_suivi_adm)
    }
    public fun listeSuivi(view: View) {
        var nom = intent.getStringExtra("nom").toString()
        var role = intent.getStringExtra("role").toString()
        var user = intent.getStringExtra("user").toString()
        var intent : Intent = Intent(applicationContext, SuiviAdmActivity::class.java)
        intent.putExtra("nom", nom)
        intent.putExtra("role", role)
        intent.putExtra("user", user)
        startActivity(intent)
    }
    public fun suiviTaches(view: View) {
        var nom = intent.getStringExtra("nom").toString()
        var role = intent.getStringExtra("role").toString()
        var user = intent.getStringExtra("user").toString()
        var intent : Intent = Intent(applicationContext, SuiviTacheAdmActivity::class.java)
        intent.putExtra("nom", nom)
        intent.putExtra("role", role)
        intent.putExtra("user", user)
        startActivity(intent)
    }
    public fun retour(view:View){
        var nom = intent.getStringExtra("nom").toString()
        var role = intent.getStringExtra("role").toString()
        var user = intent.getStringExtra("user").toString()
        var intent : Intent = Intent(applicationContext, MenuChefActivity::class.java)
        intent.putExtra("nom", nom)
        intent.putExtra("role", role)
        intent.putExtra("user", user)
        startActivity(intent)

    }
}