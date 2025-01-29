package com.example.student_app

import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.student_app.repository.StudentRepository



    class NewStudentActivity : AppCompatActivity() {

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_new_student)

            val toolbar: Toolbar = findViewById(R.id.toolbar)
            setSupportActionBar(toolbar)
            supportActionBar?.setTitle("New Student")
            supportActionBar?.setDisplayHomeAsUpEnabled(true)

            val nameEditText: EditText = findViewById(R.id.nameEditText)
            val idEditText: EditText = findViewById(R.id.idEditText)
            val PhoneEditText: EditText = findViewById(R.id.phoneEditText)
            val AddressEditText: EditText = findViewById(R.id.addressEditText)
            val saveButton: Button = findViewById(R.id.saveButton)

            saveButton.setOnClickListener {
                val name = nameEditText.text.toString()
                val id = idEditText.text.toString()
                val Phone = PhoneEditText.text.toString()
                val Address = AddressEditText.text.toString()

                if (name.isNotEmpty() && id.isNotEmpty()) {
                    val newStudent = Student(id, name,Address,Phone)
                    StudentRepository.addStudent(newStudent)
                    finish()
                } else {
                    Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                }
            }
        }
        override fun onOptionsItemSelected(item: MenuItem): Boolean {
            if (item.itemId == android.R.id.home) {
                onBackPressed()
                return true
            }
            return super.onOptionsItemSelected(item)
        }
    }


