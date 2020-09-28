package com.puntogestion.soccerleaguesstatistics.db.remote

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface SoccerApi {

    @GET("/v2/competitions/2021")
    fun competion() : Call<Competition>

    @GET("/v2/competitions/{id}/teams")
    fun teamsOfCompetition(@Path("id") id: Int ) : Call<Competitions>

    @GET("/v2/teams/{id}")
    fun playersTeam(@Path("id") id: Int ): Call<PlayersTeam>

    @GET("/v2/matches")
    fun matchesDay() : Call<MatchesDay>
}