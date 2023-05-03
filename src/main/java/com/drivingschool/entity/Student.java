package com.drivingschool.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "students")
@NoArgsConstructor
@Getter
@Setter
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String uuid;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "birth_date")
    private LocalDate birthDate;
    private String email;
    @OneToOne(cascade = CascadeType.ALL)
    private PhoneNumber phoneNumber;
    @Column(name = "remaining_hours")
    private Double remainingHours;
    @Column(name = "admitted_exam")
    private boolean admittedExam;
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @ManyToMany
    private List<Instructor> instructors;

    public void addInstructor(Instructor instructor) {
        if (instructors == null)
            instructors = new ArrayList<>();
        instructors.add(instructor);
        instructor.addStudent(this);
    }

    public void removeInstructor(Instructor instructor) {
        if (instructors != null) {
            instructors.remove(instructor);
            instructor.removeStudent(this);
        }
    }


}
