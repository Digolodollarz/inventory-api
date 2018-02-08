package tech.diggle.labmanapi.part

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/part")
class PartController (val partService: PartService) {
    @GetMapping("")
    fun getAll(): List<Part>{
        return partService.getParts()
    }

    @PostMapping("")
    fun add(@RequestBody part: Part): Part{
        return partService.addPart(part)
    }

    @GetMapping("/{id}")
    fun get(@PathVariable id: Long): Part{
        return partService.getPart(id)
    }
}