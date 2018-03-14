package tech.diggle.inventory.api.stock.requests

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity(name = "new_item_request")
class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long = 0
    var title: String = ""
    var description: String = ""
    var status: RequestStatus = RequestStatus.OPEN
    var reason: String? = null
}