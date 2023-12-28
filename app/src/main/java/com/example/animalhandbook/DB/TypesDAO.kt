package com.example.animalhandbook.DB

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TypesDAO {

    @Insert
    fun insertType(typesEntity: TypesEntity)

    @Query("SELECT * from types_animal")
    fun getAllTypes(): LiveData<List<TypesEntity>>

    @Query("SELECT * from types_animal WHERE id = :id")
    fun getByID(id: Int): TypesEntity

}