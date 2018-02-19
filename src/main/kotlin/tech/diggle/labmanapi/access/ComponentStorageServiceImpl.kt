package tech.diggle.labmanapi.access

import org.springframework.stereotype.Service

@Service
class ComponentStorageServiceImpl(val storages: ComponentStorageRepository) : ComponentStorageService {
    override fun storeComponent(componentStorage: ComponentStorage): ComponentStorage {
        return this.storages.save(componentStorage)
    }

    override fun removeComponent(componentStorage: ComponentStorage)
            = storages.delete(componentStorage)

    override fun getAll(): List<ComponentStorage> {
        return this.storages.findAll() as List<ComponentStorage>
    }
}