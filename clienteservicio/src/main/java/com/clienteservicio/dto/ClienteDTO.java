package com.clienteservicio.dto;

public class ClienteDTO {
    private Long clienteId;
    private String nombre;
    private String genero;
    private int edad;
    private String identificacion;
    private String direccion;
    private String telefono;
    private String contraseña;
    private boolean estado;

    public ClienteDTO() {}

    public ClienteDTO(Long clienteId, String nombre, String genero, int edad, String identificacion,
                      String direccion, String telefono, String contraseña, boolean estado) {
        this.clienteId = clienteId;
        this.nombre = nombre;
        this.genero = genero;
        this.edad = edad;
        this.identificacion = identificacion;
        this.direccion = direccion;
        this.telefono = telefono;
        this.contraseña = contraseña;
        this.estado = estado;
    }

}
