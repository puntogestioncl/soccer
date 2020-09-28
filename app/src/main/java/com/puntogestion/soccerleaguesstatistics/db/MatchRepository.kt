package com.puntogestion.soccerleaguesstatistics.db

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.puntogestion.soccerleaguesstatistics.db.remote.Match
import com.puntogestion.soccerleaguesstatistics.db.remote.MatchesDay
import com.puntogestion.soccerleaguesstatistics.db.remote.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MatchRepository(context: Context) {

    var listMatch = MutableLiveData<List<Match>>()

    fun loadApiMatch(){
        val call = RetrofitClient.retrofitInstance().matchesDay()

        call.enqueue(object : Callback<MatchesDay>{
            override fun onResponse(call: Call<MatchesDay>, response: Response<MatchesDay>) {
                Log.d("Api---", "${response.body()?.matches}")
                listMatch.value = response.body()?.matches
            }

            override fun onFailure(call: Call<MatchesDay>, t: Throwable) {
                Log.d("Api", "$t")
            }

        })
    }
}