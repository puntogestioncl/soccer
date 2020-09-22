package com.puntogestion.soccerleaguesstatistics.ui.laliga

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.puntogestion.soccerleaguesstatistics.db.SoccerRepository
import com.puntogestion.soccerleaguesstatistics.db.local.PlayersEntity
import com.puntogestion.soccerleaguesstatistics.db.local.TeamEntity

class LaLigaViewModel(application: Application) : AndroidViewModel(application) {

    val selected = MutableLiveData<TeamEntity>()

    fun select(team: TeamEntity?) {
        selected.value =  team
    }

    private val repositorySoccerRepository: SoccerRepository = SoccerRepository(application)

    fun getTeamAllLiga(idLiga:Int): LiveData<List<TeamEntity>> {
        val soccer = repositorySoccerRepository.loadApiCompetitions(idLiga)
        return repositorySoccerRepository.getAllTeamLiga(idLiga)
    }


    fun getAllPlayersTeam() : LiveData<List<PlayersEntity>> {
        var id = selected.value!!.id
        //Log.d("Api{{{", "$id")
        val players = repositorySoccerRepository.loadApiTeam(id)
        return repositorySoccerRepository.getAllPlayersTeam(id)
    }
}