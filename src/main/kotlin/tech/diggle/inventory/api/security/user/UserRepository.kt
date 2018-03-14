package tech.diggle.inventory.api.security.user


import org.springframework.data.jpa.repository.JpaRepository
import tech.diggle.inventory.api.security.user.User

/**
 * Created by stephan on 20.03.16.
 */
interface UserRepository : JpaRepository<User, Long> {
    fun findByUsername(username: String): User
}