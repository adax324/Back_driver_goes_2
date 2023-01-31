package com.drivingschool.entity;



import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Department extends AbstractEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String departmentCode;
    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;
//    @OneToMany(mappedBy = "department")
//    private List<Quest> quests;
}
