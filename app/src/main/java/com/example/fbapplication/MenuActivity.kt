package com.example.fbapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
    }
    public fun menuTache(view: View) {
        startActivity(Intent(this, menuTacheActivity::class.java))
    }
    public fun menuProjet(view: View) {
        startActivity(Intent(this, MenuProjetActivity::class.java))
    }
    public fun menuCollab(view: View) {
        startActivity(Intent(this, MenuCollabActivity::class.java))
    }
    public fun menuSuivi(view: View) {
        startActivity(Intent(this, menuSuiviActivity::class.java))
    }
}