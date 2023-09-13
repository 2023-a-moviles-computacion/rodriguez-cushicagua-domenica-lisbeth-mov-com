package com.example.crud_room_kotlin

interface AdaptadorListener {
    fun onEditItemClick(idEstudio: String, data: Map<String, Any>)
    fun onDeleteItemClick(idEstudio: String, data: Map<String, Any>)
}

