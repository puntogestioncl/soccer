package com.puntogestion.soccerleaguesstatistics.db.local

import androidx.annotation.Nullable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "players_team")
data class PlayersEntity(
    val countryOfBirth: String,
    val dateOfBirth: String,
    @PrimaryKey
    var id: Int,
    val id_team: Int,
    val name: String,
    val nationality: String,
    @Nullable
    val position: String?,
    val role: String,
    @Nullable
    val shirtNumber: Int?
)