package com.example.booksbackend.student

import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.LocalDate
import java.time.Month

@Configuration
class StudentConfig {

    @Bean
    fun commandLineRunner(studentRepository: StudentRepository): CommandLineRunner {
        return CommandLineRunner {
            val marium = Student("Marium", "marium@test.com", LocalDate.of(2000, Month.APRIL, 23))
            val john = Student("John", "john@test.com", LocalDate.of(2000, Month.APRIL, 23))

            studentRepository.saveAll(listOf(marium, john))
        }
    }
}