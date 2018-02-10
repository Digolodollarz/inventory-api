package tech.diggle.labmanapi.requests

import org.springframework.data.repository.CrudRepository

interface ComponentRequestRepository : CrudRepository<ComponentRequest, Long> {
    fun getByStatus(status: RequestStatus): List<ComponentRequest>
    fun getByStudentId(id: Long): List<ComponentRequest>
    fun getByComponentId(id: Long): List<ComponentRequest>
}