package com.boos.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDTO {
    private Integer idCliente;

    @NotBlank(message = "El nombre completo es obligatorio")
    @Size(min = 2, max = 150, message = "El nombre completo debe tener entre 2 y 150 caracteres")
    private String nombreCompleto;

    @NotBlank(message = "El RUC o DNI es obligatorio")
    @Pattern(
            regexp = "^(\\d{8}|\\d{11})$",
            message = "El RUC o DNI debe tener 8 u 11 dígitos"
    )

    private String rucDNI;

    @Size(max = 150, message = "La razón social no debe exceder los 150 caracteres")
    private String razonSocial;

    @Size(max = 255, message = "La dirección no debe exceder los 255 caracteres")
    private String direccion;

    @Size(max = 100, message = "El nombre de contacto no debe exceder los 100 caracteres")
    private String nombreContacto;

    @Digits(integer = 9, fraction = 0, message = "El teléfono debe tener hasta 9 dígitos")
    private Integer telefono;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El correo electrónico no es válido")
    @Size(max = 100, message = "El email no debe exceder los 100 caracteres")
    private String email;
}
