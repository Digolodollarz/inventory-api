package tech.diggle.inventory.api.stock.item

import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class ItemServiceImpl(val itemRepository: ItemRepository) : ItemService {
    override fun getPaged(page: Int?, size: Int?): List<Item> {
        return itemRepository.findAll(PageRequest(page ?: 0, size ?: 20)).content
    }

    override fun get(id: Long): Item {
        return this.itemRepository.findOne(id)
    }

    override fun getAll(): List<Item> {
        return this.itemRepository.findAll() as List<Item>
    }

    override fun getFiltered(queryText: String): List<Item> {
        val returnItems: MutableList<Item> = mutableListOf()
        returnItems.addAll(itemRepository.findByPartNumberContainingIgnoreCase(queryText))
        returnItems.addAll(itemRepository.findByNameContainingIgnoreCase(queryText))
        returnItems.addAll(itemRepository.findByDescriptionContainingIgnoreCase(queryText))
        returnItems.addAll(itemRepository.findByManufacturerContainingIgnoreCase(queryText))
        return returnItems.distinct()
    }

    override fun add(item: Item): Item {
        return this.itemRepository.save(item)
    }

    override fun update(item: Item): Item {
        return this.itemRepository.save(item)
    }

}
