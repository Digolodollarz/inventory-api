package tech.diggle.inventory.api.security.user

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.web.bind.annotation.*
import tech.diggle.inventory.api.security.jwt.JwtTokenUtil
import tech.diggle.inventory.api.security.jwt.JwtUser

import javax.servlet.http.HttpServletRequest

@RestController
class UserRestController {

    @Value("\${jwt.header}")
    private val tokenHeader: String? = null

    @Autowired
    private val jwtTokenUtil: JwtTokenUtil? = null

    @Autowired
    private val userDetailsService: UserDetailsService? = null

    @Autowired
    private val userDetailService: UserDetailServiceImpl? = null


    @RequestMapping("user", method = [(RequestMethod.GET)])
    fun getAuthenticatedUser(request: HttpServletRequest): JwtUser {
        val token = request.getHeader(tokenHeader).substring(7)
        val username = jwtTokenUtil!!.getUsernameFromToken(token)
        return userDetailsService!!.loadUserByUsername(username) as JwtUser
    }

//    @RequestMapping("student/current", method = [(RequestMethod.GET)])
//    fun getAuthenticatedStudent(request: HttpServletRequest): Student {
//        val token = request.getHeader(tokenHeader).substring(7)
//        val username = jwtTokenUtil!!.getUsernameFromToken(token)
//        return studentService.getByUsername(username)
//    }

    @GetMapping("user/{userName}")
    fun getUser(@PathVariable userName: String): JwtUser {
        return userDetailsService!!.loadUserByUsername(userName) as JwtUser
    }

    @PostMapping("user")
    @PreAuthorize("hasRole('ADMIN')")
    fun addUser(@RequestBody user: User): JwtUser {
        val usr = userDetailService!!.create(user)
        return userDetailsService!!.loadUserByUsername(usr.username) as JwtUser
    }

}
