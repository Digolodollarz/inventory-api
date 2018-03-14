package tech.diggle.inventory.api.stock.component

import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("api/component")
class ComponentController(val componentService: ComponentService) {

    @GetMapping()
    fun getFiltered(@RequestParam("queryText") queryText: Optional<String>) =
            if(queryText.isPresent) componentService.getFiltered(queryText.get()) else componentService.getAll()

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')") //TODO: Allow some techs
    fun add(@RequestBody component: Component) = componentService.add(component)

    @GetMapping("/{id}")
    fun get(@PathVariable id: Long) = componentService.get(id)

    @PostMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    fun update(@RequestBody component: Component) = componentService.update(component)
}
