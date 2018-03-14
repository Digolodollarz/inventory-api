package tech.diggle.inventory.api.stock.manufacturer

import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/manufacturer")
class ManufacturerController(val manufacturerService: ManufacturerService) {

    @GetMapping()
    fun getAll(): List<Manufacturer> {
        return manufacturerService.getAll()
    }

    @PostMapping()
    @PreAuthorize("hasRole('ADMIN')")
    fun add(@RequestBody manufacturer: Manufacturer): Manufacturer = manufacturerService.add(manufacturer)

    @GetMapping("/{id}")
    fun get(@PathVariable id: Long) = manufacturerService.get(id)
}