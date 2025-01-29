package com.example.student_app

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.student_app.repository.StudentRepository

class EditStudentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_student)


        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.setTitle("Edit Student")
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        val studentId = intent.getStringExtra("studentId")
        if (studentId == null) {
            finish()
            return
        }


        val student = StudentRepository.getStudentById(studentId)
        if (student != null) {

            val nameEditText: EditText = findViewById(R.id.nameEditText)
            val idEditText: EditText = findViewById(R.id.idEditText)
            val phoneEditText: EditText = findViewById(R.id.phoneEditText)
            val addressEditText: EditText = findViewById(R.id.addressEditText)
            val studentStatusCheckbox: CheckBox = findViewById(R.id.studentStatusCheckbox)
            val saveButton: Button = findViewById(R.id.saveButton)
            val deleteButton: Button = findViewById(R.id.deleteButton)
            val cancelButton: Button = findViewById(R.id.CancelButton)


            nameEditText.setText(student.name)
            idEditText.setText(student.id)
            phoneEditText.setText(student.Phone)
            addressEditText.setText(student.Address)
            studentStatusCheckbox.isChecked = student.isChecked

            saveButton.setOnClickListener {
                val updatedName = nameEditText.text.toString()
                val updatedId = idEditText.text.toString()
                val updatePhone = phoneEditText.text.toString()
                val updateAddress = addressEditText.text.toString()

                if (updatedName.isNotEmpty() && updatedId.isNotEmpty()) {
                    student.name = updatedName
                    student.id = updatedId
                    student.Phone = updatePhone
                    student.Address = updateAddress
                    student.isChecked = studentStatusCheckbox.isChecked
                    StudentRepository.updateStudent(student)


                    finish()
                } else {
                    Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                }
            }
            cancelButton.setOnClickListener {
                finish()
            }

            deleteButton.setOnClickListener {
                StudentRepository.deleteStudent(student.id)

                val intent = Intent(this, StudentListActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
                finish()
            }
        } else {
            finish()
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































