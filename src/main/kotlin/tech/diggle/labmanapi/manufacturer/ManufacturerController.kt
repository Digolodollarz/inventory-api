package tech.diggle.labmanapi.manufacturer

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/manufacturer")
class ManufacturerController {
    @GetMapping()
    fun getAll(): List<Manufacturer> {
        return listOf(
                Manufacturer().apply {
                    id = 0
                    title = "Digolodollarz Technologies"
                    description = "Just another component manufacturer"
                },
                Manufacturer().apply {
                    id = 1
                    title = "Vigilance Electronics"
                    description = "Honaiwo."
                },
                Manufacturer().apply {
                    id = 2
                    title = "Digolodollarz Dzika"
                    description = "Ndini wacho ndine chipatani chinoita"
                },
                Manufacturer().apply {
                    id = 3
                    title = "TBone Inc."
                    description = "All the robots"
                },
                Manufacturer().apply {
                    id = 4
                    title = "Onion Technologies"
                    description = "YOu in the kitchen? Onions got your back"
                },
                Manufacturer().apply {
                    id = 5
                    title = "Forest Technologies"
                    description = "For when you want to go hunting in style"
                },
                Manufacturer().apply {
                    id = 6
                    title = "Digolodollarz Farming"
                    description = "Just another component manufacturer for when you are down there"
                }
        )
    }

    @PostMapping("/add")

    fun add(@RequestBody manufacturer: Manufacturer): Manufacturer {
        manufacturer.id = 43
        return manufacturer
    }
}