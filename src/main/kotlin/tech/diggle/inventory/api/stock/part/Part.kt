package tech.diggle.inventory.api.stock.part

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Part {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 0

    var number: String = ""
    var title: String = ""
    var description: String? = null
}