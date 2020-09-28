package com.puntogestion.soccerleaguesstatistics.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.puntogestion.soccerleaguesstatistics.db.MatchRepository
import com.puntogestion.soccerleaguesstatistics.db.SoccerRepository
import com.puntogestion.soccerleaguesstatistics.db.remote.Match

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private var repository: MatchRepository = MatchRepository(application)
    var datos = repository.loadApiMatch()
    var getMatches: LiveData<List<Match>> = repository.listMatch
}