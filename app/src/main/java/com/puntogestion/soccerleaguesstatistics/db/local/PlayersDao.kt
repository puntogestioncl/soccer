package com.puntogestion.soccerleaguesstatistics.db.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PlayersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlayersTeam(playersTeamEntity: PlayersEntity)

    @Query("Select * from players_team where id_team = :id_team")
    fun getAllPlayersTeam(id_team:Int): LiveData<List<PlayersEntity>>
}