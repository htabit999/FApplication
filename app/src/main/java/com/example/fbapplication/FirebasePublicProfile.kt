package com.example.fbapplication

import com.google.firebase.firestore.ServerTimestamp
import java.security.Timestamp

data class FirebasePublicProfile(
    val userData: ArrayList<String>,
    val lowerCaseUsername: String = "",
    val totalMessages: Int = 0,
    @ServerTimestamp val lastLogin: Timestamp? = null
)
