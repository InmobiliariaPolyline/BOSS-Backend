package com.boos.backend.dto;

import lombok.Data;
@Data
public class EmpleadoResponseDTO  {
    private Integer idEmpleado;
    private Integer dni;
    private String nombres;
    private String apellidos;
    private String cargo;
    private Integer telefono;
    private String correoElectronico;
    private String direccion;
    private String observaciones;
    private Boolean estado;
}
