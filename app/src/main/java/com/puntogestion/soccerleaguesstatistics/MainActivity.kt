package com.puntogestion.soccerleaguesstatistics

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.os.bundleOf
import androidx.core.view.GravityCompat
import androidx.navigation.NavOptions
import androidx.navigation.ui.*


class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(setOf(
                R.id.nav_home, R.id.nav_laliga), drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        navView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.nav_laliga -> {
                    val bundle = bundleOf(Pair("idLiga", 2014))
                    navController.navigate(R.id.action_nav_home_to_nav_laliga, bundle)
                }
                R.id.nav_bundesliga -> {
                    val bundle = bundleOf(Pair("idLiga", 2002))
                    navController.navigate(R.id.action_nav_home_to_nav_laliga, bundle)
                }
                R.id.nav_eredivisie -> {
                    val bundle = bundleOf(Pair("idLiga", 2003))
                    navController.navigate(R.id.action_nav_home_to_nav_laliga, bundle)
                }
                R.id.nav_ligue1 -> {
                    val bundle = bundleOf(Pair("idLiga", 2015))
                    navController.navigate(R.id.action_nav_home_to_nav_laliga, bundle)
                }
                R.id.nav_primeiraliga -> {
                    val bundle = bundleOf(Pair("idLiga", 2017))
                    navController.navigate(R.id.action_nav_home_to_nav_laliga, bundle)
                }
                R.id.nav_ligaa -> {
                    val bundle = bundleOf(Pair("idLiga", 2019))
                    navController.navigate(R.id.action_nav_home_to_nav_laliga, bundle)
                }
                R.id.nav_premierleague -> {
                    val bundle = bundleOf(Pair("idLiga", 2021))
                    navController.navigate(R.id.action_nav_home_to_nav_laliga, bundle)
                }
                else -> true
            }
            if (it.itemId == R.id.nav_laliga){
                navView.menu.findItem(it.itemId).setChecked(true)
            }else{
                navView.menu.findItem(R.id.nav_laliga).setChecked(false)
                navView.menu.findItem(it.itemId).setChecked(true)
            }
            drawerLayout.closeDrawer(GravityCompat.START)
            NavigationUI.onNavDestinationSelected(it, navController)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.action_calendar -> {
                Toast.makeText(this, "Calendar", Toast.LENGTH_LONG).show()
            }

            else -> true
        }
        return super.onOptionsItemSelected(item)
    }
}