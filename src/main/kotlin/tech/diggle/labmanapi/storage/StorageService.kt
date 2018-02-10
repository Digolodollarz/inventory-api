package tech.diggle.labmanapi.storage

interface StorageService {
    fun findAll(): List<Storage>
    fun findFiltered(title: String): List<Storage>
    fun get(id: Long): Storage
    fun add(storage: Storage): Storage
    fun update(storage: Storage): Storage
}