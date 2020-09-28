package com.puntogestion.soccerleaguesstatistics.db.remote

data class Score(
    val duration: String,
    val fullTime: FullTime,
    val winner: String
)