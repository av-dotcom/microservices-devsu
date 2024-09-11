package com.cuentaservicio.models;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "cuentas")
@Data
public class Cuenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="numero_cuenta", nullable = false, unique = true)
    private String numeroCuenta;

    @Column(name="tipo_cuenta", nullable = false)
    private String tipoCuenta;

    @Column(name="saldo_inicial", nullable = false)
    private Double saldoInicial;

    @Column(name="estado", nullable = false)
    private Boolean estado;

    // Relación con Cliente (asumiendo que tienes un objeto Cliente)
    @Column(name="cliente_id", nullable = false)
    private Long clienteId;
}
