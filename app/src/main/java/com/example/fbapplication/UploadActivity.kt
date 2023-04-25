package com.example.fbapplication

import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.util.*

class UploadActivity : AppCompatActivity() {
    lateinit var chooseImageBtn: Button
    lateinit var uploadImageBtn: Button
    lateinit var imageView: ImageView
    lateinit var progressbar:ProgressBar
    var role: String? = null
    var fileUri: Uri? = null
    var nom: String? = null
    var prenom: String? = null
    var psw: String? = null
    var rpsw: String? = null
    var email: String? = null
    var url: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload)
        progressbar = findViewById(R.id.progressBar)
        chooseImageBtn = findViewById(R.id.show_btn)
        uploadImageBtn = findViewById(R.id.upload_btn)
        imageView = findViewById(R.id.imageView)

        val role = intent.getStringExtra("role")
        val nom = intent.getStringExtra("nom")
        val prenom = intent.getStringExtra("prenom")
        val email = intent.getStringExtra("email")
        val psw = intent.getStringExtra("psw")
        val rpsw = intent.getStringExtra("rpsw")

        progressbar.visibility= View.GONE
        chooseImageBtn.setOnClickListener {
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(
                Intent.createChooser(
                    intent,
                    "Pick your image to upload"
                ),
                22
            )
        }
        uploadImageBtn.setOnClickListener {
            uploadImage()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 22 && resultCode == RESULT_OK && data != null && data.data != null) {
            fileUri = data.data
            try {
                val bitmap: Bitmap = MediaStore.Images.Media.getBitmap(contentResolver, fileUri);
                imageView.setImageBitmap(bitmap)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun uploadImage() {
        progressbar.visibility= View.VISIBLE
        val uid = FirebaseAuth.getInstance().currentUser!!.uid
        email = intent.getStringExtra("email")
        if (fileUri != null) {

            val progressDialog = ProgressDialog(this)
            progressDialog.setTitle("Uploading...")
            progressDialog.setMessage("Uploading your image..")
            progressDialog.show()
            val ref: StorageReference = FirebaseStorage.getInstance().getReference()
                .child("Photos")
            Toast.makeText(applicationContext, fileUri!!.toString(), Toast.LENGTH_SHORT).show()
            ref.putFile(fileUri!!).addOnSuccessListener {
                progressDialog.dismiss()
                Toast.makeText(applicationContext, "Image Uploaded..", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                progressDialog.dismiss()
                Toast.makeText(applicationContext, "Fail to Upload Image..", Toast.LENGTH_SHORT)
                    .show()
            }
        }
        progressbar.visibility= View.GONE
        val role = intent.getStringExtra("role")
        val nom = intent.getStringExtra("nom")
        val prenom = intent.getStringExtra("prenom")
        val email = intent.getStringExtra("email")
        val psw = intent.getStringExtra("psw")
        val rpsw = intent.getStringExtra("rpsw")
        val intent = Intent(this, ChefProjetActivity::class.java)
        intent.putExtra("email", email)
        intent.putExtra("role", role)
        intent.putExtra("nom", nom)
        intent.putExtra("prenom", prenom)
        intent.putExtra("psw", psw)
        intent.putExtra("rpsw", rpsw)
        intent.putExtra("url", UUID.randomUUID().toString())
        startActivity(intent)
    }
}