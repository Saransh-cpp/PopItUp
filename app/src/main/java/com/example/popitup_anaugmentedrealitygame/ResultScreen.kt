package com.example.popitup_anaugmentedrealitygame

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.popitup_anaugmentedrealitygame.daos.ScoreDao
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_result_screen.*
import kotlinx.android.synthetic.main.item_leaderboard.*


class ResultScreen : AppCompatActivity() {

    private lateinit var scoreDao: ScoreDao
    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore
    private lateinit var currentUserId: String
    private lateinit var currentUserScoreId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_screen)

        scoreDao = ScoreDao()
        auth = Firebase.auth
        firestore = FirebaseFirestore.getInstance()
        currentUserId = auth.currentUser!!.uid

        val scoreFinal: Int = intent.getStringExtra("time")!!.toInt()
        resultText.text = "$scoreFinal"

        var isFound: Boolean = false
        currentUserScoreId = ""
        Toast.makeText(this@ResultScreen, "Updating the score, please wait :D", Toast.LENGTH_LONG).show()

        firestore.collection("scores")
                .get()
                .addOnCompleteListener {
                    val snapshots = it.result?.documents
                    if (snapshots != null) {
                        for (snapshot: DocumentSnapshot in snapshots) {
                            if (snapshot["createdBy.uid"] == currentUserId) {
                                isFound = true
//                                Toast.makeText(this@ResultScreen, snapshot.id, Toast.LENGTH_LONG).show()
                                break
                            }
                        }
                        if (!isFound) {
                            Toast.makeText(this@ResultScreen, "Score added, check the leaderboard:)", Toast.LENGTH_LONG).show()
                            scoreDao.addScore(scoreFinal)
                        } else {
                            firestore.collection("scores")
                                    .whereEqualTo("createdBy.uid", currentUserId)
                                    .get()
                                    .addOnSuccessListener { result ->
                                        for (document in result) {
                                            currentUserScoreId = document.id
                                        }
                                        scoreDao.updateScore(currentUserScoreId, scoreFinal)
                                        Toast.makeText(this@ResultScreen, "Score updated", Toast.LENGTH_LONG).show()
                                    }
                        }
                    }
                }

//        firestore.collection("scores")
//                .whereEqualTo("createdBy.uid", currentUserId)
//                .get()
//                .addOnSuccessListener { result ->
//                    for (document in result) {
//                        currentUserScoreId = document.id
//                    }
//                    scoreDao.updateScore(currentUserScoreId, scoreFinal)
//                    Toast.makeText(this@ResultScreen, "Score registered", Toast.LENGTH_LONG).show()
//                }

    }
}