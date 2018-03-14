package tech.diggle.inventory.api.stock.item

import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("api/item")
class ItemController(val itemService: ItemService) {
    @GetMapping
    fun getAll(@RequestParam("queryText") queryText: Optional<String>): List<Item> =
            if (queryText.isPresent) itemService.getFiltered(queryText.get()) else itemService.getAll()

    @PostMapping
    fun add(@RequestBody item: Item): Item = itemService.add(item)

    @GetMapping("{id}")
    fun get(@PathVariable id: Long): Item = itemService.get(id)

    @PostMapping("{id}")
    fun update(@RequestBody item: Item): Item = itemService.add(item)

}