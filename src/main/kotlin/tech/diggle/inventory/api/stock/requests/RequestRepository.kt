package tech.diggle.inventory.api.stock.requests

import org.springframework.data.repository.PagingAndSortingRepository

interface RequestRepository : PagingAndSortingRepository<Request, Long>