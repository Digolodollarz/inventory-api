package tech.diggle.labmanapi.notifications

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
}