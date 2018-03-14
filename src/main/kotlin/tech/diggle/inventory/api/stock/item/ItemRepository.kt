package tech.diggle.inventory.api.stock.item

import org.springframework.data.repository.PagingAndSortingRepository
import tech.diggle.inventory.api.stock.item.Item

interface ItemRepository : PagingAndSortingRepository<Item, Long> {
    fun getByComponentId(id: Long): Item


    //    Methods for filtering items
//    TODO: A better job
    fun findByComponentManufacturerId(id: Long): List<Item>
    fun findByComponentPartNumberContainingIgnoreCase(num: String): List<Item>
    fun findByComponentTitleContainingIgnoreCase(title: String): List<Item>
    fun findByComponentPartTitleContainingIgnoreCase(title: String): List<Item>
    fun findByComponentDescriptionContainingIgnoreCase(desc: String): List<Item>
    fun findByComponentManufacturerTitleContainingIgnoreCase(title: String): List<Item>
}