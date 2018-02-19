package tech.diggle.labmanapi.student

import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("student")
class StudentController(val studentService: StudentService) {
    @GetMapping
    fun getAll() = this.studentService.getAll()

    @GetMapping("/{id}")
    fun get(@PathVariable id: Long) = this.studentService.get(id)

    @GetMapping("/current")
    fun getCurrent(request: HttpServletRequest) = this.studentService.getCurrent(request)

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    fun create(@RequestBody student: Student) = this.studentService.add(student)
}