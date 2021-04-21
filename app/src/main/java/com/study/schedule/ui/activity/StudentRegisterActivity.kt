package com.study.schedule.ui.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.study.schedule.R
import com.study.schedule.dao.StudentDao
import com.study.schedule.model.Student



const val APP_BAR_TITLE_ADD_STUDENT = "Adicione o aluno"
const val APP_BAR_TITLE_EDIT_STUDENT = "Edite o aluno"

class StudentRegisterActivity : AppCompatActivity() {

   private val studentDao = StudentDao()
    private lateinit var student : Student
    private var nameText : EditText? = null
    private var phoneText : EditText? = null
    private var emailText : EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_register)

        getTextData()

        fillTextValues()

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId==R.id.activity_student_register_save_option){
            finishForm()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.activity_students_register_options_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun getTextData() {
        nameText = findViewById(R.id.activity_student_register_name)
        phoneText = findViewById(R.id.activity_student_register_phone)
        emailText = findViewById(R.id.activity_student_register_mail)
    }

    private fun fillTextValues() {

        val data = intent

        if (data.hasExtra(STUDENT_KEY)){

            student = intent.getSerializableExtra(STUDENT_KEY) as Student
            title = APP_BAR_TITLE_EDIT_STUDENT
            fillStudentFields()
        }else{
            title = APP_BAR_TITLE_ADD_STUDENT
            student = Student()
        }

    }

    private fun fillStudentFields() {
        nameText?.setText(student.name)
        phoneText?.setText(student.phone)
        emailText?.setText(student.email)
    }



    private fun finishForm() {
        fillStudent()
        if (student.hasValidId()) {
            studentDao.update(student)
        } else {
            studentDao.save(student)
        }
        finish()
    }

    private fun fillStudent() {

        val name = nameText?.text.toString()
        val phone = phoneText?.text.toString()
        val email = emailText?.text.toString()

        student.name = name
        student.phone = phone
        student.email = email

    }


}