package com.example.booksbackend.student

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/students")
class StudentController(
    private val studentService: StudentService
) {

    @GetMapping
    fun getStudents(): List<Student> {
        return studentService.getStudents()
    }

    @PostMapping
    fun registerNewStudent(@RequestBody student: Student) {
        studentService.addNewStudent(student)
    }

    @DeleteMapping(path= ["{studentId}"])
    fun deleteStudent(@PathVariable("studentId") id: Long) {
        studentService.deleteStudent(id)
    }

    @PutMapping(path=["{studentId}"])
    fun updateStudent(@PathVariable("studentId") studentId: Long,
                      @RequestParam(required = false) name: String?,
                      @RequestParam(required = false) email: String?
    ) {
        studentService.updateStudent(studentId, name, email)
    }
}