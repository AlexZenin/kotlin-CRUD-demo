package com.example.booksbackend.student

import jakarta.persistence.*
import java.time.LocalDate
import java.time.Period

@Entity
@Table
class Student() {

    @Id
    @SequenceGenerator(
        name = "student_sequence",
        sequenceName = "student_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "student_sequence"
    )
    var id: Long? = null
    var name: String? = null
    var email: String? = null
    var dob: LocalDate? = null
    @get:Transient
    val age: Int
        get() = Period.between(dob, LocalDate.now()).years

    // All args constructor but without id
    constructor(name: String, email: String, dob: LocalDate): this() {
        this.name = name
        this.email = email
        this.dob = dob
    }

    // All args constructor
    constructor(id: Long, name: String, email: String, dob: LocalDate): this() {
        this.name = name
        this.email = email
        this.dob = dob

        // TODO: Should set the id to something auto-incrementing
    }


    override fun toString(): String {
        return "Student(id=$id, name=$name, email=$email, dob=$dob, age=$age)"
    }
}