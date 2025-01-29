package com.example.student_app

import android.content.Intent
import android.os.Bundle

import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import android.widget.CheckBox
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar


import com.example.student_app.repository.StudentRepository
class StudentDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_details)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setTitle("Student's Details")
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val studentId = intent.getStringExtra("studentId")
        if (studentId == null) {
            finish()
            return
        }

        refreshStudentDetails(studentId)
    }


    private fun refreshStudentDetails(studentId: String) {
        val student = StudentRepository.getStudentById(studentId)
        if (student != null) {
            val nameTextView: TextView = findViewById(R.id.nameTextView)
            val idTextView: TextView = findViewById(R.id.idTextView)
            val AddressTextView: TextView = findViewById(R.id.addressLabelTextView)
            val PhoneTextView: TextView = findViewById(R.id.PhoneTextView)
            val studentStatusCheckbox: CheckBox = findViewById(R.id.studentStatusCheckbox)
            val statusTextView: TextView = findViewById(R.id.statusTextView)

            studentStatusCheckbox.isChecked = student.isChecked
            studentStatusCheckbox.isEnabled = false

            nameTextView.text = "Name: ${student.name}"
            idTextView.text = "ID: ${student.id}"
            PhoneTextView.text = "Address: ${student.Address}"
            AddressTextView.text = "Phone: ${student.Phone}"
            statusTextView.text = if (student.isChecked) "Checked" else "Unchecked"




        } else {
            finish()
        }


        val editButton: Button = findViewById(R.id.editButton)
        editButton.setOnClickListener {
            val intent = Intent(this, EditStudentActivity::class.java)
            intent.putExtra("studentId", studentId)
            startActivity(intent)
        }


    }

    override fun onResume() {
        super.onResume()
        val studentId = intent.getStringExtra("studentId")
        if (studentId != null) {
            refreshStudentDetails(studentId)
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












