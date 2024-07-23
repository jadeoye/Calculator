package com.example.calculator

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    val defaultOperation: String = "-- Select One --"
    var selectedOperation: String = defaultOperation

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val operations = arrayOf(defaultOperation, "Sum", "Subtract", "Divide", "Multiplication")

        val user: TextView = findViewById(R.id.nameLabel)
        val data = intent?.getStringExtra("user")
        user.text = "Welcome " + data + "! 👋"
        val firstEntry: EditText = findViewById(R.id.firstNumberEntry)
        val secondEntry: EditText = findViewById(R.id.secondNumberEntry)
        val button: Button = findViewById(R.id.calculateButton)
        val spinner: Spinner = findViewById(R.id.operationSpinner)
        val result: TextView = findViewById(R.id.result)

        spinner.adapter =
            ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, operations)

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                selectedOperation = operations.get(p2)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        button.setOnClickListener { view ->

            val firstEntryText = firstEntry.text.toString();
            val secondEntryText = secondEntry.text.toString();
            if(firstEntryText.length <= 0 || secondEntryText.length <= 0) {
                Toast.makeText(this, "Please enter a valid number", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            val a: Int = firstEntryText.toInt()
            val b: Int = secondEntryText.toInt()

            val mathResult = when (selectedOperation) {
                "Sum" -> sum(a, b).toString()
                "Divide" -> divide(a, b).toString()
                "Subtract" -> subtract(a, b).toString()
                "Multiplication" -> multiply(a, b).toString()
                else -> "NIL"
            }

            if (mathResult == "NIL")
                Toast.makeText(this, "Please select an option", Toast.LENGTH_LONG).show()
            else
                result.text = mathResult
        }
    }

    private fun sum(a: Int, b: Int): Int {
        return a + b
    }

    private fun multiply(a: Int, b: Int): Int {
        return a * b
    }

    private fun divide(a: Int, b: Int): Int {
        return a / b
    }

    private fun subtract(a: Int, b: Int): Int {
        return a - b
    }

//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        var inflater = menuInflater
//        inflater.inflate(R.menu.app_menu, menu)
//        return super.onCreateOptionsMenu(menu)
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        when(item.itemId){
//            R.id.item1 -> Toast.makeText(this, "TODO go to next page", Toast.LENGTH_SHORT).show()
//        }
//        return true;
//    }
}