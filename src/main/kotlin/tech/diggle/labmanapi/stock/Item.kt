package tech.diggle.labmanapi.stock

import tech.diggle.labmanapi.component.Component
import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long = 0

    @OneToOne
    @JoinColumn(name = "component_id", unique = true)
    @NotNull
    var component: Component = Component()

    var stock: Long = 0
    var available: Long = 0
    var borrowed: Long = 0
    var lost: Long = 0
    var destroyed: Long = 0
    var reserved: Long = 0
    var userLimits: Long = 0
    val isLimited: Boolean = false
    val isRestricted: Boolean = false
}