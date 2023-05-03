package com.drivingschool.dto;

import com.drivingschool.entity.Instructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class StudentDTO {
    private Long id;
    private String uuid;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String email;
    private PhoneNumberDTO phoneNumber;
    private Double remainingHours;
    private boolean admittedExam;
    private DepartmentDTO department;
    private List<InstructorDTO> instructors;

    public void addInstructor(InstructorDTO instructor) {
        if (instructors == null)
            instructors = new ArrayList<>();
        instructors.add(instructor);
        instructor.addStudent(this);
    }

    public void removeInstructor(InstructorDTO instructor) {
        if (instructors != null) {
            instructors.remove(instructor);
            instructor.removeStudent(this);
        }
    }
}
