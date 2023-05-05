package com.example.fbapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fbapplication.models.Usr
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import java.util.HashMap

class ChatActivity : AppCompatActivity() {
    private lateinit var chatRecyclerView: RecyclerView
    private lateinit var image:CircleImageView
    private lateinit var messageBox: EditText
    private lateinit var sendButton: ImageView
    private lateinit var messageAdapter: MessageAdapter
    private lateinit var messageList: ArrayList<Message>
    private var senderRoom: String? = null
    private var receiverRoom: String? = null
    private var dbf = Firebase.firestore
    private var dbRef = FirebaseDatabase.getInstance().getReference("projet-4f405")
    private var nom: String? = null
    private var role: String? = null
    private var user: String? = null
    private var url: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        val name = intent.getStringExtra("name")
        val receiverUid = intent.getStringExtra("uid")
        url = intent.getStringExtra("url")
        val senderUid = FirebaseAuth.getInstance().currentUser?.uid
        //Toast.makeText(this, "SendUid "+senderUid, Toast.LENGTH_LONG).show()
        senderRoom = receiverUid + senderUid
        receiverRoom = senderUid + receiverUid
        supportActionBar?.title = name
        chatRecyclerView = findViewById(R.id.ChatRecyclerView)
        messageBox = findViewById(R.id.messageBox)
        sendButton = findViewById(R.id.sendButton)
        image=findViewById(R.id.profile)
        messageList = ArrayList()
        messageAdapter = MessageAdapter(this, messageList)
        chatRecyclerView.layoutManager = LinearLayoutManager(this)
        chatRecyclerView.adapter = messageAdapter
        Picasso.get().load(url).into(image)

        var list = ArrayList<Usr>()
        val uid = FirebaseAuth.getInstance().currentUser!!.uid
        dbf.collection("Users").whereEqualTo("UID", uid).get()
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
                     role=list[0].ROLE
                     nom=list[0].NOM
                     user=list[0].UID
                     url=list[0].URL
                }
            }

        dbRef.child("Chat").child(senderRoom!!).child("Messages")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    messageList.clear()
                    for (postSnapshot in snapshot.children) {
                        val message = postSnapshot.getValue(Message::class.java)
                        messageList.add(message!!)
                    }
                    messageAdapter.notifyDataSetChanged()
                }
                override fun onCancelled(error: DatabaseError) {
                }
            }
            )

        sendButton.setOnClickListener {
           //Toast.makeText(this, "sendButton", Toast.LENGTH_LONG).show()
            val message = messageBox.text.toString()
            val messageObject = Message(message, senderUid!!,System.currentTimeMillis() / 1000)
            dbRef.child("Chat").child(senderRoom!!).child("Messages").push()
                .setValue(messageObject)
                .addOnSuccessListener {
                    dbRef.child("Chat").child(receiverRoom!!).child("Messages").push()
                        .setValue(messageObject)
                }
            messageBox.setText("")
        }
    }
    fun retour(view: View)
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