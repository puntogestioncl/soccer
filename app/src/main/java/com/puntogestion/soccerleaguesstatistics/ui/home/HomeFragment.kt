package com.puntogestion.soccerleaguesstatistics.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.puntogestion.soccerleaguesstatistics.R
import com.puntogestion.soccerleaguesstatistics.db.MatchRepository
import com.puntogestion.soccerleaguesstatistics.util.dateFormat
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by activityViewModels()
    private var adapterMatch: MatchAdapter = MatchAdapter(mutableListOf())

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rMatchDay.adapter = adapterMatch

        homeViewModel.getMatches.observe(viewLifecycleOwner, Observer {
            textViewDate.text = dateFormat(it.get(0).utcDate, "dd-MM-yyyy")
            Log.d("Api***", "$it")
            adapterMatch.updateItem(it)
        })

    }
}