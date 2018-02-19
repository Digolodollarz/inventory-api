package tech.diggle.labmanapi.student

import javax.servlet.http.HttpServletRequest

interface StudentService {
    fun add(student: Student): Student
    fun edit(student: Student): Student
    fun get(id: Long): Student
    fun getCurrent(request: HttpServletRequest): Student
    fun getAll(): List<Student>
    fun getByUsername(username: String): Student
}