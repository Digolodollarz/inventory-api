package tech.diggle.labmanapi.student

import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("student")
class StudentController(val studentService: StudentService) {
    @GetMapping
    fun getAll() = this.studentService.getAll()

    @GetMapping("/{id}")
    fun get(@PathVariable id: Long) = this.studentService.get(id)

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    fun create(@RequestBody student: Student) = this.studentService.add(student)
}