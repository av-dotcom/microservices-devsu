package com.cuentaservicio.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClienteDTO {

    @JsonProperty("clienteId")
    private Long clienteId;

    @JsonProperty("nombre")
    private String nombre;

    @JsonProperty("genero")
    private String genero;

    @JsonProperty("edad")
    private int edad;

    @JsonProperty("identificacion")
    private String identificacion;

    @JsonProperty("direccion")
    private String direccion;

    @JsonProperty("telefono")
    private String telefono;

    @JsonProperty("contraseña")
    private String contraseña;

    @JsonProperty("estado")
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
