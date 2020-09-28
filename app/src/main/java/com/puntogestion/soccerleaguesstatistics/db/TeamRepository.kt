package com.puntogestion.soccerleaguesstatistics.db

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import com.puntogestion.soccerleaguesstatistics.db.local.PlayersDao
import com.puntogestion.soccerleaguesstatistics.db.local.PlayersEntity
import com.puntogestion.soccerleaguesstatistics.db.local.SoccerDataBase
import com.puntogestion.soccerleaguesstatistics.db.local.TeamDao
import com.puntogestion.soccerleaguesstatistics.db.remote.PlayersTeam
import com.puntogestion.soccerleaguesstatistics.db.remote.RetrofitClient
import com.puntogestion.soccerleaguesstatistics.db.remote.Squad
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TeamRepository(context: Context) {

    var dataBaseInstance: SoccerDataBase = SoccerDataBase.getDataBaseInstance(context)

    var playersTeamDao: PlayersDao = dataBaseInstance.playersTeamDao()

    fun getAllPlayersTeam(idTeam:Int): LiveData<List<PlayersEntity>> {
        return playersTeamDao.getAllPlayersTeam(idTeam)
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