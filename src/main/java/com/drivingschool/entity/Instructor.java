package com.drivingschool.entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "instructors")
@NoArgsConstructor
@Getter
@Setter
public class Instructor {
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
    @Column(name = "available_hours")
    private Double availableHours;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(name = "instructors_students",
            joinColumns = @JoinColumn(name = "instructor_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private List<Student> students;

    public void addStudent(Student student) {
        if (students == null)
            students = new ArrayList<>();
        students.add(student);
        student.addInstructor(this);
    }

    public void removeStudent(Student student) {
        if (students != null) {
            students.remove(student);
            student.removeInstructor(this);
        }
    }
}
