package com.puntogestion.soccerleaguesstatistics.db.remote

data class CompetitionMatch(
    val id: Int,
    val name: String,
    val area : AreaMatch
)