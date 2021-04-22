package com.example.popitup_anaugmentedrealitygame.daos

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import com.example.popitup_anaugmentedrealitygame.models.User
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class UserDao {

    private val db = FirebaseFirestore.getInstance()
    val usersCollection = db.collection("users")
    private val auth = Firebase.auth

    fun addUser(user: User?) {
        user?.let {
            GlobalScope.launch(Dispatchers.IO) {
                usersCollection.document(user.uid).set(it)
            }
        }
    }

    fun getUserById(uId: String): Task<DocumentSnapshot>? {
        var success: Task<DocumentSnapshot>? = null
        usersCollection.document(uId).get().addOnCompleteListener {
            if (it.isSuccessful) {
                success = usersCollection.document(uId).get()
            }
        }
        return success
    }

    fun updateScore(highScore: Int) {
        GlobalScope.launch {
            val currentUserId = auth.currentUser!!.uid
            val user = getUserById(currentUserId)?.await()?.toObject(User::class.java)!!

            if (highScore < user.highScore) {
                user.highScore = highScore
            }
            usersCollection.document(currentUserId).set(user)
        }
    }
}