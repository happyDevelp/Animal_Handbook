package com.example.animalhandbook.DB.animals

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "animal_table")
data class AnimalEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "animal_name")
    val name: String,

    @ColumnInfo(name = "animal_description")
    val description: String,

    @ColumnInfo(name = "animal_pic_name")
    val picName: String
)