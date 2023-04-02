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
    startActivity(Intent(this, TacheActivity::class.java))
}
public fun listeTache(view: View) {
    startActivity(Intent(this, listeTacheActivity::class.java))
}
 public fun retourMenu(view: View) {
        startActivity(Intent(this, MenuActivity::class.java))
 }
}