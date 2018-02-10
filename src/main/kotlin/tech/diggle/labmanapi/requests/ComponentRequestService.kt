package tech.diggle.labmanapi.requests

interface ComponentRequestService {
    fun getAll(): List<ComponentRequest>
    fun getByStudentId(id: Long): List<ComponentRequest>
    fun getByComponentId(id: Long): List<ComponentRequest>
    fun get(id: Long): ComponentRequest
    fun add(request: ComponentRequest): ComponentRequest
    fun approve(request: ComponentRequest): ComponentRequest
    fun deny(request: ComponentRequest): ComponentRequest
    fun cancel(requestId: Long): Boolean
}