package com.example.animalhandbook.DB.animals

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface AnimalDAO {

    @Insert
    fun insertAnimal(animalEntity: AnimalEntity)

    @Query("SELECT * from animal_table")
    fun getAllAnimals(): LiveData<List<AnimalEntity>>

    @Query("SELECT * from animal_table WHERE id = :id")
    fun getByID(id: Int): AnimalEntity

}