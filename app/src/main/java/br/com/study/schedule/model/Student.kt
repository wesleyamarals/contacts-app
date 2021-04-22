package br.com.study.schedule.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
class Student : Serializable {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    var name: String = ""
    var phone: String = ""
    var email: String = ""


    @Override
    override fun toString(): String {
        return name
    }

    fun hasValidId(): Boolean {
        return id > 0
    }

}
