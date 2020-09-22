package com.puntogestion.soccerleaguesstatistics.db.local

import androidx.annotation.Nullable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.puntogestion.soccerleaguesstatistics.db.remote.Area

@Entity(tableName = "teams")
data class TeamEntity(
    val address: String,
    @Embedded(prefix = "area_")
    val area: Area,
    @Nullable
    val clubColors: String?,
    val crestUrl: String,
    @Nullable
    val email: String?,
    val founded: Int,
    @PrimaryKey
    var id: Int,
    val lastUpdated: String,
    val name: String,
    @Nullable
    val phone: String?,
    val shortName: String,
    val tla: String,
    val venue: String,
    val website: String
)