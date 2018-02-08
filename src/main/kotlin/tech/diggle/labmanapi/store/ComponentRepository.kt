package tech.diggle.labmanapi.store

import org.springframework.data.repository.CrudRepository

interface ComponentRepository : CrudRepository<Component, Long>{
    fun findByManufacturerId(id: Long) : List<Component>
}