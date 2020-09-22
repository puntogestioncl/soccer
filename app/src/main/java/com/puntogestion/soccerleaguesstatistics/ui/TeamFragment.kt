package com.puntogestion.soccerleaguesstatistics.ui

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.puntogestion.soccerleaguesstatistics.R
import com.puntogestion.soccerleaguesstatistics.ui.laliga.LaLigaViewModel
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou
import kotlinx.android.synthetic.main.fragment_team.*

class TeamFragment : Fragment() {

    private val viewModel: LaLigaViewModel by activityViewModels()

    companion object {
        @JvmStatic
        fun newInstance() = TeamFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_team, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.selected.observe(viewLifecycleOwner, Observer {
            GlideToVectorYou.init().with(view.context).load(Uri.parse(it.crestUrl), imageViewLogo)
            textViewTitle.text = it.name
            textViewFounded.text = "Fundado : ${it.founded.toString()}"
            textViewNameEstadio.text = it.venue
            textViewSiteWeb.text = it.website
        })

        var adapter:TeamAdapter = TeamAdapter(mutableListOf())
        rPlayers.adapter = adapter

        viewModel.getAllPlayersTeam().observe(viewLifecycleOwner, Observer {
            Log.d("Api-+-+", "${it}")
            adapter.updateItems(it)
        })
    }
}