package com.drivingschool.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uuid;
    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private LocalDate lastLoginDate;
    private LocalDate lastLoginDateDisplay;
    private LocalDate createDate;
    private String[] roles;
    private String[] authorities;
    private boolean isEnabled;
    private boolean isNonLocked;
}
