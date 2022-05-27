package com.example.ejercicioPractico.domain;

import java.math.BigDecimal;
import java.util.List;
import javax.persistence.*;
import com.example.ejercicioPractico.domain.enums.TipoCuenta;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Cuenta {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String id;

    @Column
    private String numeroCuenta;

    @Column
    @Enumerated(EnumType.STRING)
    private TipoCuenta tipoCuenta;

    @Column
    private BigDecimal saldoInicial;

    @Column
    private Boolean estado;

    @ManyToOne
    @JoinColumn(name = "cliente_id", referencedColumnName = "id")
    private Cliente cliente;

    @OneToMany(mappedBy = "cuenta", cascade = CascadeType.ALL)
    private List<Movimiento> movimientos;
}
