package com.example.animalhandbook.DB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [TypesEntity::class], version = 1, exportSchema = false)
abstract class DataBase : RoomDatabase() {
    abstract val typesDAO: TypesDAO

    companion object {
        @Volatile
        private var INSTANCE: DataBase? = null

        fun getInstance(context: Context):DataBase {
            synchronized(this){
                var instance = INSTANCE

                if (instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        DataBase::class.java,
                        "sleep_history_database"
                    ).fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }

                return instance
            }
        }
    }
}