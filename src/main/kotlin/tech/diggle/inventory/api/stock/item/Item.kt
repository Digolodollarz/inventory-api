package tech.diggle.inventory.api.stock.item

import org.hibernate.validator.constraints.NotBlank
import tech.diggle.inventory.api.stock.component.Component
import tech.diggle.inventory.api.stock.manufacturer.Manufacturer
import tech.diggle.inventory.api.stock.part.Part
import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
class Item {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "part_id")
    var part: Part? = null

    @NotBlank
    var title: String = ""
    @NotBlank
    var description: String = ""

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "manufacturer_id")
    var manufacturer: Manufacturer? = null

    var consumable: Boolean = false
    var limits: Int? = null
    var stock: Int = 0
    var imageUrl: String? = null
    var available: Long = 0
    var borrowed: Long = 0
    var lost: Long = 0
    var destroyed: Long = 0
    var reserved: Long = 0
    var userLimits: Long = 0
    var limited: Boolean = false
    var restricted: Boolean = false

}