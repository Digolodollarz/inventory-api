package tech.diggle.inventory.api.stock.item

import org.springframework.stereotype.Service

@Service
class ItemServiceImpl(val itemRepository: ItemRepository) : ItemService {
    override fun get(id: Long): Item {
        return this.itemRepository.findOne(id)
    }

    override fun getAll(): List<Item> {
        return this.itemRepository.findAll() as List<Item>
    }

    override fun getFiltered(queryText: String): List<Item> {
        val returnItems: MutableList<Item> = mutableListOf()
        returnItems.addAll(itemRepository.findByComponentPartNumberContainingIgnoreCase(queryText))
        returnItems.addAll(itemRepository.findByComponentTitleContainingIgnoreCase(queryText))
        returnItems.addAll(itemRepository.findByComponentTitleContainingIgnoreCase(queryText))
        returnItems.addAll(itemRepository.findByComponentDescriptionContainingIgnoreCase(queryText))
        returnItems.addAll(itemRepository.findByComponentManufacturerTitleContainingIgnoreCase(queryText))
        returnItems.addAll(itemRepository.findByComponentPartTitleContainingIgnoreCase(queryText))
        return returnItems.distinct()
    }

    override fun add(item: Item): Item {
        return this.itemRepository.save(item)
    }

    override fun update(item: Item): Item {
        return this.itemRepository.save(item)
    }

}
