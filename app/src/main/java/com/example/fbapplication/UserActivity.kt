package com.example.fbapplication

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fbapplication.models.User
import com.example.fbapplication.models.Usr
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class UserActivity : AppCompatActivity() {
    private lateinit var userRecyclerView: RecyclerView
    private lateinit var userList: ArrayList<Usr>
    private lateinit var adapter: UserAdapter
    private var db = Firebase.firestore
    private var role: String? = null
    private var user: String? = null
    private var nom: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
        userList = ArrayList()
        user = intent.getStringExtra("user").toString()
        nom = intent.getStringExtra("nom").toString()
        role = intent.getStringExtra("role").toString()
        //Toast.makeText(this, "userActivity", Toast.LENGTH_LONG).show()
        adapter=UserAdapter(this,userList)
        userRecyclerView=findViewById(R.id.userRecyclerView)
        userRecyclerView.layoutManager= LinearLayoutManager(this)
        userRecyclerView.adapter=adapter
        var list = ArrayList<Usr>()
        db.collection("Users").get()
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    for (document in it.result) {
                        list.add(
                            Usr(
                                document.data.getValue("Nom") as String,
                                document.data.getValue("Prenom") as String,
                                document.data.getValue("Email") as String,
                                document.data.getValue("UID") as String,
                                document.data.getValue("Role") as String,
                                document.data.getValue("Url") as String
                            )
                        )
                    }
                }
                adapter.notifyDataSetChanged()
                adapter = UserAdapter(this, list)
                userRecyclerView.adapter=adapter
            }
    }

    fun retourMenu(view: View)
    {
        if (role=="Administrateur") {
            val intent: Intent = Intent(applicationContext, MenuChefActivity::class.java)
            intent.putExtra("role", role)
            intent.putExtra("nom", nom)
            intent.putExtra("user", user)
            startActivity(intent)
        } else {
            if (role == "Chef de projet") {
                val intent: Intent = Intent(applicationContext, MenuActivity::class.java)
                intent.putExtra("role", role)
                intent.putExtra("nom", nom)
                intent.putExtra("user", user)
                startActivity(intent)
            } else {
                if (role == "Collaborateur") {
                    val intent: Intent = Intent(applicationContext, MenuColActivity::class.java)
                    intent.putExtra("role", role)
                    intent.putExtra("nom", nom)
                    intent.putExtra("user", user)
                    startActivity(intent)
                }
            }
        }
    }
}