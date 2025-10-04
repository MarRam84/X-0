package com.example.x_0

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val player1 = findViewById<EditText>(R.id.etPlayer1)
        val player2 = findViewById<EditText>(R.id.etPlayer2)
        val btnStart = findViewById<Button>(R.id.btnStartGame)

        // Deshabilitar el botón al inicio
        btnStart.isEnabled = false

        // Activar el botón solo si ambos campos tienen texto
        val watcher = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val name1 = player1.text.toString().trim()
                val name2 = player2.text.toString().trim()
                btnStart.isEnabled = name1.isNotEmpty() && name2.isNotEmpty()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        }

        player1.addTextChangedListener(watcher)
        player2.addTextChangedListener(watcher)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun nextScreen(v: View) {
        val player1 = findViewById<EditText>(R.id.etPlayer1)
        val player2 = findViewById<EditText>(R.id.etPlayer2)

        val name1 = player1.text.toString().trim()
        val name2 = player2.text.toString().trim()

        if (name1.isEmpty() || name2.isEmpty()) {
            Toast.makeText(this, "Por favor ingresa ambos nombres", Toast.LENGTH_SHORT).show()
            return
        }

        val intent = Intent(this, GameActivity::class.java).apply {
            putExtra("player1", name1)
            putExtra("player2", name2)
        }
        startActivity(intent)
    }
}