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
            var intent1 :Intent= getIntent()
            var user = intent1.getStringExtra("user").toString()
            //Toast.makeText(this@MenuProjetActivity, "USER2 : " + user, Toast.LENGTH_LONG).show()
            val intent: Intent =  Intent(applicationContext, DataFSActivity::class.java)
            intent.putExtra("user", user)
            startActivity(intent)
        }
        public fun suppProjet(view: View) {
            startActivity(Intent(this, DataActivity::class.java))
        }
        public fun modProjet(view: View) {
            startActivity(Intent(this, DataActivity::class.java))
        }
        public fun Retour(view: View) {
        startActivity(Intent(this, MenuActivity::class.java))
        }
}