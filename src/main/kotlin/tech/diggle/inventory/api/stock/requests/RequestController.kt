package tech.diggle.inventory.api.stock.requests

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("newItemRequests")
class RequestController(val service: RequestService) {
    @GetMapping("/{id}")
    fun get(@PathVariable id: Long) = service.get(id)

    /**
     * Used to update an existing request
     * @param req Request to update.
     * @throws IllegalArgumentException when the request is not found in the database
     */
    @PutMapping()
    fun update(@RequestBody req: Request) = service.update(req)

    /**
     * Used to create a new request.
     * @param req: The new request.
     */
    @PostMapping()
    fun add(@RequestBody req: Request) = service.add(req)
}