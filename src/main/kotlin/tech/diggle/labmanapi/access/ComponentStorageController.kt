package tech.diggle.labmanapi.access

import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import tech.diggle.labmanapi.requests.ComponentRequest
import tech.diggle.labmanapi.requests.ComponentRequestService

@RestController
@RequestMapping("api/component-storage")
class ComponentStorageController(val requests: ComponentRequestService,
                                 val storages: ComponentStorageService) {
    @GetMapping
    fun getCurrentStores() = storages.getAll()

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    fun storeComponent(@RequestBody componentStorage: ComponentStorage)
            = storages.storeComponent(componentStorage)

    @DeleteMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    fun removeComponent(@RequestBody componentStorage: ComponentStorage)
            = storages.removeComponent(componentStorage)

    @GetMapping("/retrieve{requestId}")
    fun retrieveComponent(@PathVariable requestId: Long) {
    }

}