package com.example.crud_room_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.crud_room_kotlin.databinding.ActivityMainBinding
import com.google.firebase.firestore.FirebaseFirestore


class MainActivity : AppCompatActivity(), AdaptadorListener {

    private val dataList = mutableListOf<Pair<String, Map<String, Any>>>()
    lateinit var binding: ActivityMainBinding
    lateinit var adatador: AdaptadorEstudio

    val db : FirebaseFirestore = FirebaseFirestore.getInstance()
    val estudiosCollection = db.collection("estudios")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.rvEstudio.layoutManager = LinearLayoutManager(this)

        // Inicializa el adaptador aquí
        adatador = AdaptadorEstudio(emptyList(), this)
        binding.rvEstudio.adapter = adatador
        cargarDatosYActualizarRecyclerView()

        binding.btnAddUpdate.setOnClickListener {
            if (binding.etNombreEstudio.text.isNotBlank() && binding.etPais.text.isNotBlank() && binding.etEmail.text.isNotBlank()) {
                val dato = hashMapOf(
                    "email" to binding.etEmail.text.toString(),
                    "idEstudio" to binding.etIdEstudio.text.toString(),
                    "nombreE" to binding.etNombreEstudio.text.toString(),
                    "pais" to binding.etPais.text.toString()
                )

                db.collection("estudios")
                    .document(binding.etIdEstudio.text.toString())
                    .set(dato)
                    .addOnSuccessListener { resultado ->
                        binding.btnAddUpdate.text ="Agregar"
                        limpiarCampos()
                        adatador.notifyDataSetChanged()
                        cargarDatosYActualizarRecyclerView()
                    }
                    .addOnFailureListener{ exception ->
                        binding.btnAddUpdate.text = "No se logro XD"
                    }
            }
        }
    }

    fun limpiarCampos() {
        binding.etIdEstudio.setText("")
        binding.etNombreEstudio.setText("")
        binding.etPais.setText("")
        binding.etEmail.setText("")
        if (binding.btnAddUpdate.text.equals("Actualizar")) {
            binding.btnAddUpdate.setText("Agregar")
            binding.etIdEstudio.isEnabled = true
            adatador.notifyDataSetChanged()
            cargarDatosYActualizarRecyclerView()
        }
    }

    private fun cargarDatosYActualizarRecyclerView() {
        estudiosCollection
            .get()
            .addOnSuccessListener { documentos ->
                dataList.clear()
                for (documento in documentos) {
                    dataList.add(Pair(documento.id, documento.data))
                }
                val adapter = AdaptadorEstudio(dataList, this)
                binding.rvEstudio.adapter = adapter
                adatador.notifyDataSetChanged()
            }
            .addOnFailureListener { exception ->
                Toast.makeText(this, "No se ha podido conectar: ${exception.message}", Toast.LENGTH_SHORT).show()
            }
    }

    override fun onEditItemClick(idEstudio: String, data: Map<String, Any>) {
        binding.btnAddUpdate.setText("Actualizar")
        binding.etIdEstudio.isEnabled = false
        binding.etIdEstudio.setText(idEstudio)
        binding.etNombreEstudio.setText(data["nombreE"] as? String ?: "")
        binding.etPais.setText(data["pais"] as? String ?: "")
        binding.etEmail.setText(data["email"] as? String ?: "")
        adatador.notifyDataSetChanged()
        cargarDatosYActualizarRecyclerView()
    }

    override fun onDeleteItemClick(idEstudio: String, data: Map<String, Any>) {
    db.collection("estudios")
        .document(idEstudio)
        .delete()
            .addOnSuccessListener {
                Toast.makeText(this@MainActivity, "Elemento eliminado", Toast.LENGTH_SHORT).show()
                adatador.notifyDataSetChanged()
                cargarDatosYActualizarRecyclerView()
            }
            .addOnFailureListener { exception ->
                Toast.makeText(this@MainActivity, "Error al eliminar: ${exception.message}", Toast.LENGTH_SHORT).show()
            }

    }

}