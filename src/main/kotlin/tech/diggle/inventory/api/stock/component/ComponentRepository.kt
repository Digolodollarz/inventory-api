package tech.diggle.inventory.api.stock.component

import org.springframework.data.repository.CrudRepository

interface ComponentRepository : CrudRepository<Component, Long> {
    fun findByManufacturerId(id: Long): List<Component>
    fun findByPartNumberContainingIgnoreCase(num: String): List<Component>
    fun findByTitleContainingIgnoreCase(title: String): List<Component>
    fun findByPartTitleContainingIgnoreCase(title: String): List<Component>
    fun findByDescriptionContainingIgnoreCase(desc: String): List<Component>
    fun findByManufacturerTitleContainingIgnoreCase(title: String): List<Component>
}