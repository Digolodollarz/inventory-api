package tech.diggle.labmanapi.security.service

import com.sun.javaws.exceptions.InvalidArgumentException
import org.springframework.security.crypto.bcrypt.BCrypt
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import tech.diggle.labmanapi.security.model.Authority
import tech.diggle.labmanapi.security.model.User
import tech.diggle.labmanapi.security.repository.UserRepository
import java.util.*

@Service
class UserDetailServiceImpl(val userRepository: UserRepository,
                            val authorityRepository: AuthorityRepository) {
    val bCrypt: BCryptPasswordEncoder = BCryptPasswordEncoder()
    fun create(user: User): User {
        if (user.username == null) throw NullPointerException("Username cannot be null")
        if (user.password == null) throw NullPointerException("Password yako cannot be nothing")
        user.password = bCrypt.encode(user.password)
        if (user.firstname == null) throw NullPointerException("Du hast einen Namen")
        if (user.lastname == null) throw NullPointerException("Wena uthi hauna iSurname?")
        if (user.email == null) throw NullPointerException("Email address cannot be null")
        if (user.enabled == null) user.enabled = true
        if (user.lastPasswordResetDate == null) user.lastPasswordResetDate = Date(1509494400)
        user.authorities = user.authorities?.mapNotNull { authority ->
            when {
                authority.name != null -> authorityRepository.findByName(authority.name.toString())
                authority.id != null -> authorityRepository.findOne(authority.id)
                else -> null
            }
        }

        if (userRepository.findByUsername(user.username!!) != null) throw IllegalArgumentException("Username taken")
        return userRepository.save(user)
    }

    fun update(user: User): User {
        val usr = userRepository.findByUsername(user.username!!) ?: throw IllegalArgumentException("Invalid user")

        return userRepository.save(usr)
    }
}