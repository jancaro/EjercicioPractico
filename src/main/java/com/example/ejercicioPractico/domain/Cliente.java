package com.example.ejercicioPractico.domain;

import java.util.List;
import javax.persistence.*;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Cliente extends Persona {
    
    @Column(nullable = false)
    private String contrasena;

    @Column
    private Boolean estado;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Cuenta> cuentas;
}
