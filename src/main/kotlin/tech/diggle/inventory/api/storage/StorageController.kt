package tech.diggle.inventory.api.storage

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/storage")
class StorageController(val storageService: StorageService) {
    @GetMapping
    fun getAll(): List<Storage> = storageService.findAll()

    @PostMapping
    fun add(@RequestBody storage: Storage): Storage = storageService.add(storage)

    @GetMapping("/get/{requestId}")
    fun getItem(@PathVariable requestId: Long): String {
        return "Zvaita!"
    }

    @GetMapping("{id}")
    fun get(@PathVariable id: Long): Storage = storageService.get(id)

    @PostMapping("{id}")
    fun update(@RequestBody storage: Storage) = storageService.update(storage)

}