package tech.diggle.inventory.api.stock.requests

interface RequestService{
    fun get(id: Long): Request
    fun add(request: Request): Request
    fun update(request: Request): Request
}