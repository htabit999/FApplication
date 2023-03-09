package com.example.fbapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MenuProjetActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_projet)
    }
        public fun addProjet(view: View) {
            startActivity(Intent(this, ProjetActivity::class.java))
        }
        public fun listeProjet(view: View) {
            startActivity(Intent(this, DataActivity::class.java))
        }
        public fun suppProjet(view: View) {
            startActivity(Intent(this, SupProjetActivity::class.java))
        }
        public fun modProjet(view: View) {
            startActivity(Intent(this, modProjetActivity::class.java))
        }
    }