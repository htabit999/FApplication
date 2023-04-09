package com.example.fbapplication

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.fbapplication.databinding.ActivityListeBinding
import com.example.fbapplication.databinding.ActivityListeavBinding

class listeavActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListeavBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listeav)
        val intent = intent
        val nprj = intent.getStringExtra("projet")
        val d1 = intent.getStringExtra("description1")
        val st = intent.getStringExtra("status")
        val dd = intent.getStringExtra("dated")
        val df = intent.getStringExtra("datef")
        val av = intent.getStringExtra("av")
        val ch = intent.getStringExtra("ch")
        val activite = intent.getStringExtra("activity")
        binding = ActivityListeavBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val feelings = resources.getStringArray(R.array.taux)
        val arrayAdapter = ArrayAdapter(this, R.layout.dropdown_item, feelings)
        binding.autoCompleteTextView.setAdapter(arrayAdapter)
        binding.autoCompleteTextView.setOnItemClickListener { _, _, position, _ ->
            val value = arrayAdapter.getItem(position) ?: ""
            if (activite == "projet") {
                val role = intent.getStringExtra("role")
                val nom = intent.getStringExtra("nom")
                val user = intent.getStringExtra("user")
                var intent: Intent = Intent(applicationContext, ProjetActivity::class.java)
                intent.putExtra("projet", nprj)
                intent.putExtra("description1", d1)
                intent.putExtra("dated", dd)
                intent.putExtra("datef", df)
                intent.putExtra("status", st)
                intent.putExtra("av", value)
                intent.putExtra("ch", ch)
                intent.putExtra("role", role)
                intent.putExtra("nom", nom)
                intent.putExtra("user", user)
                startActivity(intent)
            } else
                if (activite == "majprojet") {
                    val role = intent.getStringExtra("role")
                    val nom = intent.getStringExtra("nom")
                    val user = intent.getStringExtra("user")
                    var intent: Intent = Intent(applicationContext, majProjetActivity::class.java)
                    intent.putExtra("projet", nprj)
                    intent.putExtra("description1", d1)
                    intent.putExtra("dated", dd)
                    intent.putExtra("datef", df)
                    intent.putExtra("status", st)
                    intent.putExtra("av", value)
                    intent.putExtra("ch", ch)
                    intent.putExtra("role", role)
                    intent.putExtra("nom", nom)
                    intent.putExtra("user", user)
                    startActivity(intent)
                } else
                    if (activite == "majchprojet") {
                        val role = intent.getStringExtra("role")
                        val nom = intent.getStringExtra("nom")
                        val user = intent.getStringExtra("user")
                        var intent: Intent =
                            Intent(applicationContext, majCHProjetActivity::class.java)
                        intent.putExtra("projet", nprj)
                        intent.putExtra("description1", d1)
                        intent.putExtra("dated", dd)
                        intent.putExtra("datef", df)
                        intent.putExtra("status", st)
                        intent.putExtra("av", value)
                        intent.putExtra("ch", ch)
                        intent.putExtra("role", role)
                        intent.putExtra("nom", nom)
                        intent.putExtra("user", user)
                        startActivity(intent)
                    }
        }
    }
}