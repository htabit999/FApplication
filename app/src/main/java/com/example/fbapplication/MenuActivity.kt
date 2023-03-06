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
    public fun addTache(view: View) {
        startActivity(Intent(this, TacheActivity::class.java))
    }
    public fun addProjet(view: View) {
        startActivity(Intent(this, ProjetActivity::class.java))
    }
    public fun addCollab(view: View) {
        startActivity(Intent(this, SuiviActivity::class.java))
    }
    public fun addSuivi(view: View) {
        startActivity(Intent(this, CollabActivity::class.java))
    }
}