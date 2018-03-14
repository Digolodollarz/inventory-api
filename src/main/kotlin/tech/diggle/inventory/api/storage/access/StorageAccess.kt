package tech.diggle.inventory.api.storage.access

import tech.diggle.inventory.api.storage.Storage

interface StorageAccess {
    fun grantAccess(storage: Storage): Boolean
}