package com.study.schedule.model

import java.io.Serializable

class Student : Serializable {

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
