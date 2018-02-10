package tech.diggle.labmanapi.requests


import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/request")
class ComponentRequestController(val componentRequestService: ComponentRequestService) {
    @GetMapping()
    fun getAll(): List<ComponentRequest> = this.componentRequestService.getAll()

    @PostMapping
    fun add(@RequestBody componentRequest: ComponentRequest): ComponentRequest
            = this.componentRequestService.add(componentRequest)

    @GetMapping("/{id}")
    fun get(@PathVariable id: Long): ComponentRequest = this.componentRequestService.get(id)

    @PostMapping("/{id}")
    fun update(@PathVariable id: Long,
               @RequestBody componentRequest: ComponentRequest,
               @RequestParam("approve") approve: Boolean): ComponentRequest {
        return if (approve) componentRequestService.approve(componentRequest) else
            componentRequestService.deny(componentRequest)
    }

    @PostMapping("/{id}/cancel")
    fun cancel(
            @PathVariable id: Long
    ): Boolean {
        return this.componentRequestService.cancel(id)
    }
}