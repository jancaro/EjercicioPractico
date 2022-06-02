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
public class Client extends Person {
    
    @Column(nullable = false)
    private String password;

    @Column
    private Boolean status;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Account> accounts;
}
