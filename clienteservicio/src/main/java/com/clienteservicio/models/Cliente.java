package com.clienteservicio.models;

import com.clienteservicio.models.Persona;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "clientes")
@Data
@EqualsAndHashCode(callSuper = true)
public class Cliente extends Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "clienteid")
    private Long clienteId;

    @Column(name = "contraseña", nullable = false)
    private String contraseña;

    @Column(name = "estado", nullable = false)
    private boolean estado;
}
