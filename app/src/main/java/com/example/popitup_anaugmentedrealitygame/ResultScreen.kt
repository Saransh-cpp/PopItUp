package com.example.popitup_anaugmentedrealitygame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.popitup_anaugmentedrealitygame.daos.UserDao
import com.example.popitup_anaugmentedrealitygame.models.User
import kotlinx.android.synthetic.main.activity_result_screen.*

class ResultScreen : AppCompatActivity() {

    private lateinit var userDao: UserDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_screen)

        userDao = UserDao()

        resultText.text = intent.getStringExtra("time") + "seconds"
        userDao.updateScore(intent.getStringExtra("time")!!.toInt())


    }
}