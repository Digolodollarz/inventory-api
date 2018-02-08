package tech.diggle.labmanapi.component

import org.hibernate.validator.constraints.NotBlank
import tech.diggle.labmanapi.manufacturer.Manufacturer
import tech.diggle.labmanapi.part.Part
import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity(name = "Component")
class Component {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    val id: Long = 0

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "part_id")
    @NotNull
    var part: Part = Part()

    @NotBlank
    var title: String = ""
    var description: String = ""



    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "manufacturer_id")
    @NotNull
    var manufacturer: Manufacturer? = null
    var consumable: Boolean = false
    var limits: Int = 0
    var stock: Int = 0
    var imageUrl: String = "/assets/img/F80-M3-1800x1200.jpg"

}