package tech.diggle.labmanapi.storage

import org.springframework.stereotype.Service

@Service
class StorageServiceImpl(val storageRepository: StorageRepository) : StorageService {
    override fun findAll(): List<Storage> {
        return storageRepository.findAll() as List<Storage>
    }

    override fun findFiltered(title: String): List<Storage> {
        return storageRepository.findByTitleContainsIgnoreCase(title)
    }

    override fun get(id: Long): Storage {
        return storageRepository.findOne(id)
    }

    override fun add(storage: Storage): Storage {
        return storageRepository.save(storage)
    }

    override fun update(storage: Storage): Storage {
        return storageRepository.save(storage)
    }

}