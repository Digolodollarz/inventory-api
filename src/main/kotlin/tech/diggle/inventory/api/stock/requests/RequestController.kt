package tech.diggle.inventory.api.stock.requests

import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("api/request")
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


    /**
     * Used to get the requests that were made by a user,
     * This method return count of the requests at each state
     */

    @GetMapping("/mine")
    fun getMyRequests(request: HttpServletRequest): List<Int> {
        return service.getMyRequests(request)
    }


    /**
     * Used to get all the requests that have been made.,,
     * This method return count of the requests at each state
     */

    @GetMapping("/all")
    fun getRequests(request: HttpServletRequest): List<Int> {
        return service.getRequests(request)
    }


}