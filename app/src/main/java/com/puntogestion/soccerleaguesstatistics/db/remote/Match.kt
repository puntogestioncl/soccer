package com.puntogestion.soccerleaguesstatistics.db.remote

data class Match(
    val awayTeam: AwayTeam,
    val competition: CompetitionMatch,
    val homeTeam: HomeTeam,
    val id: Int,
    val score: Score,
    val status: String,
    val utcDate: String
)