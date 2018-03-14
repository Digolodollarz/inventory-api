package tech.diggle.inventory.api.stock.part

interface PartService {
    fun getPart(id: Long): Part
    fun addPart(part: Part): Part
    fun getParts(): List<Part>
}