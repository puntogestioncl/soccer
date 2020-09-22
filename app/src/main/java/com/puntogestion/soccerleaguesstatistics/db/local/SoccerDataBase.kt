package com.puntogestion.soccerleaguesstatistics.db.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [TeamEntity::class, PlayersEntity::class], version = 1)
abstract class SoccerDataBase: RoomDatabase() {

    abstract fun teamDao(): TeamDao
    abstract fun playersTeamDao(): PlayersDao

    companion object{
        @Volatile
        private var INSTANCE: SoccerDataBase? = null
        fun getDataBaseInstance(context: Context): SoccerDataBase{
            val tempInstance = INSTANCE
            if (tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SoccerDataBase::class.java,
                    "soccer_database")
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}