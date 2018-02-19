package tech.diggle.labmanapi.requests


import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("api/request")
class ComponentRequestController(val componentRequestService: ComponentRequestService) {
    @GetMapping()
    fun getAll(): List<ComponentRequest> = this.componentRequestService.getAll()

    @GetMapping("pending")
    fun getAllPending(): List<ComponentRequest> = this.componentRequestService.getAllPending()

    @PostMapping
    fun add(@RequestBody componentRequest: ComponentRequest): ComponentRequest
            = this.componentRequestService.add(componentRequest)

    @GetMapping("/{id}")
    fun get(@PathVariable id: Long): ComponentRequest = this.componentRequestService.get(id)


    @GetMapping("mine/{userId}/{componentId}")
    fun getUserRequestsByComponent(@PathVariable userId: Long,
                                   @PathVariable componentId: Long): List<ComponentRequest>
            = this.componentRequestService.getByUserAndComponent(userId, componentId)

    @PostMapping("/{id}")
    fun update(@PathVariable id: Long,
               @RequestBody componentRequest: ComponentRequest,
               @RequestParam("approve") approve: Boolean,
               @RequestParam("reason") reason: Optional<String>): ComponentRequest {
        return if (approve) componentRequestService.approve(componentRequest) else
            componentRequestService.deny(componentRequest, reason.get())
    }

    @PostMapping("/{id}/cancel")
    fun cancel(
            @PathVariable id: Long
    ): Boolean {
        return this.componentRequestService.cancel(id)
    }
}