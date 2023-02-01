package com.drivingschool.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PhoneNumberDTO extends AbstractDTO<Long> {
    private Long id;
    private StudentDTO student;
    String area, exchange, subscriber;
    @JsonIgnore
    public StudentDTO getStudent() {
        return student;
    }
}
