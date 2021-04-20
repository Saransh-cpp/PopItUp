package com.example.popitup_anaugmentedrealitygame.models

data class User(val uid: String = "",
                val displayName: String? = "",
                val imageUrl: String = "",
                var highScore: Int = 0)