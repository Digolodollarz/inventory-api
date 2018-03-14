package tech.diggle.inventory.api.stock.manufacturer

import org.springframework.data.repository.CrudRepository

interface ManufacturerRepository : CrudRepository<Manufacturer, Long>