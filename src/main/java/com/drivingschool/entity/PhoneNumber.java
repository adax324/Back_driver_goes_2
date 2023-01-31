package com.drivingschool.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;

@Entity
@Table(name = "phone_number")
@NoArgsConstructor
@Getter
@Setter
public class PhoneNumber extends AbstractEntity<Long> {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(mappedBy = "phoneNumber")
    private Student student;
    private String area, exchange, subscriber;
}
