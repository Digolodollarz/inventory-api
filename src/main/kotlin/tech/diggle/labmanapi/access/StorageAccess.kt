package tech.diggle.labmanapi.access

import tech.diggle.labmanapi.storage.Storage

interface StorageAccess {
    fun grantAccess(storage: Storage): Boolean
}