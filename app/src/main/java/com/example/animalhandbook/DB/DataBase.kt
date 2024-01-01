package com.example.animalhandbook.DB

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.animalhandbook.DB.animals.AnimalDAO
import com.example.animalhandbook.DB.animals.AnimalEntity
import com.example.animalhandbook.DB.types.TypesDAO
import com.example.animalhandbook.DB.types.TypesEntity


@Database(entities = [TypesEntity::class, AnimalEntity::class], /*autoMigrations = [AutoMigration(from = 3, to = 4)],*/ version = 4, exportSchema = true)
abstract class DataBase : RoomDatabase() {
    abstract val typesDAO: TypesDAO
    abstract val animalDAO: AnimalDAO


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
                        "types_database"
                    ).fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }

                return instance
            }
        }

    }
}