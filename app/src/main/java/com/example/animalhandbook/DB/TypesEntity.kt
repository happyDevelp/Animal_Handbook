package com.example.animalhandbook.DB

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "types_animal")
data class TypesEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "types_name")
    val name: String,

    @ColumnInfo(name = "types_description")
    val description: String,

    @ColumnInfo(name = "types_pic_name")
    val picName: String
)