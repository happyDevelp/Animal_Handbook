package com.example.animalhandbook.DB

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "types_animals")
data class TypesEntity (

    @PrimaryKey
    val id: Int = 0,

    @ColumnInfo(name = "animal_name")
    val name: String,

    @ColumnInfo(name = "animal_description")
    val description: String,

    @ColumnInfo(name = "pic_id")
    val picID: Int
)