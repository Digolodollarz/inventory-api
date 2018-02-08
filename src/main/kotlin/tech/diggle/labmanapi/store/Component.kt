package tech.diggle.labmanapi.store

import tech.diggle.labmanapi.manufacturer.Manufacturer
import javax.persistence.*

@Entity(name = "Component")
class Component {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    val id: Long = 0

    var partId: Long = 0
    var title: String = ""
    var description: String = ""



    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "manufacturer_id")
    var manufacturer: Manufacturer? = null
    var consumable: Boolean = false
    var limits: Int = 0
    var stock: Int = 0

}