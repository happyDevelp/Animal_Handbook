package com.example.animalhandbook.DB

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TypesDAO {

    @Query("SELECT * from types_animals")
    fun getAllTypes(): List<TypesEntity>

    @Query("SELECT * from types_animals WHERE pic_id = :id")
    fun getByID(id: Int): TypesEntity


}