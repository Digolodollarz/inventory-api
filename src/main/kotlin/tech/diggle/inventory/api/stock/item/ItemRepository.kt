package tech.diggle.inventory.api.stock.item

import org.springframework.data.repository.PagingAndSortingRepository
import tech.diggle.inventory.api.stock.item.Item

interface ItemRepository : PagingAndSortingRepository<Item, Long> {
    fun findByPartNumberContainingIgnoreCase(num: String): List<Item>
    fun findByNameContainingIgnoreCase(title: String): List<Item>
    fun findByDescriptionContainingIgnoreCase(desc: String): List<Item>
    fun findByManufacturerContainingIgnoreCase(title: String): List<Item>
}