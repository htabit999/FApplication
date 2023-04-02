package com.example.fbapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MenuActivity : AppCompatActivity() {
    //public var user: String = ""
    //var intent :Intent= getIntent()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        //
        //var mApp = LoginActivity()
        //var strGlobalVar = mApp.user.toString()
        //var intent :Intent= getIntent()
        //var user = intent.getStringExtra("user").toString()
        //Toast.makeText(this@MenuActivity, "USR1 : " + user, Toast.LENGTH_LONG).show()
        //
    }
    public fun menuTache(view: View) {
        startActivity(Intent(this, MenuTacheActivity::class.java))
    }
    public fun menuProjet(view: View) {
        //val intent: Intent =  Intent(applicationContext, MenuActivity::class.java)
        var intent1 :Intent= getIntent()
        var user = intent1.getStringExtra("user").toString()

        val intent: Intent =  Intent(applicationContext, MenuProjetActivity::class.java)
        intent.putExtra("user", user)
        startActivity(intent)

        //intent.putExtra("user", user)
        //Toast.makeText(this@MenuActivity, "USR1 : " + user, Toast.LENGTH_LONG).show()
        //startActivity(intent)
        //startActivity(Intent(this, MenuProjetActivity::class.java))
    }
    public fun menuCollab(view: View) {
        startActivity(Intent(this, MenuCollabActivity::class.java))
    }
    public fun menuSuivi(view: View) {
        startActivity(Intent(this, menuSuiviActivity::class.java))
    }
    public fun Retour(view: View) {
        startActivity(Intent(this, MainActivity::class.java))
    }
}