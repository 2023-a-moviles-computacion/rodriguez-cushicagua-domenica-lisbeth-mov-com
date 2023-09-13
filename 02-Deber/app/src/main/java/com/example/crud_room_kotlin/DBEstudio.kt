package com.example.crud_room_kotlin

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Estudio::class],
    version = 3
)
abstract class DBEstudio: RoomDatabase() {
    abstract fun daoEstudio(): DaoEstudio
}