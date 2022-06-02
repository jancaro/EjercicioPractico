package com.example.ejercicioPractico.domain;

import java.math.BigDecimal;
import java.util.List;
import javax.persistence.*;
import com.example.ejercicioPractico.domain.enums.AccountType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String id;

    @Column
    private String accountNumber;

    @Column
    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @Column
    private BigDecimal initialBalance;

    @Column
    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private Client client;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<Movement> movements;
}
