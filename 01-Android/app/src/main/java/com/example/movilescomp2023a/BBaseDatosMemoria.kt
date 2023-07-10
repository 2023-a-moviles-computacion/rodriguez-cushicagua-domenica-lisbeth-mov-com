package com.example.movilescomp2023a

class BBaseDatosMemoria {
    companion object{
        val arregloBEntredador = arrayListOf<BEntrenador>()
        init {
            arregloBEntredador
                .add(
                    BEntrenador(1,"Adrian","a@a.com")
                )
            arregloBEntredador
                .add(
                    BEntrenador(2,"Vicente","b@b.com")
                )
            arregloBEntredador
                .add(
                   BEntrenador(3,"Carolina","c@c.com")
                )
        }
    }
}