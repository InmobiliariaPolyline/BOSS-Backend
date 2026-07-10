package com.boos.backend.dto;

import lombok.Data;
import jakarta.validation.constraints.*;

@Data
public class EmpleadoCreateDTO {
    @NotNull(message = "El DNI es obligatorio")
    @Digits(integer = 8, fraction = 0, message = "El DNI debe tener 8 dígitos")
    private Integer dni;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    private String nombres;

    @NotBlank(message = "El apellido es obligatorio")
    @Size(min = 2, max = 100, message = "El apellido debe tener entre 2 y 100 caracteres")
    private String apellidos;

    @NotBlank(message = "El cargo es obligatorio")
    private String cargo;

    private Integer telefono;

    @NotBlank(message = "El correo es obligatorio")
    private String correoElectronico;

    private String direccion;
    private String observaciones;

    @NotNull(message = "El estado es obligatorio")
    private Boolean estado;
}
