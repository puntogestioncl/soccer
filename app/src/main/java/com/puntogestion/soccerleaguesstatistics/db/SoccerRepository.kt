package com.puntogestion.soccerleaguesstatistics.db

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import com.puntogestion.soccerleaguesstatistics.db.local.*
import com.puntogestion.soccerleaguesstatistics.db.remote.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SoccerRepository(context: Context) {

    private var listTeams = ArrayList<TeamEntity>()

    var idArea :Int = 0

    var dataBaseInstance = SoccerDataBase.getDataBaseInstance(context)

    var teamDao: TeamDao = dataBaseInstance.teamDao()
    var playersTeamDao: PlayersDao = dataBaseInstance.playersTeamDao()

    fun getAllTeamLiga(idLiga:Int): LiveData<List<TeamEntity>> {
        when(idLiga){
            2002 -> idArea = 2088
            2003 -> idArea = 2163
            2014 -> idArea = 2224
            2015 -> idArea = 2081
            2017 -> idArea = 2187
            2019 -> idArea = 2114
            2021 -> idArea = 2072
        }
        return teamDao.getTeamsForCompetitions(idArea)
    }

    fun getAllPlayersTeam(idTeam:Int): LiveData<List<PlayersEntity>> {
        return playersTeamDao.getAllPlayersTeam(idTeam)
    }

    fun loadApiCompetitions(idLiga: Int) {
        val call = RetrofitClient.retrofitInstance().teamsOfCompetition(idLiga)
        call.enqueue(object : Callback<Competitions> {
            override fun onResponse(
                call: Call<Competitions>,
                response: Response<Competitions>
            ) {
                Log.d("Api", "${response.body()!!.teams}")
                saveTeam(response.body()?.teams)
            }

            override fun onFailure(call: Call<Competitions>, t: Throwable) {
                Log.d("Api", "$t")
            }
        })
    }

    private fun saveTeam(teams: List<Team>?) {
        teams?.map {
            listTeams.add(
                TeamEntity(it.address, it.area, it.clubColors, it.crestUrl, it.email, it.founded, it.id, it.lastUpdated, it.name, it.phone, it.shortName, it.tla, it.venue, it.website)
            )
            CoroutineScope(Dispatchers.Main).launch {
                //Log.d("repository","lista ${photolist.size}")
                for (team in listTeams) {
                    teamDao.insertTeam(team)
                }
            }
        }
    }


    fun loadApiTeam(id: Int) {
        val call = RetrofitClient.retrofitInstance().playersTeam(id)
        call.enqueue(object : Callback<PlayersTeam> {
            override fun onResponse(
                call: Call<PlayersTeam>,
                response: Response<PlayersTeam>
            ) {
                Log.d("Api", "${response.body()}")
                savePlayersTeam(response.body()!!.squad, id)
            }

            override fun onFailure(call: Call<PlayersTeam>, t: Throwable) {
                Log.d("Api", "$t")
            }
        })
    }

    private fun savePlayersTeam(playersTeam: List<Squad>, id: Int) {
        for (players in playersTeam) {
            CoroutineScope(Dispatchers.IO).launch {
                playersTeamDao.insertPlayersTeam(
                    PlayersEntity(
                        players.countryOfBirth,
                        players.dateOfBirth,
                        players.id,
                        id,
                        players.name,
                        players.nationality,
                        players.position,
                        players.role,
                        players.shirtNumber
                    )
                )
            }
        }
    }
}