package com.example.fbapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class menuSuiviActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_suivi)
    }
    public fun listeSuivi(view: View) {
        startActivity(Intent(this, SuiviActivity::class.java))
    }
    public fun listeTaches(view: View) {
        startActivity(Intent(this, SuiviActivity::class.java))
    }
}