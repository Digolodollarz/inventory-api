package tech.diggle.inventory.api.notifications

import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long = 0
    var subject: String = ""
    var body: String = ""
    var userId: Long = 0
    var senderId: Long? = null
    var dateCreated: Date = Date()
    var dateViewed: Date? = null
    var action: String? = null
    var url: String? = null
}