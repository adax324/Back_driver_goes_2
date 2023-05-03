package com.drivingschool.dto;

import com.drivingschool.entity.Instructor;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PhoneNumberDTO {
    private Long id;
    private StudentDTO student;
    private InstructorDTO instructor;
    String area, exchange, subscriber;
    @JsonIgnore
    public StudentDTO getStudent() {
        return student;
    }
    @JsonIgnore
    public InstructorDTO getInstructor() {
        return instructor;
    }
}
