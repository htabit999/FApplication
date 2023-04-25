package com.example.fbapplication
import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.fbapplication.models.Usr
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.squareup.picasso.Picasso
import java.util.*
import kotlin.collections.HashMap

class CollabActivity : AppCompatActivity() {
    lateinit var auth: FirebaseAuth
    private var dbf = Firebase.firestore
    lateinit var imageView: ImageView
    var selectedPhotoUri: Uri? = null;
    lateinit var uploadImageBtn: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_collab)
        auth = FirebaseAuth.getInstance()
        imageView = findViewById(R.id.imageView21)
        uploadImageBtn = findViewById(R.id.uploadImageBtn)
        uploadImageBtn.visibility=View.GONE
        val role=intent.getStringExtra("role")
        val nom =intent.getStringExtra("nom")
        val nomc =intent.getStringExtra("nomc")
        val prenom=intent.getStringExtra("prenom")
        val email=intent.getStringExtra("email")
        val psw = intent.getStringExtra("psw")
        val rpsw=intent.getStringExtra("rpsw")
        val url = intent.getStringExtra("url")
        val rl = this.findViewById(R.id.role_edit_text) as TextView
        val nm = this.findViewById(R.id.editTextNom) as TextView
        val pr = this.findViewById(R.id.editTextPrenom) as TextView
        val em = this.findViewById(R.id.email_edit_text) as TextView
        val ps = this.findViewById(R.id.password_edit_text) as TextView
        val rp = this.findViewById(R.id.re_password_edit_text) as TextView
        rl.text = "Collaborateur"
        nm.text=nomc
        pr.text=prenom
        em.text = email
        ps.text=psw
        rp.text=rpsw
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
            Log.d(ContentValues.TAG, "Photo was selected")
            val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
            contentResolver.query(selectedPhotoUri!!, filePathColumn, null, null, null)?.use {
                it.moveToFirst()
                //Toast.makeText(this@CollabActivity,"31",Toast.LENGTH_SHORT).show()
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

    fun uploadImageToFirebaseStorage() {
        if (selectedPhotoUri == null) {
            saveUserToFirebaseDatabase(null)
        } else {
            val filename = UUID.randomUUID().toString()
            val ref = FirebaseStorage.getInstance().getReference("/img/$filename")
            ref.putFile(selectedPhotoUri!!)
                .addOnSuccessListener {
                    Log.d(ContentValues.TAG, "Successfully uploaded image: ${it.metadata?.path}")
                    @Suppress("NestedLambdaShadowedImplicitParameter")
                    ref.downloadUrl.addOnSuccessListener {
                        Log.d(ContentValues.TAG, "File Location: $it")
                        saveUserToFirebaseDatabase(it.toString())
                        addUser(it.toString())
                    }
                }
                .addOnFailureListener {
                    Log.d(ContentValues.TAG, "Failed to upload image to storage: ${it.message}")
                }
        }
    }
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
                Log.d(ContentValues.TAG, "Finally we saved the user to Firebase Database")
                val intent = Intent(this, CollabActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
            }
            .addOnFailureListener {
                Log.d(ContentValues.TAG, "Failed to set value to database: ${it.message}")
            }
        uploadImageBtn.visibility=View.GONE
    }
    public fun registerUser(view: View) {
        var email: String = findViewById<EditText>(R.id.email_edit_text).text.toString()
        var password: String = findViewById<EditText>(R.id.password_edit_text).text.toString()
        var nom: String = findViewById<EditText>(R.id.editTextNom).text.toString()
        var prenom: String = findViewById<EditText>(R.id.editTextPrenom).text.toString()
        //var role: String = findViewById<EditText>(R.id.role_edit_text).text.toString()
        uploadImageBtn.visibility=View.VISIBLE
        if (nom.isEmpty() || password.isEmpty() || email.isEmpty() || prenom.isEmpty() )
            {
                Toast.makeText(this, "Merci de remplir les champs", Toast.LENGTH_LONG).show()
            }
        else {
            val user = HashMap<String, Any>()
            user["Email"] = email
            user["Nom"] = nom
            user["Prenom"] = prenom
            user["Role"] = "Collaborateur"
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "User added ", Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(this, "User not added ", Toast.LENGTH_LONG).show()
                    }
                }
        }
    }
    public fun addUser(profileImageUrl:String){
        val uid = FirebaseAuth.getInstance().uid ?: return
        var email: String = findViewById<EditText>(R.id.email_edit_text).text.toString()
        var nomc: String = findViewById<EditText>(R.id.editTextNom).text.toString()
        var prenom: String = findViewById<EditText>(R.id.editTextPrenom).text.toString()
        val add = HashMap<String, Any>()
        add["UID"] = uid
        add["Nom"] = nomc
        add["Prenom"] = prenom
        add["Email"] = email
        add["Role"] = "Collaborateur"
        add["Url"] = profileImageUrl
        dbf.collection("Users")
            .document(nomc).set(add)
            .addOnSuccessListener {
                Toast.makeText(this, "Data added ", Toast.LENGTH_LONG).show()
                var intent1 :Intent= getIntent()
                var user = intent1.getStringExtra("user").toString()
                var nom = intent1.getStringExtra("nom").toString()
                var role = intent1.getStringExtra("role").toString()
                val intent: Intent =  Intent(applicationContext, MenuCollabActivity::class.java)
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
    public fun retour(view: View)
    {
        var intent1 :Intent= getIntent()
        var user = intent1.getStringExtra("user").toString()
        var nom = intent1.getStringExtra("nom").toString()
        var role = intent1.getStringExtra("role").toString()
        val intent: Intent =  Intent(applicationContext, MenuCollabActivity::class.java)
        intent.putExtra("user", user)
        intent.putExtra("role", role)
        intent.putExtra("nom", nom)
        startActivity(intent)
    }
}