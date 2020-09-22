package com.puntogestion.soccerleaguesstatistics.ui

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.puntogestion.soccerleaguesstatistics.R
import com.puntogestion.soccerleaguesstatistics.db.local.PlayersEntity
import kotlinx.android.synthetic.main.play_position_layout.view.*

class TeamAdapter(private var listPlayersEntity: MutableList<PlayersEntity>): RecyclerView.Adapter<TeamAdapter.TeamViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.play_position_layout, parent, false)
        return TeamViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        holder.textName.text = listPlayersEntity.get(position).name
        holder.textNacionality.text = listPlayersEntity.get(position).nationality
        holder.textDateOfBirth.text = listPlayersEntity.get(position).dateOfBirth
        var date = listPlayersEntity.get(position).dateOfBirth
        date.replace("T00:00:00Z", "",true)
        when(listPlayersEntity.get(position).position){
                null -> holder.textPosition.text = listPlayersEntity.get(position).role
                else -> holder.textPosition.text = listPlayersEntity.get(position).position
        }

        when(listPlayersEntity.get(position).position){
            "Goalkeeper" -> Glide.with(holder.itemView.context).load(R.drawable.ic_goalkeeper).into(holder.imagePlay)
            "Defender" -> Glide.with(holder.itemView.context).load(R.drawable.ic_defender).into(holder.imagePlay)
            "Midfielder" -> Glide.with(holder.itemView.context).load(R.drawable.ic_midfielder).into(holder.imagePlay)
            "Attacker" -> Glide.with(holder.itemView.context).load(R.drawable.ic_attacker).into(holder.imagePlay)
            else -> when(listPlayersEntity.get(position).role){
                "COACH" -> Glide.with(holder.itemView.context).load(R.drawable.ic_coach).into(holder.imagePlay)
                "ASSISTANT_COACH" -> Glide.with(holder.itemView.context).load(R.drawable.ic_coach).into(holder.imagePlay)
            }
        }

    }

    override fun getItemCount(): Int {
        return listPlayersEntity.size
    }

    fun updateItems(players: List<PlayersEntity>?) {
        listPlayersEntity.clear()
        players?.let { listPlayersEntity.addAll(it) }
        notifyDataSetChanged()
    }

    class TeamViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textName = itemView.textViewName
        var textNacionality = itemView.textViewNationality
        var textDateOfBirth = itemView.textViewDateOfBirth
        var textPosition = itemView.textViewPosition
        var imagePlay = itemView.imageViewPlayPosition
    }
}