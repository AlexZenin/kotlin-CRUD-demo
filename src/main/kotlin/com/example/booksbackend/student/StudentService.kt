package com.example.booksbackend.student

import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class StudentService (
    private val studentRepository: StudentRepository
) {
    fun getStudents(): List<Student> {
        return studentRepository.findAll()
    }

    fun addNewStudent(student: Student) {
        val studentOptional = if (student.email == null) null else studentRepository.findStudentByEmail(student.email!!)
        if (studentOptional !== null) {
            throw IllegalStateException("Email taken")
        }
        studentRepository.save(student)
    }

    fun deleteStudent(id: Long) {
        val exists = studentRepository.existsById(id)
        if (!exists) {
           throw IllegalStateException("Student with id $id does not exist")
        }
        studentRepository.deleteById(id)
    }

    @Transactional
    fun updateStudent(studentId: Long, name: String?, email: String?) {
        val savedStudent = studentRepository.findById(studentId).orElseThrow()

        if (name !== null && name !== savedStudent.name) {
            savedStudent.name = name
        }

        if (email !== null && email !== savedStudent.email) {
            val existingStudentWithEmail = studentRepository.findStudentByEmail(email)
            if (existingStudentWithEmail !== null) {
                throw IllegalStateException("Student with email $email already exists")
            }
            savedStudent.email = email
        }
    }
}