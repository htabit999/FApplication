package com.example.fbapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
     public fun gotoLogin(view: View) {
         //val intent = intent
         val role = intent.getStringExtra("role")
         val nom = intent.getStringExtra("nom")
         val user = intent.getStringExtra("user")
         val intent: Intent =  Intent(applicationContext, MenuTacheActivity::class.java)
         intent.putExtra("role", role)
         intent.putExtra("nom", nom)
         intent.putExtra("user", user)
         startActivity(intent)
         //startActivity(Intent(this, LoginActivity::class.java))
        }
    public fun gotoReg(view: View) {
        //val intent = intent
        val role = intent.getStringExtra("role")
        val nom = intent.getStringExtra("nom")
        val user = intent.getStringExtra("user")
        val intent: Intent =  Intent(applicationContext, RegisterActivity::class.java)
        intent.putExtra("role", role)
        intent.putExtra("nom", nom)
        intent.putExtra("user", user)
        startActivity(intent)
        //startActivity(Intent(this, RegisterActivity::class.java))
        }

}