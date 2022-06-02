package com.example.ejercicioPractico.domain;

import java.util.Date;
import java.math.BigDecimal;
import javax.persistence.*;

import com.example.ejercicioPractico.domain.enums.MovementType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Movement {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String id;

    @Column
    private Date date;

    @Column
    @Enumerated(EnumType.STRING)
    private MovementType movementType;

    @Column
    private BigDecimal amount;

    @Column
    private BigDecimal balance;

    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;
}
