package tech.diggle.inventory.api.storage.access

import org.springframework.stereotype.Service
import tech.diggle.inventory.api.storage.Storage

@Service
class FakeStorageAccessImpl() : StorageAccess {
    override fun grantAccess(storage: Storage): Boolean {
        System.out.println("Access Granted to component " + storage.title)
        return true
    }

}