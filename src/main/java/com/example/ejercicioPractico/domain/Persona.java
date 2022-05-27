package com.example.ejercicioPractico.domain;

import com.example.ejercicioPractico.domain.enums.Genero;
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
public class Persona {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String id;

    @Column
    private String nombre;

    @Column
    private int edad;

    @Column
    @Enumerated(EnumType.STRING)
    private Genero genero;

    @Column
    private String identificacion;

    @Column
    private String direccion;

    @Column
    private String telefono;
}