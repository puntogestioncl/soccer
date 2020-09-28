package com.puntogestion.soccerleaguesstatistics.util

import android.content.Context
import android.view.View
import android.view.ViewAnimationUtils
import com.puntogestion.soccerleaguesstatistics.R
import java.text.SimpleDateFormat
import java.util.*

fun dateFormat(dateMatch:String, formato:String): String{
    val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
    val date = dateFormat.parse(dateMatch)
    val formatter = SimpleDateFormat(formato, Locale.getDefault())
    val dateStr = formatter.format(date)
    return dateStr
}

fun logo(idLiga: Int): Int {
    var idLogo: Int = 0
    when (idLiga) {
        2002 -> idLogo = R.drawable.ic_bundesliga
        2003 -> idLogo = R.drawable.ic_eredivisie
        2014 -> idLogo = R.drawable.ic_laliga
        2015 -> idLogo = R.drawable.ic_ligue1
        2017 -> idLogo = R.drawable.ic_liganos
        2019 -> idLogo = R.drawable.ic_seriea
        2021 -> idLogo = R.drawable.ic_premierleague
    }
    return idLogo
}

fun title(idLiga: Int, context: Context): String {
    var title: String = R.string.app_name.toString()
    when (idLiga) {
        2002 -> title = context.resources.getText(R.string.menu_bundesliga).toString()
        2003 -> title = context.resources.getText(R.string.menu_eredivisie).toString()
        2014 -> title = context.resources.getText(R.string.menu_laliga).toString()
        2015 -> title = context.resources.getText(R.string.menu_ligue1).toString()
        2017 -> title = context.resources.getText(R.string.menu_primeiraliga).toString()
        2019 -> title = context.resources.getText(R.string.menu_seriea).toString()
        2021 -> title = context.resources.getText(R.string.menu_premierleague).toString()
    }
    return title
}

fun area(idLiga:Int): Int{
    var idArea: Int = 0
    when(idLiga){
        2002 -> idArea = 2088
        2003 -> idArea = 2163
        2014 -> idArea = 2224
        2015 -> idArea = 2081
        2017 -> idArea = 2187
        2019 -> idArea = 2114
        2021 -> idArea = 2072
    }
    return idArea
}

fun circularAnimation(view: View){
    var centerX = 0
    var centerY = 0
    var startRadius = 0F
    var endRadius: Int = Math.max(view.width, view.height)
    var animation = ViewAnimationUtils.createCircularReveal(view, centerX, centerY, startRadius, endRadius.toFloat())
    view.visibility = View.VISIBLE
    animation.start()
}