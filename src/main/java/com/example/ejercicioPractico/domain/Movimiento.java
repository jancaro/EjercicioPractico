package com.example.ejercicioPractico.domain;

import java.util.Date;
import java.math.BigDecimal;
import javax.persistence.*;
import com.example.ejercicioPractico.domain.enums.TipoMovimiento;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Movimiento {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String id;

    @Column
    private Date fecha;

    @Column
    @Enumerated(EnumType.STRING)
    private TipoMovimiento tipoMovimiento;

    @Column
    private BigDecimal valor;

    @Column
    private BigDecimal saldo;

    @ManyToOne
    @JoinColumn(name = "cuenta_id", referencedColumnName = "id")
    private Cuenta cuenta;
}
