package tech.diggle.inventory.api.security.authority

import org.springframework.data.repository.CrudRepository
import tech.diggle.inventory.api.security.authority.Authority

interface AuthorityRepository : CrudRepository<Authority, Long> {
    fun findByName(name: String): Authority
}