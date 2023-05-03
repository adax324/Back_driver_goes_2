package com.drivingschool.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "phone_number")
@NoArgsConstructor
@Getter
@Setter
public class PhoneNumber {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(mappedBy = "phoneNumber")
    private Student student;
    @OneToOne(mappedBy = "phoneNumber")
    private Instructor instructor;
    private String area, exchange, subscriber;
}
