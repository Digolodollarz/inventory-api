package tech.diggle.labmanapi.component

import org.springframework.data.repository.CrudRepository

interface ComponentRepository : CrudRepository<Component, Long>{
    fun findByManufacturerId(id: Long) : List<Component>
}