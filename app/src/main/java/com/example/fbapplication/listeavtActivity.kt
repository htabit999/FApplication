package com.example.fbapplication

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.fbapplication.databinding.ActivityListeBinding
import com.example.fbapplication.databinding.ActivityListeavBinding
import com.example.fbapplication.databinding.ActivityListeavtBinding

class listeavtActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListeavtBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listeavt)
        val intent = intent
        val tache = intent.getStringExtra("tache")
        val projet = intent.getStringExtra("projet")
        val d1 = intent.getStringExtra("description1")
        val st = intent.getStringExtra("status")
        val dd = intent.getStringExtra("dated")
        val df = intent.getStringExtra("datef")
        val av = intent.getStringExtra("av")
        binding = ActivityListeavtBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val feelings = resources.getStringArray(R.array.taux)
        val arrayAdapter = ArrayAdapter(this, R.layout.dropdown_item, feelings)
        binding.autoCompleteTextView.setAdapter(arrayAdapter)
        binding.autoCompleteTextView.setOnItemClickListener { _, _, position, _ ->
            val value = arrayAdapter.getItem(position) ?: ""
            val act=intent.getStringExtra("activity")
            if (act=="majtacheactivity") {
                var intent : Intent= Intent(applicationContext, majTacheActivity::class.java)
                intent.putExtra("projet", projet)
                intent.putExtra("tache", tache)
                intent.putExtra("description1", d1)
                intent.putExtra("dated", dd)
                intent.putExtra("datef",df)
                intent.putExtra("status", st)
                intent.putExtra("avancement", value.toString())
                startActivity(intent)
            }
            else
            {
                var intent : Intent= Intent(applicationContext, TacheActivity::class.java)
                intent.putExtra("projet",projet)
                intent.putExtra("tache", tache)
                intent.putExtra("description1", d1)
                intent.putExtra("dated", dd)
                intent.putExtra("datef",df)
                intent.putExtra("status", st)
                intent.putExtra("avancement", value.toString())
                startActivity(intent)
            }
        }
    }
}