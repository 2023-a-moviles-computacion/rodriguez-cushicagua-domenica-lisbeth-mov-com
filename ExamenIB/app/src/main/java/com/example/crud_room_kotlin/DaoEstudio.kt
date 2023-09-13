package com.example.crud_room_kotlin

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DaoEstudio {

    @Query("SELECT * FROM estudios")
    suspend fun obtenerEstudio(): MutableList<Estudio>

    @Insert
    suspend fun agregarEstudio(estudio: Estudio)
    //agrego los atributos para actualizar
    @Query("UPDATE estudios set nombreE=:nombreE ,pais=:pais, email=:email WHERE idEstudio=:idEstudio")
    suspend fun actualizarEstudio(idEstudio: Int, nombreE: String, pais: String, email: String)

    @Query("DELETE FROM estudios WHERE idEstudio=:idEstudio")
    suspend fun borrarEstudio(idEstudio: Int)

    @Query("SELECT idEstudio FROM estudios LIMIT 1")
    suspend fun obtenerIdEstudio(): Int?


}