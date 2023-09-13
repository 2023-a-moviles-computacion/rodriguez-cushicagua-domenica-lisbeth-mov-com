package com.example.crud_room_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.crud_room_kotlin.databinding.ActivityPersonajesBinding
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.coroutines.Dispatchers

class personajesMain: AppCompatActivity(), AdaptadorListener2{

    lateinit var binding: ActivityPersonajesBinding
    var listaPersonajes: MutableList<Personaje> = mutableListOf()
    lateinit var adatador: AdaptadorPersonajes
    lateinit var room: DBEstudio
    lateinit var room2: DBPersonajes
    lateinit var personaje: Personaje

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPersonajesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvPersonaje.layoutManager = LinearLayoutManager(this)
        room = Room.databaseBuilder(this, DBEstudio::class.java, "dbEstudio").build()
        room2 = Room.databaseBuilder(this, DBPersonajes::class.java, "dbPersonaje").build()

        binding.btnAddUpdateP.setOnClickListener {
            if (binding.etNombrePersonaje.text.isNullOrEmpty() || binding.etPaisPersonaje.text.isNullOrEmpty() || binding.etEdadPersonaje.text.isNullOrEmpty()) {
                Toast.makeText(this, "DEBES LLENAR TODOS LOS CAMPOS", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

                if (binding.btnAddUpdateP.text.equals("agregar")) {
                val personaje = Personaje(
                    idEstudio = binding.etIdEstudio.text.toString().trim().toInt(),
                    nombrePersonaje = binding.etNombrePersonaje.text.toString().trim(),
                    paisPersonaje = binding.etPaisPersonaje.text.toString().trim(),
                    edadPersonaje = binding.etEdadPersonaje.text.toString().trim().toInt()
                )
                agregarPersonaje(room2, personaje)
            } else if (binding.btnAddUpdateP.text.equals("actualizar")) {
                personaje.paisPersonaje = binding.etPaisPersonaje.text.toString().trim()
                personaje.edadPersonaje = binding.etEdadPersonaje.text.toString().trim().toInt()
                actualizarPersonaje(room2, personaje)
            }
        }

    }

     fun obtenerPersonajes(room: DBPersonajes) {
        lifecycleScope.launch(Dispatchers.IO) {
            val idEstudioActual = obtenerIdEstudioActual()
            if (idEstudioActual != null) {
                listaPersonajes = room.daoPersonaje().obtenerPersonajes(idEstudioActual)
                withContext(Dispatchers.Main) {
                    adatador = AdaptadorPersonajes(listaPersonajes, this@personajesMain)
                    binding.rvPersonaje.adapter = adatador
                }
            } else {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@personajesMain, "No se encontró el idEstudio actual", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    suspend fun obtenerIdEstudioActual(): Int? {
        return withContext(Dispatchers.IO) {
            room.daoEstudio().obtenerIdEstudio()
        }
    }

    fun agregarPersonaje(room: DBPersonajes, personaje: Personaje) {
        lifecycleScope.launch {
            val idEstudioActual = obtenerIdEstudioActual()
            if (idEstudioActual != null) {
                personaje.idEstudio = idEstudioActual
                room.daoPersonaje().agregarPersonaje(personaje)
                obtenerPersonajes(room)
                limpiarCampos()
            } else {
                Toast.makeText(this@personajesMain, "No se encontró el idEstudio actual", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun actualizarPersonaje(room: DBPersonajes, personaje: Personaje) {
        lifecycleScope.launch {
            room.daoPersonaje().actualizarPersonaje(personaje.nombrePersonaje, personaje.paisPersonaje, personaje.edadPersonaje,personaje.idEstudio)
            obtenerPersonajes(room)
            limpiarCampos()
        }
    }

    fun limpiarCampos() {
        personaje.nombrePersonaje = ""
        personaje.paisPersonaje = ""
        personaje.edadPersonaje= 0
        binding.etNombrePersonaje.setText("")
        binding.etPaisPersonaje.setText("")
        binding.etEdadPersonaje.setText("")

        if (binding.btnAddUpdateP.text.equals("actualizar")) {
            binding.btnAddUpdateP.setText("agregar")
            binding.etNombrePersonaje.isEnabled = true
        }

    }


    override fun onEditItemClick(personaje: Personaje) {

        binding.btnAddUpdateP.text = "actualizar"
        binding.etNombrePersonaje.isEnabled = false
        this.personaje = personaje
        binding.etNombrePersonaje.setText(this.personaje.nombrePersonaje.toString())
        binding.etPaisPersonaje.setText(this.personaje.paisPersonaje.toString())
        binding.etEdadPersonaje.setText(this.personaje.edadPersonaje.toString())
    }


    override fun onDeleteItemClick(personaje: Personaje) {
        lifecycleScope.launch {
            room2.daoPersonaje().borrarPersonaje(personaje.nombrePersonaje)
            adatador.notifyDataSetChanged()
            obtenerPersonajes(room2)
        }
    }
}