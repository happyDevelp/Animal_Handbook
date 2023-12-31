package com.example.animalhandbook.DB.types

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TypesDAO {

    @Insert
    fun insertType(typesEntity: TypesEntity)

    @Query("SELECT * from type_table")
    fun getAllTypes(): LiveData<List<TypesEntity>>

    @Query("SELECT * from type_table WHERE id = :id")
    fun getByID(id: Int): TypesEntity

}