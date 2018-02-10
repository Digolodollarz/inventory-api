package tech.diggle.labmanapi.student

import org.springframework.data.repository.CrudRepository

interface StudentRepository : CrudRepository<Student, Long>{
    fun findByStudentId(id: Long):Student
}