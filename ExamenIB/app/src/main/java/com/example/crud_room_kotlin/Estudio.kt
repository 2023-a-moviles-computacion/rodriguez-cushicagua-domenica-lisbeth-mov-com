package com.example.crud_room_kotlin

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "estudios")
data class Estudio(
    @PrimaryKey var idEstudio: Int =0 ,
    @ColumnInfo(name = "nombreE") var nombreE: String,
    @ColumnInfo(name = "pais") var pais: String,
    @ColumnInfo(name = "email") var email: String,
)