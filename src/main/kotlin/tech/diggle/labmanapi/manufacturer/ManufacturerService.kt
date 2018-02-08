package tech.diggle.labmanapi.manufacturer

interface ManufacturerService{
    fun add(manufacturer: Manufacturer) : Manufacturer
    fun get(id: Long): Manufacturer
    fun getAll(): List<Manufacturer>
    fun update(manufacturer: Manufacturer) : Manufacturer
}