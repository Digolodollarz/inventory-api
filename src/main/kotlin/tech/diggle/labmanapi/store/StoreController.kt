package tech.diggle.labmanapi.store

import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/store")
class StoreController(val componentService: ComponentService) {
    @GetMapping("/components")
    fun getAllComponents() = componentService.getAll()

    @PostMapping("/component")
    @PreAuthorize("hasRole('ADMIN')")
    fun addComponent(@RequestBody component: Component): Component {
        return componentService.add(component)
    }

    @GetMapping("/component/{componentId}")
    fun getComponent(@PathVariable componentId: Long) = componentService.get(componentId)
}