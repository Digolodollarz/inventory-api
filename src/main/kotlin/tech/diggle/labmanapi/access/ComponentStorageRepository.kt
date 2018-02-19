package tech.diggle.labmanapi.access

import org.springframework.data.repository.CrudRepository

interface ComponentStorageRepository: CrudRepository<ComponentStorage, Long>{
    fun findByComponentId(id: Long): ComponentStorage
    fun findByStorageTitleContainsIgnoreCase(title: String): ComponentStorage
//    fun storeComponent(componentStorage: ComponentStorage)
}