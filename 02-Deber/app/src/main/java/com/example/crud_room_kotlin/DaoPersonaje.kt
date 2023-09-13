package com.example.crud_room_kotlin

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DaoPersonaje {

    @Query("SELECT * FROM personajes WHERE idEstudio = :idEstudio")
    suspend fun obtenerPersonajes(idEstudio: Int): MutableList<Personaje>
    @Insert
    suspend fun agregarPersonaje(personaje: Personaje)
    //agrego los atributos para actualizar
    @Query("UPDATE personajes set paisPersonaje=:paisPersonaje, edadPersonaje=:edadPersonaje, idEstudio=:idEstudio WHERE nombrePersonaje=:nombrePersonaje")
    suspend fun actualizarPersonaje(nombrePersonaje: String, paisPersonaje: String, edadPersonaje: Int, idEstudio : Int)

    @Query("DELETE FROM personajes WHERE nombrePersonaje=:nombrePersonaje")
    suspend fun borrarPersonaje(nombrePersonaje: String)
}