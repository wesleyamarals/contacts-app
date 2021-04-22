package br.com.study.schedule.activity

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import br.com.study.schedule.dao.RoomStudentDao
import br.com.study.schedule.ui.adapter.StudentsListViewAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.study.schedule.R
import br.com.study.schedule.dao.StudentDao
import br.com.study.schedule.database.ScheduleDatabase
import br.com.study.schedule.model.Student

const val APP_BAR_TITLE_STUDENTS_LIST = "Lista de Alunos"

class StudentListActivity : AppCompatActivity() {

    private lateinit var studentsDao : RoomStudentDao
    private lateinit var adapter : StudentsListViewAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_list)

        this.title = APP_BAR_TITLE_STUDENTS_LIST

        studentsDao = ScheduleDatabase.getInstance(this)

        configureListView()
        configureButtonAddStudent()
    }

    private fun configureListView() {

        val studentsList: ListView = findViewById(R.id.student_list_activity_students_list)

        configureAdapter(studentsList)
        configureOnItemClick(studentsList)
        registerForContextMenu(studentsList)
    }

    private fun configureAdapter(studentsList: ListView) {
        adapter = StudentsListViewAdapter(this)
        studentsList.adapter = adapter
    }

    private fun configureOnItemClick(studentsList: ListView) {
        studentsList.setOnItemClickListener { adapterView: AdapterView<*>, _, position: Int, _ ->

            val selectedStudent : Student = adapterView.getItemAtPosition(position) as Student

            startActivity(Intent(this, StudentRegisterActivity::class.java).putExtra("student", selectedStudent))

        }
    }


    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.activity_students_list_menu,menu)
    }

    override fun onResume() {

        super.onResume()
        updateListView()

    }

    private fun updateListView() {
        adapter.update(studentsDao.findAllStudents())
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {

        if (item.itemId == R.id.activity_student_list_menu_remove) {

            removeConfirmation(item)
        }
        return super.onContextItemSelected(item)

    }

    private fun removeConfirmation(item: MenuItem) {
        val menuInfo: AdapterView.AdapterContextMenuInfo = item.menuInfo as AdapterView.AdapterContextMenuInfo
        AlertDialog.Builder(this).setTitle("Remover Aluno?").setPositiveButton("Sim") { _: DialogInterface, _: Int ->

            val selectedStudent: Student = adapter.getItem(menuInfo.position)
            removeStudent(selectedStudent)
        }.setNegativeButton("Não", null).show()
    }


    private fun removeStudent(selectedStudent: Student) {
        studentsDao.remove(selectedStudent)
        adapter.remove(selectedStudent)
    }

    private fun configureButtonAddStudent() {
        val buttonAddStudent: FloatingActionButton = findViewById(R.id.activity_student_list_fab_add_student)
        buttonAddStudent.setOnClickListener {
            startActivity(Intent(this, StudentRegisterActivity::class.java))
        }
    }






}