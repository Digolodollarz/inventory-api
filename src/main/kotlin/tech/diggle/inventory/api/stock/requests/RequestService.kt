package tech.diggle.inventory.api.stock.requests

import javax.servlet.http.HttpServletRequest

interface RequestService {
    fun get(id: Long): Request
    fun add(request: Request): Request
    fun update(request: Request): Request
    fun getUserRequests(userId: Long): List<Request>
    fun approveRequest(request: Request): Request
    fun getMyRequests(request: HttpServletRequest) : List<Int>
    fun getRequests(request: HttpServletRequest) : List<Int>
}