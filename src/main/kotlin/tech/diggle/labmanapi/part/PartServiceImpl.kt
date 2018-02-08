package tech.diggle.labmanapi.part

import org.springframework.stereotype.Service

@Service
class PartServiceImpl(val partRepository: PartRepository) : PartService {
    override fun getPart(id: Long): Part {
        return partRepository.findOne(id)
    }

    override fun addPart(part: Part): Part {
        return partRepository.save(part)
    }

    override fun getParts(): List<Part> {
        return partRepository.findAll() as List<Part>
    }
}