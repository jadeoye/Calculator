package com.example.calculator

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class StartupActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_startup)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val nameEntry: EditText = findViewById(R.id.nameEntry)
        val formButton: Button = findViewById(R.id.formButton)
        val calculatorButton: Button = findViewById(R.id.calculatorButton)

        formButton.setOnClickListener {
            val user = nameEntry.text.toString()
            if (user.length <= 0) {
                Toast.makeText(this, "Please enter your name", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            val intent = Intent(this, RegisterActivity::class.java).apply {
                putExtra("user", nameEntry.text.toString())
            }
            startActivity(intent)
        }

        calculatorButton.setOnClickListener {
            val user = nameEntry.text.toString()
            if (user.length <= 0) {
                Toast.makeText(this, "Please enter your name", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            val intent = Intent(this, MainActivity::class.java).apply {
                putExtra("user", nameEntry.text.toString())
            }
            startActivity(intent)
        }
    }
}