package com.example.fbapplication

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.fbapplication.databinding.ActivityListeBinding
import com.example.fbapplication.databinding.ActivityListeChefBinding
import com.example.fbapplication.databinding.ActivityListeavBinding
import com.example.fbapplication.models.Project
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class listeChefActivity : AppCompatActivity() {

    private var db = Firebase.firestore
    private lateinit var binding:ActivityListeChefBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_liste_chef)
        val intent = intent
        val nprj = intent.getStringExtra("projet")
        val d1 = intent.getStringExtra("description1")
        val st = intent.getStringExtra("status")
        val dd = intent.getStringExtra("dated")
        val df = intent.getStringExtra("datef")
        val av = intent.getStringExtra("av")
        val ch = intent.getStringExtra("ch")

        //val ch = intent.getStringExtra("chef")
        val activite = intent.getStringExtra("activity")
        binding = ActivityListeChefBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var list = ArrayList<String>()
        db.collection("Users").whereEqualTo("Role", "Chef de projet")
            .get()
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    for (document in it.result) {
                        list.add(
                            document.data.getValue("Nom") as String
                        )
                    }
                }
            }
            val arrayAdapter = ArrayAdapter(this, R.layout.dropdown_item, list)
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
                    intent.putExtra("av", av)
                    intent.putExtra("ch", value)
                    intent.putExtra("role", role)
                    intent.putExtra("nom", nom)
                    intent.putExtra("user", user)
                    startActivity(intent)
                } else
                {
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
                        intent.putExtra("av", av)
                        intent.putExtra("ch", value)
                        intent.putExtra("role", role)
                        intent.putExtra("nom", nom)
                        intent.putExtra("user", user)
                    startActivity(intent)
                } else {
                        if (activite == "majchprojet") {
                            val role = intent.getStringExtra("role")
                            val nom = intent.getStringExtra("nom")
                            val user = intent.getStringExtra("user")
                            //Toast.makeText(this, " majchprojet", Toast.LENGTH_LONG).show()
                            var intent: Intent =
                                Intent(applicationContext, majCHProjetActivity::class.java)
                            intent.putExtra("projet", nprj)
                            intent.putExtra("description1", d1)
                            intent.putExtra("dated", dd)
                            intent.putExtra("datef", df)
                            intent.putExtra("status", st)
                            intent.putExtra("av", av)
                            intent.putExtra("ch", value)
                            intent.putExtra("role", role)
                            intent.putExtra("nom", nom)
                            intent.putExtra("user", user)
                            startActivity(intent)
                        }
                    }
                }
            }
        }
    }