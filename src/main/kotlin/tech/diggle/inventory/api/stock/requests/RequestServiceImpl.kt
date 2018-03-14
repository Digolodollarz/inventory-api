package tech.diggle.inventory.api.stock.requests

import org.springframework.stereotype.Service

@Service
class RequestServiceImpl(val repository: RequestRepository) : RequestService {
    override fun get(id: Long): Request {
        return this.repository.findOne(id)
    }

    override fun add(request: Request): Request {
        return this.repository.save(request)
    }

    override fun update(request: Request): Request {
        return if (this.repository.exists(request.id))
            this.repository.save(request)
        else throw IllegalArgumentException("Request not found")
    }
}