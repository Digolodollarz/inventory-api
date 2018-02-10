package tech.diggle.labmanapi.student

import org.hibernate.validator.constraints.NotBlank
import tech.diggle.labmanapi.security.model.User
import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 0

    @NotNull
    @NotBlank
    @Column(name = "student_id", unique = true)
    var studentId: String = ""

    @Column(name = "barcode")
    var barcode: String = ""

    @Column(name = "is_registered")
    var isRegistered: Boolean = true

    @Temporal(TemporalType.TIMESTAMP)
    var dateRegistered: Date = Date()

    @Temporal(TemporalType.TIMESTAMP)
    var dateGraduated: Date? = null

    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    var user: User = User()

}