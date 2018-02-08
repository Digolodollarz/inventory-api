package tech.diggle.labmanapi.manufacturer

import org.springframework.stereotype.Service

@Service
class ManufacturerServiceImpl(val manufacturerRepository: ManufacturerRepository) : ManufacturerService {
    override fun add(manufacturer: Manufacturer): Manufacturer {
        if (manufacturer.title.isBlank())
            throw IllegalArgumentException("Title cannot be null")
        return manufacturerRepository.save(manufacturer)
    }

    override fun get(id: Long): Manufacturer {
        return manufacturerRepository.findOne(id)
    }

    @Suppress("UNCHECKED_CAST")
    override fun getAll(): List<Manufacturer> {
        return manufacturerRepository.findAll() as List<Manufacturer>
    }

    override fun update(manufacturer: Manufacturer): Manufacturer {
        if (manufacturer.title.isBlank())
            throw IllegalArgumentException("Title cannot be null")
        return manufacturerRepository.save(manufacturer)
    }

}