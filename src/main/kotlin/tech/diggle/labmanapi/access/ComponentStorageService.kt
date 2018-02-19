package tech.diggle.labmanapi.access

import org.springframework.data.repository.CrudRepository

interface ComponentStorageService {
    fun storeComponent(componentStorage: ComponentStorage): ComponentStorage
    fun removeComponent(componentStorage: ComponentStorage)
    fun getAll(): List<ComponentStorage>
}