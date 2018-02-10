package tech.diggle.labmanapi.requests

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class ComponentRequest(
        val studentId: Long,
        val componentId: Long
) {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long = 0
    var status: RequestStatus = RequestStatus.OPEN
}

