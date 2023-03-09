package com.example.fbapplication

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.security.AccessController.getContext


class listeProjetActivity : AppCompatActivity()
{
    var db = FirebaseDatabase.getInstance().getReference("projet-4f405")
    var tblName = arrayOf<String>("")
    //C","C++","Java",".Net","Kotlin","Ruby","Rails","Python","Java Script","Php","Ajax","Perl","Hadoop")
    var tblEml = arrayOf<String>("")
    //
    //"C programming is considered as the base for other programming languages",
    //"C++ is an object-oriented programming language.",
    //"Java is a programming language and a platform.",
    //".NET is a framework which is used to develop software applications.",
    //"Kotlin is a open-source programming language, used to develop Android apps and much more.",
    //"Ruby is an open-source and fully object-oriented programming language.",
    //"Ruby on Rails is a server-side web application development framework written in Ruby language.",
    //"Python is interpreted scripting  and object-oriented programming language.",
    //"JavaScript is an object-based scripting language.",
    //"PHP is an interpreted language, i.e., there is no need for compilation.",
    //"AJAX allows you to send and receive data asynchronously without reloading the web page.",
    //"Perl is a cross-platform environment used to create network and server-side applications.",
    //"Hadoop is an open source framework from Apache written in Java."
    // )
    var imageId = arrayOf<Int>(R.drawable.photo)
    //    R.drawable.photo,R.drawable.photo,R.drawable.photo,R.drawable.photo,
    //  R.drawable.photo,R.drawable.photo,R.drawable.photo,
    //R.drawable.photo,R.drawable.photo,R.drawable.photo,
    //R.drawable.photo
    //)
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        val TAG = javaClass.simpleName
        android.util.Log.d(TAG, "onCreate")
        setContentView(R.layout.activity_liste_projet)
        //var db = FirebaseDatabase.getInstance().getReference("projet-4f405")
        var listeEml=readDataFB()
        //val menuListener = object : ValueEventListener {
          //  override fun onDataChange(snapshot: DataSnapshot) {
            //    tblEml = arrayOf<String>()
              //  tblName = arrayOf<String>()
                //imageId = arrayOf<Int>()
                //for (i in snapshot.children) {
                  //  i.child("Projet").children.forEach {
                    //    it.children.forEach {
                      //      it.children.forEach {
                        //        if (it.key == "projet") {
                          //          tblEml = addElement(tblEml, it.value as String)
                            //        imageId = addElement(imageId, R.drawable.ic_launcher_background)
                              ///      tblName = addElement(tblName, it.value as String)
                                    //Log.v("EML_VALUE"," :" + it.value as String);
                                    //Log.v("Name_VALUE"," :" + R.drawable.ic_launcher_background);
                                    //Log.v("Img_VALUE","  :" + it.value as String);
                                    //Toast.makeText(this@listeProjetActivity, it.value as String, Toast.LENGTH_LONG).show()
                                //}
                                //if (it.key == "description") {
                                //    imageId = addElement(imageId, R.drawable.ic_launcher_background)
                                //    tblName = addElement(tblName, it.value as String)
                                // }
                            //}
                        //}
                    //}
                //}
                //for (i in 0..(imageId.size-1)) {
                //  Toast.makeText(this@listeProjetActivity, imageId[i], Toast.LENGTH_LONG).show()
                //}
                val listView = findViewById<ListView>(R.id.listView) as ListView
                //println("Avant TTTTTTTTTTTTTTTT")
                val myListAdapter = listeAdapter(this, tblName, tblEml, imageId)
                //println("Apres TTTTTTTTTTTTTTTT")
                listView.adapter == myListAdapter
                listView.setOnItemClickListener()
                {
                        adapterView, view, position, id ->
                        val itemAtPos = adapterView.getItemAtPosition(position)
                        val itemIdAtPos = adapterView.getItemIdAtPosition(position)
                        //Toast.makeText(this@listeProjetActivity, "Click on item at $itemAtPos its item id $itemIdAtPos", Toast.LENGTH_LONG).show()
                }
            }
            //override fun onCancelled(databaseError: DatabaseError)
            //{
            //}

       // db.addValueEventListener(menuListener)
    }

    public fun addElement(tblName: Array<String>, chaine: String): Array<String> {
        val tblList = tblName.toMutableList()
        tblList.add(chaine)
        return tblList.toTypedArray()
    }

    public fun addElement(tblName: Array<Int>, chaine: Int): Array<Int> {
        val tblList = tblName.toMutableList()
        tblList.add(chaine)
        return tblList.toTypedArray()
    }

    public fun readDataFB() : Array<String>
    {
        val db = FirebaseDatabase.getInstance().getReference("projet-4f405")
        val menuListener = object : ValueEventListener
        {
            var tblEml = arrayOf<String>()
            var tblName = arrayOf<String>()
            var imageId = arrayOf<Int>()
            override fun onDataChange(snapshot: DataSnapshot)
            {
                for (i in snapshot.children) {
                    i.child("Projet").children.forEach {
                        it.children.forEach {
                            it.children.forEach {
                                if (it.key == "projet") {
                                    tblEml = addElement(tblEml, it.value as String)
                                    imageId = addElement(imageId, R.drawable.ic_launcher_background)
                                    tblName = addElement(tblName, it.value as String)
                                }
                            }
                        }
                    }
                }
            }
            override fun onCancelled(databaseError: DatabaseError)
            {
            }
        }
        var tbl = arrayOf<String>("")
        return tbl
    }

