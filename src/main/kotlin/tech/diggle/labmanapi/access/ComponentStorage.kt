package tech.diggle.labmanapi.access

import tech.diggle.labmanapi.component.Component
import tech.diggle.labmanapi.storage.Storage
import javax.persistence.*

@Entity
class ComponentStorage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long = 0

    @OneToOne
    var component: Component = Component()

    @ManyToOne
    var storage: Storage = Storage()
}