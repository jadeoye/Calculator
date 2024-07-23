package com.example.calculator

import android.annotation.SuppressLint
import android.os.Bundle
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

class RegisterActivity : AppCompatActivity() {
    val defaultGender: String = "-- Select --"
    val defaultAgeGroup: String = "-- Select --"

    var selectedGender: String = defaultGender
    var selectedAgeGroup: String = defaultAgeGroup

    lateinit var genderSpinner: Spinner
    lateinit var ageGroupSpinner: Spinner
    lateinit var firstNameEntry: EditText
    lateinit var lastNameEntry: EditText

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val user: TextView = findViewById(R.id.nameLabel)
        val data = intent?.getStringExtra("user")
        user.text = "Welcome " + data + "! 👋"

        val genders = arrayOf(defaultGender, "Male", "Female")
        val ageGroups = arrayOf(defaultAgeGroup, "18 to 25", "26 to 35", "36 to 45", "46 or Older")

        firstNameEntry = findViewById(R.id.firstNameEntry)
        lastNameEntry = findViewById(R.id.lastNameEntry)
        val resetButton: Button = findViewById(R.id.resetButton)
        val submitButton: Button = findViewById(R.id.saveButton)
        genderSpinner = findViewById(R.id.genderSpinner)
        ageGroupSpinner = findViewById(R.id.ageGroupSpinner)

        genderSpinner.adapter =
            ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, genders)

        ageGroupSpinner.adapter =
            ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ageGroups)

        genderSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                selectedGender = genders.get(p2)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        ageGroupSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                selectedAgeGroup = ageGroups.get(p2)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        resetButton.setOnClickListener {
           resetEntries(true)
        }

        submitButton.setOnClickListener {
            if (selectedAgeGroup == defaultAgeGroup) {
                notify("Please select an Age Group")
                return@setOnClickListener
            }

            if (selectedGender == defaultGender) {
                notify("Please select a Gender")
                return@setOnClickListener
            }

            if (firstNameEntry.text.length <= 0) {
                notify("Please enter a First Name")
                return@setOnClickListener
            }

            if (lastNameEntry.text.length <= 0) {
                notify("Please enter a Last Name")
                return@setOnClickListener
            }

            resetEntries(false)
            notify("You have been registered!")
        }
    }

    private  fun resetEntries(showMessage: Boolean) {
        selectedGender = defaultGender
        selectedAgeGroup = defaultAgeGroup

        genderSpinner.setSelection(0, true)
        ageGroupSpinner.setSelection(0, true)

        firstNameEntry.text.clear()
        lastNameEntry.text.clear()

        if (showMessage)
            Toast.makeText(this, "Form has been reset", Toast.LENGTH_LONG).show()
    }

    private fun notify(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show()
    }
}