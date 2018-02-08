package tech.diggle.labmanapi.manufacturer

import javax.persistence.*

@Entity(name = "manufacturer")
class Manufacturer {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 0
    var title: String = ""
    var description: String = ""
}