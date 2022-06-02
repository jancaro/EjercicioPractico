package com.example.ejercicioPractico.domain;

import com.example.ejercicioPractico.domain.enums.Gender;

import javax.persistence.*;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.GenericGenerator;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@MappedSuperclass
public class Person {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String id;

    @Column
    private String name;

    @Column
    private int age;

    @Column
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column
    private String identification;

    @Column
    private String direction;

    @Column
    private String phone;
}