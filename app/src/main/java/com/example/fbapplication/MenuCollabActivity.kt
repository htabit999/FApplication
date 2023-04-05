package com.example.fbapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MenuCollabActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_collab)
    }
    public fun addCollab(view: View) {
        startActivity(Intent(this, CollabActivity::class.java))
    }
    public fun listeCollab(view: View) {
        startActivity(Intent(this, lCollabActivity::class.java))
    }
    public fun suppCollab(view: View) {
        startActivity(Intent(this, CollabActivity::class.java))
    }
    public fun modCollab(view: View) {
        startActivity(Intent(this, CollabActivity::class.java))
    }
}