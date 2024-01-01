package com.example.animalhandbook.DB.animals

import androidx.lifecycle.LifecycleRegistry
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
    fun getAnimalByID(id: Int): AnimalEntity

    @Query("SELECT * from animal_table WHERE animal_name = :name")
    fun getAnimalByName(name: String): AnimalEntity


    @Query("SELECT * from animal_table where animal_type = :animalType")
    fun getAllAnimalByType(animalType: String): LiveData<List<AnimalEntity>>


}