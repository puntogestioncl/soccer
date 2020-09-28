package com.puntogestion.soccerleaguesstatistics.db

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import com.puntogestion.soccerleaguesstatistics.db.local.*
import com.puntogestion.soccerleaguesstatistics.db.remote.*
import com.puntogestion.soccerleaguesstatistics.util.area
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SoccerRepository(context: Context) {

    private var listTeams = ArrayList<TeamEntity>()

    var idArea :Int = 0

    var dataBaseInstance: SoccerDataBase = SoccerDataBase.getDataBaseInstance(context)

    var teamDao: TeamDao = dataBaseInstance.teamDao()

    fun getAllTeamLiga(idLiga:Int): LiveData<List<TeamEntity>> {
        idArea = area(idLiga)
        return teamDao.getTeamsForCompetitions(idArea)
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
}