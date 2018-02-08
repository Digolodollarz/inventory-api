package tech.diggle.labmanapi.part

import org.springframework.data.repository.CrudRepository

interface PartRepository : CrudRepository<Part, Long> {
}