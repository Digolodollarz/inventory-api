package tech.diggle.labmanapi.requests

interface ComponentRequestService {
    fun getAll(): List<ComponentRequest>
    fun getAllPending(): List<ComponentRequest>
    fun getByStudentId(id: Long): List<ComponentRequest>
    fun getByComponentId(id: Long): List<ComponentRequest>
    fun get(id: Long): ComponentRequest
    fun add(request: ComponentRequest): ComponentRequest
    fun approve(request: ComponentRequest): ComponentRequest
    fun deny(request: ComponentRequest, reason: String): ComponentRequest
    fun cancel(requestId: Long): Boolean
    fun getByUserAndComponent(userId: Long,componentId: Long): List<ComponentRequest>
}