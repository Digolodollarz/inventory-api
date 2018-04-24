package tech.diggle.inventory.api.security.user


import org.springframework.data.jpa.repository.JpaRepository
import tech.diggle.inventory.api.security.user.User

interface UserRepository : JpaRepository<User, Long> {
    fun findByUsername(username: String): User
}