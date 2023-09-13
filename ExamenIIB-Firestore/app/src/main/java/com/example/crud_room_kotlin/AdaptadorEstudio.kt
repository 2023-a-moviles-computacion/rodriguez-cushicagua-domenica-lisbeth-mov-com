package com.example.crud_room_kotlin

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView


class AdaptadorEstudio(
    val listaEstudios: List<Pair<String, Map<String, Any>>>,
    val listener: AdaptadorListener
) : RecyclerView.Adapter<AdaptadorEstudio.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.item_rv_estudio, parent, false)
        return ViewHolder(vista)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val (id, data) = listaEstudios[position]
        holder.tvIdEstudio.text = id
        holder.tvNombreEstudio.text = data["nombreE"] as? String ?: ""
        holder.tvPais.text = data["pais"] as? String ?: ""
        holder.tvEmail.text = data["email"] as? String ?: ""
        holder.cvEstudio.setOnClickListener {
            listener.onEditItemClick(id, data)
        }

        holder.btnBorrar.setOnClickListener {
            val (id, data) = listaEstudios[position]
            listener.onDeleteItemClick(id, data)
        }

        holder.btnCrear.setOnClickListener {
            val (id, _) = listaEstudios[position] // No necesitamos los datos aquí, solo el ID
            val intent = Intent(holder.itemView.context, personajesMain::class.java)
            intent.putExtra("idEstudio", id)
            holder.itemView.context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return listaEstudios.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cvEstudio = itemView.findViewById<CardView>(R.id.cvEstudio)
        val tvIdEstudio = itemView.findViewById<TextView>(R.id.tvIdEstudio)
        val tvNombreEstudio = itemView.findViewById<TextView>(R.id.tvNombreEstudio)
        val tvPais = itemView.findViewById<TextView>(R.id.tvPais)
        val tvEmail = itemView.findViewById<TextView>(R.id.tvEmail)
        val btnBorrar = itemView.findViewById<Button>(R.id.btnBorrar)
        val btnCrear = itemView.findViewById<Button>(R.id.btnCrear)
    }
}
