package com.example.crud_room_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.crud_room_kotlin.databinding.ActivityPersonajesBinding
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class personajesMain: AppCompatActivity(), AdaptadorListener2{

    private val dataList = mutableListOf<Pair<String, Map<String, Any>>>()
    lateinit var binding: ActivityPersonajesBinding
    lateinit var adatador: AdaptadorPersonajes

    val db : FirebaseFirestore = FirebaseFirestore.getInstance()
    val personajeCollection = db.collection("personajes")

    var idEstudioVine: String? = null // Inicializarlo como null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        idEstudioVine = intent.getStringExtra("idEstudio")

        binding = ActivityPersonajesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.rvPersonaje.layoutManager = LinearLayoutManager(this)

        // Inicializa el adaptador aquí
        adatador = AdaptadorPersonajes(emptyList(), this)
        binding.rvPersonaje.adapter = adatador
        cargarDatosYActualizarRecyclerView()

        binding.btnAddUpdateP.setOnClickListener {
            if (binding.etNombrePersonaje.text.isNotBlank() && binding.etPaisPersonaje.text.isNotBlank() && binding.etEdadPersonaje.text.isNotBlank()) {
                val dato = hashMapOf(
                    "edadPersonaje" to binding.etEdadPersonaje.text.toString(),
                    "idEstudio" to idEstudioVine.toString(),
                    "idPersonaje" to binding.etIdPersonaje.text.toString(),
                    "nombrePersonaje" to binding.etNombrePersonaje.text.toString(),
                    "paisPersonaje" to binding.etPaisPersonaje.text.toString()
                )
                db.collection("personajes")
                    .document(binding.etIdPersonaje.text.toString())
                    .set(dato)
                    .addOnSuccessListener { resultado ->
                        binding.btnAddUpdateP.text ="Agregar"
                        limpiarCampos()
                        adatador.notifyDataSetChanged()
                        cargarDatosYActualizarRecyclerView()
                    }
                    .addOnFailureListener{ exception ->
                        binding.btnAddUpdateP.text = "No se logro XD"
                    }
            }
        }
    }

    fun limpiarCampos() {
        binding.etIdPersonaje.setText("")
        binding.etNombrePersonaje.setText("")
        binding.etPaisPersonaje.setText("")
        binding.etEdadPersonaje.setText("")
        if (binding.btnAddUpdateP.text.equals("Actualizar")) {
            binding.btnAddUpdateP.setText("Agregar")
            binding.etIdPersonaje.isEnabled = true
            adatador.notifyDataSetChanged()
        }
    }

    private fun cargarDatosYActualizarRecyclerView() {
        idEstudioVine = intent.getStringExtra("idEstudio")
        personajeCollection
            .whereEqualTo("idEstudio", idEstudioVine)
            .get()
            .addOnSuccessListener { documentos ->
                dataList.clear() // Limpia los datos anteriores
                for (documento in documentos) {
                    dataList.add(Pair(documento.id, documento.data))
                }
                val adapter = AdaptadorPersonajes(dataList, this)
                binding.rvPersonaje.adapter = adapter
                adatador.notifyDataSetChanged() // Notifica al adaptador que los datos han cambiado
            }
            .addOnFailureListener { exception ->
                Toast.makeText(this, "No se ha podido conectar: ${exception.message}", Toast.LENGTH_SHORT).show()
            }
    }

    override fun onEditItemClick(idPersonaje: String, data: Map<String, Any>) {
        binding.btnAddUpdateP.setText("Actualizar")
        binding.etIdPersonaje.isEnabled = false
        binding.etIdPersonaje.setText(idPersonaje)
        binding.etNombrePersonaje.setText(data["nombrePersonaje"] as? String ?: "")
        binding.etPaisPersonaje.setText(data["paisPersonaje"] as? String ?: "")
        binding.etEdadPersonaje.setText(data["edadPersonaje"] as? String ?: "")
        adatador.notifyDataSetChanged()
        cargarDatosYActualizarRecyclerView()

    }

    override fun onDeleteItemClick(idPersonaje: String, data: Map<String, Any>) {
        db.collection("personajes")
            .document(idPersonaje)
            .delete()
            .addOnSuccessListener {
                Toast.makeText(this@personajesMain, "Elemento eliminado", Toast.LENGTH_SHORT).show()
                adatador.notifyDataSetChanged()
                cargarDatosYActualizarRecyclerView()
            }
            .addOnFailureListener { exception ->
                Toast.makeText(this@personajesMain, "Error al eliminar: ${exception.message}", Toast.LENGTH_SHORT).show()
            }
    }
}