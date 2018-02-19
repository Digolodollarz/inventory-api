package tech.diggle.labmanapi.access

import org.springframework.stereotype.Service
import tech.diggle.labmanapi.component.Component

@Service
class FakeStorageAccessImpl() : StorageAccess {
    override fun grantAccess(component: Component): Boolean {
        System.out.println("Access Granted to component " + component.title)
        return true
    }

}