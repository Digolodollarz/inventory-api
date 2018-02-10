package tech.diggle.labmanapi.storage

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/storage")
class StorageController(val storageService: StorageService) {
    @GetMapping
    fun getAll(): List<Storage> = storageService.findAll()

    @PostMapping
    fun add(@RequestBody storage: Storage): Storage = storageService.add(storage)

    @GetMapping("{id}")
    fun get(@PathVariable id: Long): Storage = storageService.get(id)

    @PostMapping("{id}")
    fun update(@RequestBody storage: Storage) = storageService.update(storage)

}