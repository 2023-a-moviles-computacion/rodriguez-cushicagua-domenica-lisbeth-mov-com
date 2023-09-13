package com.example.crud_room_kotlin

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "personajes")
data class Personaje(
    @PrimaryKey var nombrePersonaje: String,
    @ColumnInfo(name = "paisPersonaje") var paisPersonaje: String,
    @ColumnInfo(name = "edadPersonaje") var edadPersonaje: Int,
    @ColumnInfo(name = "idEstudio") var idEstudio: Int = 0,
)

