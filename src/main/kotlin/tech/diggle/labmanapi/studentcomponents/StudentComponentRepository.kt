package tech.diggle.labmanapi.studentcomponents

import org.springframework.data.repository.PagingAndSortingRepository

interface StudentComponentRepository : PagingAndSortingRepository<StudentComponent, Long> {
    fun getByStudentId(id: Long): List<StudentComponent>
    fun getByComponentId(id: Long): List<StudentComponent>
    fun getByReturned(returned: Boolean): List<StudentComponent>
}