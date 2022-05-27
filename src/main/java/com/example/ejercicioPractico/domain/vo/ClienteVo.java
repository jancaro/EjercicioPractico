package com.example.ejercicioPractico.domain.vo;

import com.example.ejercicioPractico.domain.enums.Genero;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ClienteVo {
    String nombre;
    int edad;
    Genero genero;
    String identificacion;
    String direccion;
    String telefono;
    String contrasena;
    Boolean estado;
}
