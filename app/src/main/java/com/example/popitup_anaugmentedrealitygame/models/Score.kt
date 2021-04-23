package com.example.popitup_anaugmentedrealitygame.models

data class Score (
        var highScore: Int = Int.MAX_VALUE,
        val createdBy: User = User(),
)