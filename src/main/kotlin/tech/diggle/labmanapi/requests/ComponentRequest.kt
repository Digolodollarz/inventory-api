package tech.diggle.labmanapi.requests

import tech.diggle.labmanapi.component.Component
import tech.diggle.labmanapi.student.Student
import javax.persistence.*

@Entity
data class ComponentRequest(
        @ManyToOne
        @JoinColumn(name = "student_id")
        val student: Student,

        @ManyToOne
        @JoinColumn(name = "component_id")
        val component: Component
) {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long = 0
    var status: RequestStatus = RequestStatus.OPEN
}

