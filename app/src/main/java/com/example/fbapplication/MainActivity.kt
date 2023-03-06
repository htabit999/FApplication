package com.example.fbapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }
     public fun gotoLogin(view: View) {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    public fun gotoReg(view: View) {
            startActivity(Intent(this, RegistrationActivity::class.java))
        }

    public fun addTache(view: View) {
        startActivity(Intent(this, TacheActivity::class.java))
    }
}