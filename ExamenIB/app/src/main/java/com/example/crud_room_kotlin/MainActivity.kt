package com.example.crud_room_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.crud_room_kotlin.databinding.ActivityMainBinding
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.coroutines.Dispatchers

class MainActivity : AppCompatActivity(), AdaptadorListener {

    lateinit var binding: ActivityMainBinding
    var listaEstudio: MutableList<Estudio> = mutableListOf()

    lateinit var adatador: AdaptadorEstudio

    lateinit var room: DBEstudio

    lateinit var estudio: Estudio

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvEstudio.layoutManager = LinearLayoutManager(this)

        room = Room.databaseBuilder(this, DBEstudio::class.java, "dbEstudio").build()

        obtenerEstudio(room)

        binding.btnAddUpdate.setOnClickListener {
            if (binding.etNombreEstudio.text.isNullOrEmpty() || binding.etPais.text.isNullOrEmpty() || binding.etEmail.text.isNullOrEmpty()) {
                Toast.makeText(this, "DEBES LLENAR TODOS LOS CAMPOS", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val idEstudio = binding.etIdEstudio.text.toString().trim().toInt()
            val nombreEstudio = binding.etNombreEstudio.text.toString().trim()
            val pais = binding.etPais.text.toString().trim()
            val email = binding.etEmail.text.toString().trim()

            if (binding.btnAddUpdate.text == "agregar") {
                estudio = Estudio(idEstudio, nombreEstudio, pais, email)
                agregarEstudio(room, estudio)
            } else if (binding.btnAddUpdate.text == "actualizar") {
                estudio = Estudio(idEstudio, nombreEstudio, pais, email)
                actualizarEstudio(room, estudio)
            }
        }

    }

    fun obtenerEstudio(room: DBEstudio) {
        lifecycleScope.launch {
            listaEstudio = room.daoEstudio().obtenerEstudio()
            adatador = AdaptadorEstudio(listaEstudio, this@MainActivity)
            binding.rvEstudio.adapter = adatador
        }
    }

    fun agregarEstudio(room: DBEstudio, estudio: Estudio) {
        lifecycleScope.launch {
            room.daoEstudio().agregarEstudio(estudio)
            obtenerEstudio(room)
            limpiarCampos()
        }
    }

    fun actualizarEstudio(room: DBEstudio, estudio: Estudio) {
        lifecycleScope.launch {
            room.daoEstudio().actualizarEstudio(estudio.idEstudio, estudio.nombreE, estudio.pais, estudio.email)
            obtenerEstudio(room)
            limpiarCampos()
        }
    }


    fun limpiarCampos() {
        estudio.idEstudio =0
        estudio.nombreE = ""
        estudio.pais = ""
        estudio.email = ""
        binding.etIdEstudio.setText("")
        binding.etNombreEstudio.setText("")
        binding.etPais.setText("")
        binding.etEmail.setText("")

        if (binding.btnAddUpdate.text.equals("actualizar")) {
            binding.btnAddUpdate.setText("agregar")
            binding.etIdEstudio.isEnabled = true
        }

    }




    override fun onEditItemClick(usuario: Estudio) {
        binding.btnAddUpdate.setText("actualizar")
        binding.etIdEstudio.isEnabled = false
        this.estudio = usuario
        binding.etIdEstudio.setText(this.estudio.idEstudio.toString())
        binding.etNombreEstudio.setText(this.estudio.nombreE)
        binding.etPais.setText(this.estudio.pais)
        binding.etEmail.setText(this.estudio.email)
    }

    override fun onDeleteItemClick(estudio: Estudio) {
        lifecycleScope.launch {
            room.daoEstudio().borrarEstudio(estudio.idEstudio)
            adatador.notifyDataSetChanged()
            obtenerEstudio(room)
        }
    }


}