package com.puntogestion.soccerleaguesstatistics.db.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TeamDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTeams(teams: List<TeamEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTeam(team: TeamEntity)

    @Query("Select * from teams")
    fun getAllTeams() : LiveData<List<TeamEntity>>

    @Query("Select * from teams where area_id = :id")
    fun getTeamsForCompetitions(id:Int) : LiveData<List<TeamEntity>>
}