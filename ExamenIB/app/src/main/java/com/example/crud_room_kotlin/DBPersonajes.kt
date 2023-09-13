package com.example.crud_room_kotlin

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    entities = [Personaje::class],
    version = 3
)
abstract class DBPersonajes: RoomDatabase() {
    abstract fun daoPersonaje(): DaoPersonaje
}