package tech.diggle.labmanapi.requests

import tech.diggle.labmanapi.component.Component
import tech.diggle.labmanapi.student.Student
import java.util.*
import javax.persistence.*

@Entity
class ComponentRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long = 0
    var status: RequestStatus = RequestStatus.OPEN

    @ManyToOne
    @JoinColumn(name = "student_id")
    var student: Student = Student()

    @ManyToOne
    @JoinColumn(name = "component_id")
    var component: Component = Component()

    var dateCreated: Date = Date()
    var dateUpdated: Date? = null
}

