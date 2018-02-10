package tech.diggle.labmanapi.stock

interface ItemService {
    fun get(id: Long): Item
    fun getAll(): List<Item>
    fun getFiltered(queryText: String): List<Item>
    fun add(item: Item): Item
    fun update(item: Item): Item
}