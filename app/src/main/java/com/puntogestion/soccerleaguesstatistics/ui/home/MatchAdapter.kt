package com.puntogestion.soccerleaguesstatistics.ui.home

import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou
import com.puntogestion.soccerleaguesstatistics.R
import com.puntogestion.soccerleaguesstatistics.db.remote.Match
import com.puntogestion.soccerleaguesstatistics.util.dateFormat
import kotlinx.android.synthetic.main.match_day_layout.view.*
import java.text.SimpleDateFormat
import java.util.*

class MatchAdapter(private var mDataSetMatch: MutableList<Match>): RecyclerView.Adapter<MatchAdapter.MatchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.match_day_layout , parent, false)
        return MatchViewHolder(view)
    }

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        holder.textHome.text = mDataSetMatch.get(position).homeTeam.name
        holder.textAway.text = mDataSetMatch.get(position).awayTeam.name
        holder.textDate.text = dateFormat(mDataSetMatch.get(position).utcDate, "HH:mm")
        when(mDataSetMatch.get(position).competition.area.ensignUrl){
            null -> Glide.with(holder.itemView.context).load(R.drawable.ic_logo).into(holder.imageLogo)
            else -> GlideToVectorYou.init().with(holder.itemView.context).load(Uri.parse(mDataSetMatch.get(position).competition.area.ensignUrl), holder.imageLogo)
        }
    }

    override fun getItemCount(): Int {
        return mDataSetMatch.size
    }

    fun updateItem(it: List<Match>?) {
        Log.d("Api+++", "$it")
        mDataSetMatch.clear()
        if (it != null) {
            mDataSetMatch.addAll(it)
        }
        notifyDataSetChanged()
    }

    class MatchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textHome = itemView.textViewHomeTeam
        var textAway = itemView.textViewAwayTeam
        var textDate = itemView.textViewDate
        var imageLogo = itemView.imageViewLogoCompetitions
    }
}
