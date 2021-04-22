package br.com.study.schedule.dao

import br.com.study.schedule.model.Student

val students : MutableList<Student> = mutableListOf()
var idIncrement = 1

class StudentDao {

    fun save(student: Student) {
        student.id = idIncrement
        students.add(student)

        updateId()


    }

    private fun updateId() {
        idIncrement++
    }

    fun findAllStudents() : List<Student>{
        val studentsList = mutableListOf<Student>()
        students.forEach{
            studentsList.add(it)
        }
        return studentsList
    }

    fun update(student: Student) {

        val studentFound : Student? = findStudentById(student)

        if(studentFound!=null){
            val index = students.indexOf(studentFound)
            students[index] = student
        }

    }

    fun remove(student:Student){
        val studentFound : Student? = findStudentById(student)
        if(studentFound!=null){
            students.remove(studentFound)
        }
    }

    private fun findStudentById(student : Student): Student? {

        students.forEach {
            if(it.id == student.id){
                return it
            }
        }
        return null
    }



}
