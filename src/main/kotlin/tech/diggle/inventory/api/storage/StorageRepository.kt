package tech.diggle.inventory.api.storage

import org.springframework.data.repository.CrudRepository

interface StorageRepository: CrudRepository<Storage, Long>{
    fun findByTitleContainsIgnoreCase(title: String): List<Storage>
    fun findByKindIgnoreCase(kind: String): List<Storage>
}