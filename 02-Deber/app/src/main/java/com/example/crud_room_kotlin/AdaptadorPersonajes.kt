package com.example.crud_room_kotlin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class AdaptadorPersonajes(
    val listaPersonajes: MutableList<Personaje>,
    val listener: AdaptadorListener2
): RecyclerView.Adapter<AdaptadorPersonajes.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.item_rv_personaje, parent, false)
        return ViewHolder(vista)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val personaje = listaPersonajes[position]
        holder.tvIdEstudio.text = personaje.idEstudio.toString()
        holder.tvNombrePersonaje.text = personaje.nombrePersonaje
        holder.tvPaisPersonaje.text = personaje.paisPersonaje
        holder.tvEdadPersonaje.text = personaje.edadPersonaje.toString()

        holder.cvPersonaje.setOnClickListener {
            listener.onEditItemClick(personaje)
        }

        holder.btnBorrarP.setOnClickListener {
            listener.onDeleteItemClick(personaje)
        }

    }

    override fun getItemCount(): Int {
        return listaPersonajes.size
    }

    inner class ViewHolder(ItemView: View): RecyclerView.ViewHolder(ItemView) {
        val cvPersonaje = itemView.findViewById<CardView>(R.id.cvPersonaje)
        val tvIdEstudio = itemView.findViewById<TextView>(R.id.tvIdEstudio)
        val tvNombrePersonaje = itemView.findViewById<TextView>(R.id.tvNombreP)
        val tvPaisPersonaje = itemView.findViewById<TextView>(R.id.tvPaisP)
        val tvEdadPersonaje = itemView.findViewById<TextView>(R.id.tvEdadP)
        val btnBorrarP = itemView.findViewById<Button>(R.id.btnBorrarP)

    }

}