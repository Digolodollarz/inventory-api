package tech.diggle.labmanapi.dashboard

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import tech.diggle.labmanapi.security.JwtTokenUtil
import tech.diggle.labmanapi.security.model.User
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("api/dashboard")
public class DashboardController(val dashboardService: DashboardService) {
    @Value("\${jwt.header}")
    private val tokenHeader: String? = null

    @Autowired
    private val jwtTokenUtil: JwtTokenUtil? = null

    @Autowired
    private val userDetailsService: UserDetailsService? = null
    @GetMapping("header-items")
    fun getAdminHeaderItems(request: HttpServletRequest): List<AdminHeaderCard> {
        val token = request.getHeader(tokenHeader).substring(7)
        val username = jwtTokenUtil!!.getUsernameFromToken(token)
        return dashboardService.getHeaderCards(username)
    }

}
