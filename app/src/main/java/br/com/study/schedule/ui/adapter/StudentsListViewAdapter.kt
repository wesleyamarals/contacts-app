package br.com.study.schedule.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.study.schedule.R
import com.study.schedule.model.Student

class StudentsListViewAdapter(private val context: Context) : BaseAdapter() {

    private val studentsList = ArrayList<Student>()

    override fun getCount(): Int {
            return studentsList.size
        }

        override fun getItem(position: Int): Student {
            return studentsList[position]
        }

        override fun getItemId(position: Int): Long {
            return studentsList[position].id.toLong()
        }

        override fun getView(position: Int, convertView: View?, viewGroup: ViewGroup?): View {
            val createdView : View = createView(viewGroup)
            val selectedStudent = studentsList[position]
            fillFields(createdView, selectedStudent)
            return createdView
        }

    private fun fillFields(createdView: View, selectedStudent: Student) {
        val name: TextView = createdView.findViewById(R.id.student_item_name)
        name.text = selectedStudent.name
        val phone: TextView = createdView.findViewById(R.id.student_item_phone)
        phone.text = selectedStudent.phone
    }

    private fun createView(viewGroup:ViewGroup?): View {
        return LayoutInflater.from(context).inflate(R.layout.student_item,viewGroup,false)
    }

    fun clear(){
        studentsList.clear()
    }

    fun addAll(students : List<Student>){
        studentsList.addAll(students)
    }

    fun remove(student:Student){
        studentsList.remove(student)
        notifyDataSetChanged()
    }

    fun update(students: List<Student>){
        clear()
        addAll(students)
        notifyDataSetChanged()
    }

}
