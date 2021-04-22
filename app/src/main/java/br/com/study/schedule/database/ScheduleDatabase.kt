package br.com.study.schedule.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.study.schedule.dao.RoomStudentDao
import br.com.study.schedule.model.Student

@Database(entities = [Student::class],version = 1, exportSchema = false)
abstract class ScheduleDatabase : RoomDatabase() {

    abstract fun getRoomStudentDao() : RoomStudentDao

    companion object{

        private const val DATABASE_NAME = "schedule.db"

        fun getInstance(context: Context) : RoomStudentDao{
            return Room.databaseBuilder(context,ScheduleDatabase::class.java, DATABASE_NAME)
                .allowMainThreadQueries().build().getRoomStudentDao()
        }
    }


}