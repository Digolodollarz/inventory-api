package tech.diggle.labmanapi.student

interface StudentService {
    fun add(student: Student): Student
    fun edit(student: Student): Student
    fun get(id: Long): Student
    fun getAll(): List<Student>
}