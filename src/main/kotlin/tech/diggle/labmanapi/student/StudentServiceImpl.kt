package tech.diggle.labmanapi.student

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import tech.diggle.labmanapi.security.JwtTokenUtil
import tech.diggle.labmanapi.security.repository.UserRepository
import tech.diggle.labmanapi.security.service.UserDetailServiceImpl
import javax.servlet.http.HttpServletRequest

@Service
class StudentServiceImpl(val studentRepository: StudentRepository,
                         val userRepository: UserRepository,
                         val userDetailServiceImpl: UserDetailServiceImpl) : StudentService {


    @Value("\${jwt.header}")
    private val tokenHeader: String? = null

    @Autowired
    private val jwtTokenUtil: JwtTokenUtil? = null

    override fun add(student: Student): Student {
        if(student.user.username.isNullOrEmpty()) throw NullPointerException("Username")
        if(student.studentId.isEmpty()) throw NullPointerException("Student Registration Number")

        val username = student.user.username

        val usr = userRepository.findByUsername(username!!)
                ?: userDetailServiceImpl.create(student.user)
                ?: throw NullPointerException("User not registered")
        student.user = usr
        return studentRepository.save(student)
    }

    override fun edit(student: Student): Student {
        return studentRepository.save(student)
    }

    override fun get(id: Long): Student {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    @Suppress("UNCHECKED_CAST")
    override fun getAll(): List<Student> {
        return studentRepository.findAll() as List<Student>
    }

    override fun getByUsername(username: String): Student {
        return studentRepository.findByUserUsername(username)
    }

    override fun getCurrent(request: HttpServletRequest): Student {
        val token = request.getHeader(tokenHeader).substring(7)
        val username = jwtTokenUtil!!.getUsernameFromToken(token)
        return studentRepository.findByUserUsername(username)
    }
}