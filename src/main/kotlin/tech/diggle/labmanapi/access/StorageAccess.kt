package tech.diggle.labmanapi.access

import tech.diggle.labmanapi.component.Component

interface StorageAccess {
    fun grantAccess(component: Component): Boolean
}