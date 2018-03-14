package tech.diggle.inventory.api.stock.requests

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("newItemRequests")
class RequestController(val service: RequestService) {
    @GetMapping("/{id}")
    fun get(@PathVariable id: Long) = service.get(id)
}