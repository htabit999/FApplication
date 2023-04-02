package com.example.fbapplication

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.fbapplication.databinding.ActivityListeBinding

class listeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_liste)
        val intent = intent
        val nprj = intent.getStringExtra("projet")
        val d1 = intent.getStringExtra("description1")
        val st = intent.getStringExtra("status")
        val dd = intent.getStringExtra("dated")
        val df = intent.getStringExtra("datef")
        val av = intent.getStringExtra("av")
        val activite = intent.getStringExtra("activity")
        binding = ActivityListeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val feelings = resources.getStringArray(R.array.feelings)
        val arrayAdapter = ArrayAdapter(this, R.layout.dropdown_item, feelings)
        binding.autoCompleteTextView.setAdapter(arrayAdapter)
        binding.autoCompleteTextView.setOnItemClickListener { _, _, position, _ ->
            val value = arrayAdapter.getItem(position) ?: ""
            if (activite=="projet") {
                var intent: Intent = Intent(applicationContext, ProjetActivity::class.java)
                intent.putExtra("projet", nprj)
                intent.putExtra("description1", d1)
                intent.putExtra("dated", dd)
                intent.putExtra("datef",df)
                intent.putExtra("status", value)
                intent.putExtra("av", av)
                startActivity(intent)
            }
            else {
                var intent: Intent = Intent(applicationContext, majProjetActivity::class.java)
                intent.putExtra("projet", nprj)
                intent.putExtra("description1", d1)
                intent.putExtra("dated", dd)
                intent.putExtra("datef",df)
                intent.putExtra("status", value)
                intent.putExtra("av", av)
                startActivity(intent)
            }
        }
    }
}