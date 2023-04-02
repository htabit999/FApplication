package com.example.fbapplication

import android.app.Activity
import android.content.ContentResolver
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import java.io.ByteArrayOutputStream
import com.bumptech.glide.Glide
import java.io.IOException
import java.util.*


class ImageActivity : AppCompatActivity() {
    private val TAG = ImageActivity::class.java.simpleName
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image)
        val button = findViewById<Button>(R.id.testButton)
        val imageView = findViewById<ImageView>(R.id.imageView)
        val uploadButton = findViewById<Button>(R.id.uploadButton)
        // 다운로드
        button.setOnClickListener {
            val ref = FirebaseStorage.getInstance().getReference("bokchi.jpg")
            ref.downloadUrl
                .addOnCompleteListener(OnCompleteListener { task ->
                    if (task.isSuccessful) {
                            Glide.with(this)
                            .load(task.result)
                            .into(imageView)

                    } else {
                        Log.e(TAG, "error")
                    }

                })
        }
        // 업로
        uploadButton.setOnClickListener {
            val mountainsRef = FirebaseStorage.getInstance().getReference().child("aaa")
            // Get the data from an ImageView as bytes
            imageView.isDrawingCacheEnabled = true
            imageView.buildDrawingCache()
            val bitmap = (imageView.drawable as BitmapDrawable).bitmap
            val baos = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
            val data = baos.toByteArray()
            var uploadTask = mountainsRef.putBytes(data)
            uploadTask.addOnFailureListener {
                // Handle unsuccessful uploads
            }.addOnSuccessListener { taskSnapshot ->
                // taskSnapshot.metadata contains file metadata such as size, content-type, etc.
                // ...
            }
        }
    }
}
