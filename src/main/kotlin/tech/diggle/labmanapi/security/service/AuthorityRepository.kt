package tech.diggle.labmanapi.security.service

import org.springframework.data.repository.CrudRepository
import tech.diggle.labmanapi.security.model.Authority

interface AuthorityRepository : CrudRepository<Authority, Long> {
    fun findByName(name: String): Authority
}