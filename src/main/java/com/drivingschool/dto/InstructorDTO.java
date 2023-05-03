package com.drivingschool.dto;

import com.drivingschool.entity.Student;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class InstructorDTO {
    private String uuid;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String email;
    private PhoneNumberDTO phoneNumber;
    private Double availableHours;
    private List<StudentDTO> students;

    public void addStudent(StudentDTO student) {
        if (students == null)
            students = new ArrayList<>();
        students.add(student);
        student.addInstructor(this);
    }

    public void removeStudent(StudentDTO student) {
        if (students != null) {
            students.remove(student);
            student.removeInstructor(this);
        }
    }
}

