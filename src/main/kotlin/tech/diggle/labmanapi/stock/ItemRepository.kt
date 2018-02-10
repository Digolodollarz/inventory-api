package tech.diggle.labmanapi.stock

import org.springframework.data.repository.PagingAndSortingRepository

interface ItemRepository : PagingAndSortingRepository<tech.diggle.labmanapi.stock.Item, Long> {
    fun getByComponentId(id: Long): Item
}