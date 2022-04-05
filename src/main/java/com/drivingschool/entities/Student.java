package com.drivingschool.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Entity
@Table(name = "students")
@NoArgsConstructor
@Getter
@Setter
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "code", unique = true)
    private String uuid;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "birth_date")
    private LocalDate birthDate;
    private String email;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "remaining_hours")
    private Double remainingHours;
    @Column(name = "admitted_exam")
    private boolean admittedExam;
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
    //private Instructor instructor;


}
