package com.puntogestion.soccerleaguesstatistics.ui.laliga

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.puntogestion.soccerleaguesstatistics.R
import kotlinx.android.synthetic.main.la_liga_fragment.*

class LaLigaFragment : Fragment() {

    companion object {
        fun newInstance() = LaLigaFragment()
    }

    private val viewModel: LaLigaViewModel by activityViewModels()
    private var idLiga: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Log.d("Api***", "${arguments!!.getInt("idLiga")}")
        idLiga = arguments!!.getInt("idLiga")
        return inflater.inflate(R.layout.la_liga_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        var adaptador = LaLigaAdapter(mutableListOf())
        rLaLiga.layoutManager = GridLayoutManager(context, 2)
        rLaLiga.adapter = adaptador

        viewModel.getTeamAllLiga(idLiga).observe(viewLifecycleOwner, Observer {
            adaptador.updateItems(it)
        })

        adaptador.team.observe(viewLifecycleOwner, Observer {
            viewModel.select(it)
            Log.d("Api---", "$it")
            view?.findNavController()?.navigate(R.id.teamFragment)
        })
    }
}