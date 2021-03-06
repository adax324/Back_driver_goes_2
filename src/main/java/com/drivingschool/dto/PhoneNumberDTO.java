package com.drivingschool.dto;

import com.drivingschool.entities.Student;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PhoneNumberDTO {
    private Long id;
    private StudentDTO student;
    String area, exchange, subscriber;
    @JsonIgnore
    public StudentDTO getStudent() {
        return student;
    }
}
