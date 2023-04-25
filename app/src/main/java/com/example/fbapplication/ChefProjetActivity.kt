package com.example.fbapplication

import android.app.Activity
import android.app.ProgressDialog
import android.content.ContentValues.TAG
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.fbapplication.models.User
import com.example.fbapplication.models.Usr
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.squareup.picasso.Picasso
import java.util.*


class ChefProjetActivity : AppCompatActivity() {
    lateinit var auth: FirebaseAuth
    private var dbf = Firebase.firestore
    lateinit var imageView: ImageView
    var fileUri: Uri? = null;
    var selectedPhotoUri: Uri? = null;
    lateinit var uploadImageBtn: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chef_projet)
        uploadImageBtn = findViewById(R.id.uploadImageBtn)
        auth = FirebaseAuth.getInstance()
        val image: ImageView = findViewById(R.id.imageView21)
        val role = intent.getStringExtra("role")
        val nom = intent.getStringExtra("nom")
        val prenom = intent.getStringExtra("prenom")
        val name = intent.getStringExtra("name")
        val email = intent.getStringExtra("email")
        val psw = intent.getStringExtra("psw")
        val rpsw = intent.getStringExtra("rpsw")
        val url = intent.getStringExtra("url")
        imageView = findViewById(R.id.imageView21)
        uploadImageBtn.visibility=View.GONE
        val nm = this.findViewById(R.id.editTextNom) as TextView
        val pr = this.findViewById(R.id.editTextPrenom) as TextView
        val em = this.findViewById(R.id.email_edit_text) as TextView
        val ps = this.findViewById(R.id.password_edit_text) as TextView
        val rp = this.findViewById(R.id.re_password_edit_text) as TextView
        nm.text = name
        pr.text = prenom
        em.text = email
        ps.text = psw
        rp.text = rpsw
        uploadImageBtn.setOnClickListener {
              val intent = Intent(Intent.ACTION_PICK)
                intent.type = "image/*"
                startActivityForResult(intent, 0)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 0 && resultCode == Activity.RESULT_OK && data != null) {
            selectedPhotoUri = data.data ?: return
            Log.d(TAG, "Photo was selected")
            val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
            contentResolver.query(selectedPhotoUri!!, filePathColumn, null, null, null)?.use {
                it.moveToFirst()
                Toast.makeText(this@ChefProjetActivity,"31",Toast.LENGTH_SHORT).show()
                val columnIndex = it.getColumnIndex(filePathColumn[0])
                val picturePath = it.getString(columnIndex)
                if (picturePath.contains("DCIM")) {
                   Picasso.get().load(selectedPhotoUri).rotate(270f).into(imageView)
               } else {
                    Picasso.get().load(selectedPhotoUri).into(imageView)
                }
            }
            uploadImageToFirebaseStorage()
            }
    }

    fun getImage()
    {
        val firebaseDatabase = FirebaseDatabase.getInstance()
        val databaseReference = firebaseDatabase.reference
        val getImage = databaseReference.child("Photos")
        getImage.addListenerForSingleValueEvent(
            object : ValueEventListener {
                override fun onDataChange( dataSnapshot: DataSnapshot  ) {
                    val link = dataSnapshot.getValue()
                }
                override fun onCancelled(  databaseError: DatabaseError ) {
                    Toast.makeText(this@ChefProjetActivity,"Error Loading Image",Toast.LENGTH_SHORT).show()
                }
            })
    }

     fun uploadImageToFirebaseStorage() {
            if (selectedPhotoUri == null) {
                // save user without photo
                saveUserToFirebaseDatabase(null)
            } else {
                val filename = UUID.randomUUID().toString()
                val ref = FirebaseStorage.getInstance().getReference("/img/$filename")
                ref.putFile(selectedPhotoUri!!)
                    .addOnSuccessListener {
                        Log.d(TAG, "Successfully uploaded image: ${it.metadata?.path}")
                        @Suppress("NestedLambdaShadowedImplicitParameter")
                        ref.downloadUrl.addOnSuccessListener {
                            Log.d(TAG, "File Location: $it")
                            saveUserToFirebaseDatabase(it.toString())
                            addUser(it.toString())
                        }
                    }
                    .addOnFailureListener {
                        Log.d(TAG, "Failed to upload image to storage: ${it.message}")
                        //loading_view.visibility = View.GONE
                        //already_have_account_text_view.visibility = View.VISIBLE
                    }
            }

    }

    fun registerUser(view: View) {
            val url = intent.getStringExtra("url")
            var email: String = findViewById<EditText>(R.id.email_edit_text).text.toString()
            var password: String = findViewById<EditText>(R.id.password_edit_text).text.toString()
            var nom: String = findViewById<EditText>(R.id.editTextNom).text.toString()
            var prenom: String = findViewById<EditText>(R.id.editTextPrenom).text.toString()
            //var role: String = findViewById<EditText>(R.id.role_edit_text).text.toString()
            uploadImageBtn.visibility=View.VISIBLE
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Il faut remplir tous les champs", Toast.LENGTH_SHORT).show()
            } else {
                val user = HashMap<String, Any>()
                user["Email"] = email
                user["Nom"] = nom
                user["Prenom"] = prenom
                user["Role"] = "Chef de projet"
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this, "User added ", Toast.LENGTH_LONG).show()
                            //addUser()
                        } else {
                            Toast.makeText(this, "User not added ", Toast.LENGTH_LONG).show()
                        }
                    }
            }
    }

     fun addUser(profileImageUrl:String) {
            val uid = FirebaseAuth.getInstance().uid ?: return
            var email: String = findViewById<EditText>(R.id.email_edit_text).text.toString()
            var password: String = findViewById<EditText>(R.id.password_edit_text).text.toString()
            var nom: String = findViewById<EditText>(R.id.editTextNom).text.toString()
            var prenom: String = findViewById<EditText>(R.id.editTextPrenom).text.toString()
            //var role: String = findViewById<EditText>(R.id.role_edit_text).text.toString()
            if (email.isEmpty() || password.isEmpty() || nom.isEmpty() || prenom.isEmpty()) {
                Toast.makeText(this, "Il faut remplir tous les champs", Toast.LENGTH_SHORT).show()
            } else {
                val add = HashMap<String, Any>()
                add["UID"] = uid
                add["Nom"] = nom
                add["Prenom"] = prenom
                add["Email"] = email
                add["Role"] = "Chef de projet"
                add["Url"] = profileImageUrl
                dbf.collection("Users")
                    .document(nom).set(add)
                    .addOnSuccessListener {
                        val role = intent.getStringExtra("role")
                        val nom = intent.getStringExtra("nom")
                        val user = intent.getStringExtra("user")
                        Toast.makeText(this, "Data added ", Toast.LENGTH_LONG).show()
                        val intent = Intent(this, MenuChefProjetActivity::class.java)
                        intent.putExtra("user", user)
                        intent.putExtra("role", role)
                        intent.putExtra("nom", nom)
                        startActivity(intent)
                    }
                    .addOnFailureListener {
                        Toast.makeText(this, " Data not added ", Toast.LENGTH_LONG).show()
                    }
                return
            }
    }

     fun uploadImage() {
            //progressbar.visibility= View.VISIBLE
            var nom: String = findViewById<EditText>(R.id.editTextNom).text.toString()
            val uid = FirebaseAuth.getInstance().currentUser!!.uid
            //email = intent.getStringExtra("email")
            if (fileUri != null) {
                val progressDialog = ProgressDialog(this)
                progressDialog.setTitle("Uploading...")
                progressDialog.setMessage("Uploading your image..")
                progressDialog.show()
                val ref: StorageReference = FirebaseStorage.getInstance().getReference()
                    .child("Photos").child(nom)
                Toast.makeText(applicationContext, fileUri!!.toString(), Toast.LENGTH_SHORT).show()
                ref.putFile(fileUri!!).addOnSuccessListener {
                    progressDialog.dismiss()
                    Toast.makeText(applicationContext, "Image Uploaded..", Toast.LENGTH_SHORT)
                        .show()
                }.addOnFailureListener {
                    progressDialog.dismiss()
                    Toast.makeText(applicationContext, "Fail to Upload Image..", Toast.LENGTH_SHORT)
                        .show()
                }
            }
            //progressbar.visibility= View.GONE
    }

     fun Upload(view: View) {
            val role = intent.getStringExtra("role")
            val nom = intent.getStringExtra("nom")
            val user = intent.getStringExtra("user")
            var name: String = findViewById<EditText>(R.id.editTextNom).text.toString()
            var prenom: String = findViewById<EditText>(R.id.editTextPrenom).text.toString()
            var email: String = findViewById<EditText>(R.id.email_edit_text).text.toString()
            var psw: String = findViewById<EditText>(R.id.password_edit_text).text.toString()
            var rpsw: String = findViewById<EditText>(R.id.re_password_edit_text).text.toString()
            val intent = Intent(this, UploadActivity::class.java)
            intent.putExtra("name", name)
            intent.putExtra("prenom", prenom)
            intent.putExtra("email", email)
            intent.putExtra("psw", psw)
            intent.putExtra("rpsw", rpsw)
            intent.putExtra("user", user)
            intent.putExtra("role", role)
            intent.putExtra("nom", nom)
            startActivity(intent)
    }

     fun retour(view: View) {
            val role = intent.getStringExtra("role")
            val nom = intent.getStringExtra("nom")
            val user = intent.getStringExtra("user")
            val intent = Intent(this, MenuChefProjetActivity::class.java)
            intent.putExtra("user", user)
            intent.putExtra("role", role)
            intent.putExtra("nom", nom)
            startActivity(intent)
    }
        //

     fun saveUserToFirebaseDatabase(profileImageUrl: String?) {
            val uid = FirebaseAuth.getInstance().uid ?: return
            val ref = FirebaseDatabase.getInstance().getReference("/usr/$uid")
            var name: String = findViewById<EditText>(R.id.editTextNom).text.toString()
            var prenom: String = findViewById<EditText>(R.id.editTextPrenom).text.toString()
            var email: String = findViewById<EditText>(R.id.email_edit_text).text.toString()

            val user = if (profileImageUrl == null) {
                Usr(name,prenom,email,uid,"Chef Projet", null!!)
            } else {
                Usr(name,prenom,email,uid,"Chef Projet",profileImageUrl)
            }
            ref.setValue(user)
                .addOnSuccessListener {
                    Log.d(TAG, "Finally we saved the user to Firebase Database")
                    val intent = Intent(this, ChefProjetActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                    //overridePendingTransition(R.anim.enter, R.anim.exit)
                }
                .addOnFailureListener {
                    Log.d(TAG, "Failed to set value to database: ${it.message}")
                    //loading_view.visibility = View.GONE
                    //already_have_account_text_view.visibility = View.VISIBLE
                }
         uploadImageBtn.visibility=View.GONE
    }
}