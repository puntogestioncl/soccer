package com.puntogestion.soccerleaguesstatistics.db.remote

data class Competition(
    val area: Area,
    val code: String,
    val id: Int,
    val lastUpdated: String,
    val name: String,
    val plan: String
)