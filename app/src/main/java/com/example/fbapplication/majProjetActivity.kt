package com.example.fbapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast

class majProjetActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maj_projet)
        val intent = intent
        val nprj = intent.getStringExtra("projet")
        val d1= intent.getStringExtra("description1")
        val d2= intent.getStringExtra("description2")
        val ch= intent.getStringExtra("chef")
        val st= intent.getStringExtra("status")
        val dd= intent.getStringExtra("dated")
        val df= intent.getStringExtra("datef")
        Toast.makeText(this, ch, Toast.LENGTH_LONG).show()
        val prj = this.findViewById(R.id.nomp_edit_text) as TextView
        val des1 = this.findViewById(R.id.descriptionp1_edit_text) as TextView
        val des2 = this.findViewById(R.id.descriptionp2_edit_text) as TextView
        val che = this.findViewById(R.id.chef_edit_text) as TextView
        val dtd = this.findViewById(R.id.editTextPDateD) as TextView
        val dtf = this.findViewById(R.id.editTextPDateF) as TextView
        val sta = this.findViewById(R.id.statusP_edit_text) as TextView
        prj.text = nprj
        des1.text=d1
        des2.text=d2
        che.text = ch
        dtd.text=dd
        dtf.text = df
        sta.text=st
    }
}
