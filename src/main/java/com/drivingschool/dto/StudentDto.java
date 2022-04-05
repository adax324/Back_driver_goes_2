package com.drivingschool.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@NoArgsConstructor
@Getter
@Setter
public class StudentDto {
    private String uuid;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String email;
    private String phoneNumber;
    private Double remainingHours;
    private boolean admittedExam;
    private DepartmentDto department;
    //private Instructor instructor;
}
