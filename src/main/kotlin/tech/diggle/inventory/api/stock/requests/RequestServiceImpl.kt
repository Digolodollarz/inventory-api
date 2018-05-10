package tech.diggle.inventory.api.stock.requests

import com.sun.javaws.exceptions.InvalidArgumentException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import tech.diggle.inventory.api.security.jwt.JwtTokenUtil
import tech.diggle.inventory.api.security.user.UserRepository
import tech.diggle.inventory.api.stock.item.ItemRepository
import javax.servlet.http.HttpServletRequest

@Service
class RequestServiceImpl(@Autowired
                         private val jwtTokenUtil: JwtTokenUtil,
                         val repository: RequestRepository,
                         val itemRepository: ItemRepository,
                         val userRepository: UserRepository) : RequestService {
    @Value("\${jwt.header}")
    private val tokenHeader: String? = null

    override fun get(id: Long): Request {
        return this.repository.findOne(id)
    }

    override fun add(request: Request): Request {
        if (request.item.availableStock < 0) throw IllegalStateException("Too many items requested")
        if (request.item.limited && this.getUserRequests(request.user!!.id).count() > request.item.limits!!)
            throw IllegalStateException("You have exceeded your limits for this item.")
        request.item.availableStock--
        if (!request.item.consumable) request.item.takenStock++
        request.user = userRepository.findByUsername(request.user!!.username!!)
        if (request.user == null) throw IllegalArgumentException("User cannot be found around this place")
        itemRepository.save(request.item)
        return this.repository.save(request)
    }

    override fun update(request: Request): Request {
        return if (this.repository.exists(request.id))
            this.repository.save(request)
        else throw IllegalArgumentException("Request not found")
    }

    override fun getUserRequests(userId: Long): List<Request> {
        val requests = mutableListOf<Request>()
        requests.addAll(repository.findByStatusAndUserId(RequestStatus.OPEN, userId))
        requests.addAll(repository.findByStatusAndUserId(RequestStatus.GRANTED, userId))
        return requests
    }

    override fun approveRequest(request: Request): Request {
        request.status = RequestStatus.GRANTED
        return this.repository.save(request)
    }

    override fun getMyRequests(request: HttpServletRequest): List<Int> {
        val token = request.getHeader(tokenHeader).substring(7)
        val username = jwtTokenUtil.getUsernameFromToken(token)
        val userId = userRepository.findByUsername(username).id
        val diezer = mutableListOf<Int>()
        diezer.add(repository.findByStatusAndUserId(RequestStatus.OPEN, userId).size)
        diezer.add(repository.findByStatusAndUserId(RequestStatus.GRANTED, userId).size)
        diezer.add(repository.findByStatusAndUserId(RequestStatus.DENIED, userId).size)
        return diezer
    }

    override fun getRequests(request: HttpServletRequest): List<Int> {
        val token = request.getHeader(tokenHeader).substring(7)
        val username = jwtTokenUtil.getUsernameFromToken(token)
        val userId = userRepository.findByUsername(username).id
        val diezer = mutableListOf<Int>()
        diezer.add(repository.findByStatusAndUserId(RequestStatus.OPEN, userId).size)
        diezer.add(repository.findByStatusAndUserId(RequestStatus.GRANTED, userId).size)
        diezer.add(repository.findByStatusAndUserId(RequestStatus.DENIED, userId).size)
        return diezer
    }
}