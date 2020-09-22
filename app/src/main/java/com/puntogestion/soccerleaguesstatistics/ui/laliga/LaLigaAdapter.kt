package com.puntogestion.soccerleaguesstatistics.ui.laliga

import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.puntogestion.soccerleaguesstatistics.R
import com.puntogestion.soccerleaguesstatistics.db.local.TeamEntity
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou
import kotlinx.android.synthetic.main.teams_layaout.view.*

class LaLigaAdapter(private var teamsDataSet: MutableList<TeamEntity> ): RecyclerView.Adapter<LaLigaAdapter.LaLigaViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LaLigaAdapter.LaLigaViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.teams_layaout, parent, false)
        return LaLigaViewHolder(view)
    }

    var team = MutableLiveData<TeamEntity>()

    override fun onBindViewHolder(holder: LaLigaAdapter.LaLigaViewHolder, position: Int) {

        holder.textName.text = teamsDataSet.get(position).name
        GlideToVectorYou.init().with(holder.imageView.context).load(Uri.parse(teamsDataSet.get(position).crestUrl), holder.imageView)

        holder.itemView.setOnClickListener {
            Log.d("Api+++", "${teamsDataSet.get(position)}")
            team.postValue(teamsDataSet.get(position))
        }
    }

    override fun getItemCount(): Int {
        return teamsDataSet.size
    }

    fun updateItems(data: List<TeamEntity>) {
        teamsDataSet.clear()
        teamsDataSet.addAll(data)
        notifyDataSetChanged()
    }

    class LaLigaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textName = itemView.textViewNameTeam
        var imageView = itemView.imageViewLogo
    }
}