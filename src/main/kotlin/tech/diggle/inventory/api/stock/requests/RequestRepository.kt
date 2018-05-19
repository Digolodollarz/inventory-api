package tech.diggle.inventory.api.stock.requests

import org.springframework.data.repository.PagingAndSortingRepository

interface RequestRepository : PagingAndSortingRepository<Request, Long> {
    fun findByStatusAndUserId(status: RequestStatus, userId: Long): List<Request>
    fun findByStatus(status: RequestStatus): List<Request>
}