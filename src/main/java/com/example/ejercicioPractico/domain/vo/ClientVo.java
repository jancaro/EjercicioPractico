package com.example.ejercicioPractico.domain.vo;

import com.example.ejercicioPractico.domain.enums.Gender;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ClientVo {
    String name;
    int age;
    Gender gender;
    String identification;
    String direction;
    String phone;
    String password;
    Boolean status;
}
