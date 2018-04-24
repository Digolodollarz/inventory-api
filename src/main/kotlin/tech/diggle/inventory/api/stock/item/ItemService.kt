package tech.diggle.inventory.api.stock.item

import tech.diggle.inventory.api.stock.item.Item

interface ItemService {
    fun get(id: Long): Item
    fun getAll(): List<Item>
    fun getFiltered(queryText: String): List<Item>
    fun getPaged(page: Int?, size: Int?): List<Item>
    fun add(item: Item): Item
    fun update(item: Item): Item
}