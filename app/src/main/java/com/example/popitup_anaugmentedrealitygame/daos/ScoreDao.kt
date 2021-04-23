package com.example.popitup_anaugmentedrealitygame.daos

import com.example.popitup_anaugmentedrealitygame.models.Score
import com.example.popitup_anaugmentedrealitygame.models.User
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class ScoreDao {
    private val db = FirebaseFirestore.getInstance()
    val scoreCollection = db.collection("scores")
    private val auth = Firebase.auth

    fun addScore(highScore: Int) {
        GlobalScope.launch {
            val currentUserId = auth.currentUser!!.uid
            val userDao = UserDao()
            val user = userDao.getUserById(currentUserId).await().toObject(User::class.java)!!

            val score = Score(highScore, user)
            scoreCollection.document().set(score)
        }
    }

    fun getScoreById(scoreId: String): Task<DocumentSnapshot> {
        return scoreCollection.document(scoreId).get()
    }

    fun updateScore(scoreId: String, highScore: Int) {
        GlobalScope.launch {
            val currentUserId = auth.currentUser!!.uid
            val score = getScoreById(scoreId).await().toObject(Score::class.java)!!

            if (highScore < score.highScore) {
                score.highScore = highScore
            }
            scoreCollection.document(scoreId).set(score)
        }
    }
}