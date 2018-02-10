package tech.diggle.labmanapi.access

import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToOne

class ComponentStorage{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long = 0


}