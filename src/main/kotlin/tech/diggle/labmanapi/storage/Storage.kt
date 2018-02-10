package tech.diggle.labmanapi.storage

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Storage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id = 0
    var title: String = ""
    var description: String = ""
}
