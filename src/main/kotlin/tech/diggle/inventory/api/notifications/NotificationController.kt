package tech.diggle.inventory.api.notifications

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import tech.diggle.inventory.api.security.jwt.JwtTokenUtil
import tech.diggle.inventory.api.security.user.UserRepository
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("api/notifications")
class NotificationController(val notificationService: NotificationService,
                             val userRepository: UserRepository) {
    @Value("\${jwt.header}")
    private val tokenHeader: String? = null

    @Autowired
    private val jwtTokenUtil: JwtTokenUtil? = null

    @GetMapping
    fun getAll(request: HttpServletRequest): List<Notification> {
        val token = request.getHeader(tokenHeader).substring(7)
        val username = jwtTokenUtil!!.getUsernameFromToken(token)
        return notificationService.findAllByUserId(userRepository.findByUsername(username).id!!)
    }
}