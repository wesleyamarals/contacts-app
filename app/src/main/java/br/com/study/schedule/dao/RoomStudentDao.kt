package br.com.study.schedule.dao

import androidx.room.*
import br.com.study.schedule.model.Student

@Dao
interface RoomStudentDao {

    @Query("SELECT * FROM student")
    fun findAllStudents(): List<Student>

    @Delete
    fun remove(selectedStudent: Student)

    @Update
    fun update(student: Student)

    @Insert
    fun save(student: Student)


}