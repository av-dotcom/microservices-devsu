package com.cuentaservicio.models;

import com.cuentaservicio.models.Cuenta;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "movimientos")
@Data
public class Movimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="fecha", nullable = false)
    private LocalDateTime fecha;

    @Column(name="tipo_movimiento", nullable = false)
    private String tipoMovimiento;

    @Column(name="valor", nullable = false)
    private Double valor;

    @Column(name="saldo", nullable = false)
    private Double saldo;

    // Relación con Cuenta
    @ManyToOne
    @JoinColumn(name="cuenta_id", nullable = false)
    private Cuenta cuenta;
}
