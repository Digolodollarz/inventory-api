package tech.diggle.labmanapi.part

interface PartService {
    fun getPart(id: Long): Part
    fun addPart(part: Part): Part
    fun getParts(): List<Part>
}