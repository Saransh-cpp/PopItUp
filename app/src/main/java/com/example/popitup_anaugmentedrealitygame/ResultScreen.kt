package com.example.popitup_anaugmentedrealitygame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.popitup_anaugmentedrealitygame.daos.ScoreDao
import com.example.popitup_anaugmentedrealitygame.daos.UserDao
import com.example.popitup_anaugmentedrealitygame.models.Score
import com.example.popitup_anaugmentedrealitygame.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_result_screen.*

class ResultScreen : AppCompatActivity() {

    private lateinit var scoreDao: ScoreDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_screen)

        scoreDao = ScoreDao()
//        val auth = Firebase.auth
//        val firestore = FirebaseFirestore.getInstance()
//        val currentUserId = auth.currentUser!!.uid

//        firestore.collection("scores").whereEqualTo("uid", currentUserId)
//            .get()
//            .addOnCompleteListener {
//                if (it.isSuccessful) {
//                    val documents = snapshot.documents
//                }
//            }
        resultText.text = intent.getStringExtra("time") + "seconds"
        intent.getStringExtra("time")?.let { scoreDao.addScore(it.toInt()) }


    }
}