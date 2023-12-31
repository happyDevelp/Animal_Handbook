package com.example.animalhandbook.DB.types

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "type_table")
data class TypesEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "type_name")
    val name: String,

    @ColumnInfo(name = "type_description")
    val description: String,

    @ColumnInfo(name = "type_pic_name")
    val picName: String
)