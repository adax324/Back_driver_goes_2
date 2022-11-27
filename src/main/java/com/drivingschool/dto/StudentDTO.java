package com.drivingschool.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
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
    //private Instructor instructor;
}
