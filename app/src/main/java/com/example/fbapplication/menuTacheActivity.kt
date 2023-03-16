package com.example.fbapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class menuTacheActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_tache)
    }
public fun addTache(view: View) {
    startActivity(Intent(this, TacheActivity::class.java))
}
public fun listeTache(view: View) {
    startActivity(Intent(this,listeTacheActivity::class.java))
}
public fun suppTache(view: View) {
    startActivity(Intent(this, TacheActivity::class.java))
}
public fun modTache(view: View) {
    startActivity(Intent(this, TacheActivity::class.java))
}
}