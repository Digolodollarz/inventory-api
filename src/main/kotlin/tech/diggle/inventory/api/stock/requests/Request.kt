package tech.diggle.inventory.api.stock.requests

import tech.diggle.inventory.api.security.user.User
import tech.diggle.inventory.api.stock.item.Item
import java.util.*
import javax.persistence.*

@Entity(name = "new_item_request")
class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long = 0
    var status: RequestStatus = RequestStatus.OPEN
    var reason: String? = null
    @ManyToOne
    var item: Item = Item()
    @ManyToOne
    var user: User? = null
    var itemCount: Short = 0

    var dateCreated: Date = Date()
    var dateGranted: Date? = null
}