package tech.diggle.inventory.api.stock.part

import org.springframework.data.repository.CrudRepository

interface PartRepository : CrudRepository<Part, Long> {
}