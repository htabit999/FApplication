package com.example.fbapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.view.get
import com.example.fbapplication.models.Collaborateur
import com.example.fbapplication.models.Projet
import com.google.firebase.database.*

class ProjetActivity : AppCompatActivity()
{
    private lateinit var db : DatabaseReference
    private lateinit var spinner : Spinner
    var listeid = arrayOf<String>()
    var collaborateur=mutableListOf<String>("--- Choisir un collaborateur ---")
    //var collaborateur=mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_projet)
        db= FirebaseDatabase.getInstance().getReference("projet-4f405")
        spinner = findViewById(R.id.spinner)
        val dataList = collaborateur.toMutableList()
        db.addValueEventListener(object : ValueEventListener
        {
            override fun onDataChange(snapshot: DataSnapshot)
            {
                if (snapshot!!.exists()) {
                    dataList.clear()
                    for (e in snapshot.children)
                    {
                        e.child("Collaborateur").children.forEach() {
                            it.children.forEach {
                                if (it.key == "idc") {
                                    listeid = addElement(listeid, it.value as String)
                                }
                                if (it.key == "nom") {
                                    collaborateur.add( it.value as String )
                                    //dataList.add(it.value as String)
                                    //Toast.makeText(this@ProjetActivity,it.value as String , Toast.LENGTH_SHORT).show()
                                }
                            }
                        }
                    }
                }
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
        //collaborateur= addElement(collaborateur,"111")
        //collaborateur= dataList.toTypedArray()
        //Toast.makeText(this@ProjetActivity,collaborateur[1], Toast.LENGTH_SHORT).show()
        //------------------------------------------------------------------
        //val listenom=lcolab()
        //Toast.makeText(this@ProjetActivity,listenom.size, Toast.LENGTH_SHORT).show()
        //var i:Int =0
        //while ( i < listenom.size )
        //{
          //  collaborateur = addElement(collaborateur, listenom[i])
            //Toast.makeText(this@ProjetActivity,listenom[i], Toast.LENGTH_SHORT).show()
            //i = i + 1
        //}
        //-------------------------------------------------------------------
        val adapter=ArrayAdapter(this,android.R.layout.simple_list_item_1,collaborateur)
        spinner.adapter=adapter
        spinner.setOnItemSelectedListener (object : AdapterView.OnItemSelectedListener
        {
            override fun onItemSelected (
                parent : AdapterView<*>?,
                view:View?,
                position:Int,
                id: Long
            ) {
                //Toast.makeText(this@ProjetActivity, "Collaborateur", Toast.LENGTH_SHORT).show()
                val selecetedItem = collaborateur[position]
                if (selecetedItem != "--- Choisir un collaborateur ---") {
                    //spinner.setSelection(selecetedItem)
                    //Toast.makeText(this@ProjetActivity, "Collaborateur = $selecetedItem", Toast.LENGTH_SHORT).show()
                    spinner.visibility = View.INVISIBLE
                    //chef_edit_text
                    val chef = this@ProjetActivity.findViewById(R.id.chef_edit_text) as TextView
                    chef.text = selecetedItem
                } else {
                    Toast.makeText(
                        this@ProjetActivity,
                        "Choisir un collaborateur",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
        )
    }

    public fun listecolab(view:View)
    {
        spinner.visibility= View.VISIBLE
    }

     public fun addProjet(view: View) {
        var namep: String = findViewById<EditText>(R.id.nomp_edit_text).text.toString()
        var chef : String = findViewById<EditText>(R.id.chef_edit_text).text.toString()
        var statusp : String = findViewById<EditText>(R.id.statusP_edit_text).text.toString()
        var datedp : String = findViewById<EditText>(R.id.editTextPDateD).text.toString()
        var datefp : String = findViewById<EditText>(R.id.editTextPDateF).text.toString()
        var descr1p : String = findViewById<EditText>(R.id.descriptionp1_edit_text).text.toString()
        var descr2p : String = findViewById<EditText>(R.id.descriptionp2_edit_text).text.toString()
        var avance : String = findViewById<EditText>(R.id.editTextAvc).text.toString()

         if (namep.isEmpty() || chef.isEmpty() || statusp.isEmpty() || datedp.isEmpty() || datefp.isEmpty() || descr1p.isEmpty() || descr2p.isEmpty() || avance.isEmpty()  )
        {
            Toast.makeText(this, "Il faut remplir tous les champs", Toast.LENGTH_SHORT).show()
        }
        else
        {
            var idp : String = db.push().key!!
            var av=avance.toInt()
            val projet = Projet(idp,namep, descr1p, descr2p, chef, datedp, datefp, statusp,av)
            db.child("Base").child("Projet").child(idp).setValue(projet)
                .addOnCompleteListener {
                    Toast.makeText(this, "Data inserted successfully", Toast.LENGTH_LONG).show()
                }.addOnFailureListener { err ->
                    Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
                }
            startActivity(Intent(this, MenuActivity::class.java))
        }
    }
}