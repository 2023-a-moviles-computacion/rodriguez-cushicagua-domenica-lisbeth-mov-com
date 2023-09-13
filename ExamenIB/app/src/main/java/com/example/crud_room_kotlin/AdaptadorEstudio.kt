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
    val listaEstudios: MutableList<Estudio>,
    val listener: AdaptadorListener
): RecyclerView.Adapter<AdaptadorEstudio.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.item_rv_estudio, parent, false)
        return ViewHolder(vista)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val estudio = listaEstudios[position]
        holder.tvIdEstudio.text = estudio.idEstudio.toString()
        holder.tvNombreEstudio.text = estudio.nombreE
        holder.tvPais.text = estudio.pais
        holder.tvEmail.text = estudio.email

        holder.cvEstudio.setOnClickListener {
            listener.onEditItemClick(estudio)
        }

        holder.btnBorrar.setOnClickListener {
            listener.onDeleteItemClick(estudio)
        }

        holder.btnCrear.setOnClickListener {
            val position = holder.adapterPosition // Obtener la posición del elemento en el adaptador
            val estudio = listaEstudios[position] // Obtener el objeto Estudio correspondiente a esa posición
            val idEstudio = estudio.idEstudio

            val intent = Intent(holder.itemView.context, personajesMain::class.java)

            intent.putExtra("idEstudio", idEstudio) // Pasar el idEstudio como extra en el intent
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return listaEstudios.size
    }

    inner class ViewHolder(ItemView: View): RecyclerView.ViewHolder(ItemView) {
        val cvEstudio = itemView.findViewById<CardView>(R.id.cvEstudio)
        val tvIdEstudio = itemView.findViewById<TextView>(R.id.tvIdEstudio)
        val tvNombreEstudio = itemView.findViewById<TextView>(R.id.tvNombreEstudio)
        val tvPais = itemView.findViewById<TextView>(R.id.tvPais)
        val tvEmail = itemView.findViewById<TextView>(R.id.tvEmail)
        val btnBorrar = itemView.findViewById<Button>(R.id.btnBorrar)
        val btnCrear = itemView.findViewById<Button>(R.id.btnCrear)
    }
}