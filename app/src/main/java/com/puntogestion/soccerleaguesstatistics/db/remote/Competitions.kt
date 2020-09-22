package com.puntogestion.soccerleaguesstatistics.db.remote

data class Competitions(
    val competition: Competition,
    val teams: List<Team>
)