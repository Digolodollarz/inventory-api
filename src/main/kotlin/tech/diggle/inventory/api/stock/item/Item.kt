package tech.diggle.inventory.api.stock.item

import org.hibernate.validator.constraints.NotBlank
import tech.diggle.inventory.api.stock.component.Component
import tech.diggle.inventory.api.stock.manufacturer.Manufacturer
import tech.diggle.inventory.api.stock.part.Part
import tech.diggle.inventory.api.storage.Storage
import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
class Item {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0

    var partNumber: String? = null

    @NotBlank
    var name: String = ""
    @NotBlank
    var description: String = ""
    var manufacturer: String? = null
    var consumable: Boolean = false
    var availableStock: Int = 0
    var takenStock: Int = 0
    var totalStock: Int = 0
    var lostStock: Int = 0
    var imageUrl: String = "/assets/img/F80-M3-1800x1200.jpg"
    var limited: Boolean = false
    var limits: Int? = null
    var restricted: Boolean = false

    @ManyToOne
    var storage: Storage? = null
}