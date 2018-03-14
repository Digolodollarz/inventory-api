package tech.diggle.labmanapi.security.user


import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import tech.diggle.labmanapi.security.model.Authority
import java.util.Date
import javax.persistence.*

import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Entity
@Table(name = "USER")
class User {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
//    @SequenceGenerator(name = "user_seq", sequenceName = "user_seq", allocationSize = 1)
    var id: Long? = null

    @Column(name = "USERNAME", length = 50, unique = true)
    @NotNull
    @Size(min = 4, max = 50)
    var username: String? = null

    @Column(name = "PASSWORD", length = 100)
    @NotNull
    @Size(min = 4, max = 100)
    var password: String? = null

    @Column(name = "FIRSTNAME", length = 50)
    @NotNull
    @Size(min = 2, max = 50)
    var firstname: String? = null

    @Column(name = "LASTNAME", length = 50)
    @NotNull
    @Size(min = 2, max = 50)
    var lastname: String? = null

    @Column(name = "EMAIL", length = 50)
    @NotNull
    @Size(min = 4, max = 50)
    var email: String? = null

    @Column(name = "ENABLED")
    @NotNull
    var enabled: Boolean? = null

    @Column(name = "LASTPASSWORDRESETDATE")
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    var lastPasswordResetDate: Date? = null

    @JsonIgnoreProperties("users")
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "USER_AUTHORITY", joinColumns = arrayOf(JoinColumn(name = "USER_ID", referencedColumnName = "ID")), inverseJoinColumns = arrayOf(JoinColumn(name = "AUTHORITY_ID", referencedColumnName = "ID")))
    var authorities: List<Authority>? = null
}