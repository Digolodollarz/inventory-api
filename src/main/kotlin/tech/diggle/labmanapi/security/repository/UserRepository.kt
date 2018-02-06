package tech.diggle.labmanapi.security.repository


import org.springframework.data.jpa.repository.JpaRepository
import tech.diggle.labmanapi.security.model.User

/**
 * Created by stephan on 20.03.16.
 */
interface UserRepository : JpaRepository<User, Long> {
    fun findByUsername(username: String): User
}