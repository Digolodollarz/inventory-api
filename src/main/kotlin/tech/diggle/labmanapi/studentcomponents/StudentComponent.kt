package tech.diggle.labmanapi.studentcomponents

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import tech.diggle.labmanapi.component.Component
import javax.persistence.*

@Entity
class StudentComponent{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long = 0

    @ManyToOne
    @JoinColumn(name = "component_id")
    var component: Component = Component()

//    @ForeignKey
    var studentId: Long = 0

    var requestId: Long = 0

    var returned: Boolean = false
}
