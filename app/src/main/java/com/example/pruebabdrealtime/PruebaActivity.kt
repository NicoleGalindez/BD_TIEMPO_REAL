package com.example.pruebabdrealtime

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class PruebaActivity : AppCompatActivity() {

    private lateinit var editTextName: EditText
    private lateinit var listView: ListView
    private lateinit var adapter: ArrayAdapter<String>
    private val dataList = mutableListOf<String>()
    private lateinit var selectedTextView: TextView
    private var selectedItemIndex: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prueba)

        // Inicializar las vistas y el adaptador
        editTextName = findViewById(R.id.editTextName)
        listView = findViewById(R.id.listView)
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, dataList)
        listView.adapter = adapter

        // TextView adicional para mostrar el elemento seleccionado
        selectedTextView = findViewById(R.id.selectedTextView)





        // Manejar el evento de selección de un elemento en la lista
        listView.setOnItemClickListener { parent, view, position, id ->
            val selectedItem = adapter.getItem(position)
            selectedTextView.text = "Elemento seleccionado: $selectedItem"
            editTextName.setText(selectedItem)
            selectedItemIndex = position
        }




        // Manejar el evento de clic en el botón "Crear"
        val buttonCreate: Button = findViewById(R.id.buttonCreate)
        buttonCreate.setOnClickListener {
            val newName = editTextName.text.toString().trim()
            if (newName.isNotEmpty()) {
                dataList.add(newName)
                adapter.notifyDataSetChanged()
                editTextName.text.clear()
            }
        }

        // Manejar el evento de clic en el botón "Eliminar"
        val buttonDelete: Button = findViewById(R.id.buttonDelete)
        buttonDelete.setOnClickListener {
            if (selectedItemIndex != -1) {
                dataList.removeAt(selectedItemIndex)
                adapter.notifyDataSetChanged()
                selectedTextView.text = "" // Limpiar el TextView de la selección
                selectedItemIndex = -1 // Resetear el índice seleccionado
            }
        }

// Manejar el evento de clic en el botón "Actualizar"
        val buttonUpdate: Button = findViewById(R.id.buttonUpdate)
        buttonUpdate.setOnClickListener {
            val newName = editTextName.text.toString().trim()
            if (newName.isNotEmpty() && selectedItemIndex != -1) {
                dataList[selectedItemIndex] = newName
                adapter.notifyDataSetChanged()
                editTextName.text.clear()
                selectedTextView.text = "" // Limpiar el TextView de la selección
                selectedItemIndex = -1 // Resetear el índice seleccionado
            }
        }


    }
}