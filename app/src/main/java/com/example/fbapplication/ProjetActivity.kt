package com.example.fbapplication

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.example.fbapplication.BuildConfig.DEBUG
import com.example.fbapplication.models.Projet
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import com.google.firebase.firestore.*
import com.google.firebase.firestore.ktx.firestore
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class ProjetActivity : AppCompatActivity()
{
    private lateinit var db : DatabaseReference
    private lateinit var spinner : Spinner
    private var dbf = Firebase.firestore
    //var listeid = arrayOf<String>()
    //var collaborateur=mutableListOf<String>("--- Choisir un collaborateur ---")
    //var collaborateur=mutableListOf<String>()
    var auth: FirebaseAuth= FirebaseAuth.getInstance()

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_projet)
        db= FirebaseDatabase.getInstance().getReference("projet-4f405")
        //var spinner : Spinner
        //val dataList = collaborateur.toMutableList()
        var list = ArrayList<String>()
        //nouveau code liste deroulane
        dbf.collection("Collaborateur")
            .get()
            .addOnCompleteListener {
                if (it.isSuccessful)
                {
                   for (document in it.result)
                   {
                       list.add (document.data.getValue("Nom") as String)
                       //list.add (    document.data.getValue("Prenom") as String)
                       //list.add (   document.data.getValue("Email") as String)
                   }
                }
                //Toast.makeText(this@ProjetActivity, list[0], Toast.LENGTH_SHORT).show()
                // ancien code liste deroulane
        //db.addValueEventListener(object : ValueEventListener
        //{
          //  override fun onDataChange(snapshot: DataSnapshot)
            //{
              //  if (snapshot!!.exists()) {
                //    dataList.clear()
                 //   for (e in snapshot.children)
                   // {
          //              e.child("Collaborateur").children.forEach() {
        //                    it.children.forEach {
            //                    if (it.key == "idc") {
              //                      listeid = addElement(listeid, it.value as String)
                //                }
                  //             if (it.key == "nom") {
                   //                 collaborateur.add( it.value as String )
                   //                 //dataList.add(it.value as String)
                                    //Toast.makeText(this@ProjetActivity,it.value as String , Toast.LENGTH_SHORT).show()
                     //           }
                       //     }
                        //}
                    //}
                //}
            //}

                spinner = findViewById(R.id.spinner)
                val adapter=ArrayAdapter(this,android.R.layout.simple_list_item_1,list)
                spinner.adapter=adapter
                spinner.setOnItemSelectedListener (object : AdapterView.OnItemSelectedListener
                {
                    override fun onItemSelected (
                        parent : AdapterView<*>?,
                        view:View?,
                        position:Int,
                        id: Long
                    ) {
                        val selecetedItem = list[position]
                        if (selecetedItem != "--- Choisir un collaborateur ---") {
                            val chef = this@ProjetActivity.findViewById(R.id.chef_edit_text) as TextView
                            chef.text = selecetedItem

                        } else {
                            Toast.makeText(
                                this@ProjetActivity,
                                "Choisir un collaborateur",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        spinner.visibility = View.INVISIBLE
                    }
                    override fun onNothingSelected(p0: AdapterView<*>?) {
                        TODO("Not yet implemented")
                    }

                    fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
            })
            //

        }
    }

    public fun listecolab(view:View)
    {
      spinner.visibility= View.VISIBLE
    }

     public fun addFSProjet(view : View){
         val user = FirebaseAuth.getInstance().currentUser!!.uid
         var namep: String = findViewById<EditText>(R.id.nomp_edit_text).text.toString()
         var chef : String = findViewById<EditText>(R.id.chef_edit_text).text.toString()
         var statusp : String = findViewById<EditText>(R.id.statusP_edit_text).text.toString()
         var datedp : String = findViewById<EditText>(R.id.editTextPDateD).text.toString()
         var datefp : String = findViewById<EditText>(R.id.editTextPDateF).text.toString()
         var descr1p : String = findViewById<EditText>(R.id.descriptionp1_edit_text).text.toString()
         var descr2p : String = findViewById<EditText>(R.id.descriptionp2_edit_text).text.toString()
         var avance : String = findViewById<EditText>(R.id.editTextAvc).text.toString()
       if (legalDoB(datedp))
        {
           Toast.makeText(this, "Date correcte", Toast.LENGTH_SHORT).show()
        }
         val add = HashMap<String, Any>()
         add["Projet"] = namep
         add["Description1"] = descr1p
         add["Description2"] = descr2p
         add["Status"] = statusp
         add["datedeb"] = datedp
         add["Avancement"] = avance
         add["datefin"] = datefp
         add["Chef"] = chef
         add["USERID"] = user
         var idp : String = dbf.collection("Projet").id
         dbf.collection("Projet")
             .document(namep).set(add)
             //.add(add)
             .addOnSuccessListener {
                 Toast.makeText(this, "Data added ", Toast.LENGTH_LONG).show()
                 startActivity(Intent(this, MenuProjetActivity::class.java))
             }
             .addOnFailureListener {
                 Toast.makeText(this, " Data not added ", Toast.LENGTH_LONG).show()
             }
         return
     }

     public fun addProjet(view: View) {
         val user = FirebaseAuth.getInstance().currentUser!!.uid
         Toast.makeText(this, user, Toast.LENGTH_SHORT).show()

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
            val projet = Projet(idp,namep, descr1p, descr2p, chef, datedp, datefp, statusp,user,av)
            db.child("Base").child("Projet").child(idp).setValue(projet)
                .addOnCompleteListener {
                    Toast.makeText(this, "Data inserted successfully", Toast.LENGTH_LONG).show()
                }.addOnFailureListener { err ->
                    Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
                }
            startActivity(Intent(this, DataActivity::class.java))
        }
    }
    fun legalDoB( dobTextId : String): Boolean {
        val dobString = dobTextId.toString()
        val df = SimpleDateFormat("MM/dd/yy")
        try {
            val date: Date = df.parse(dobString)
            Log.d(DEBUG.toString(), "Legal Date $date")
            return true
        } catch (e: ParseException) {
            Log.d(DEBUG.toString(), "NOT Legal Date")
            return false
        }
        return false
    }
}