package com.boos.backend.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class DonacionesDTO {
    private Integer idDonacion;

    @NotNull(message = "La obra es obligatoria")
    private Integer idObra;

    @NotBlank(message = "El nombre del donante es obligatorio")
    @Size(min = 2, max = 100, message = "El nombre del donante debe tener entre 2 y 100 caracteres")
    private String nombreDonante;

    @NotBlank(message = "El tipo de donación es obligatorio")
    @Size(max = 50, message = "El tipo de donación no debe exceder los 50 caracteres")
    private String tipoDonacion;

    @NotBlank(message = "La descripción es obligatoria")
    @Size(max = 255, message = "La descripción no debe exceder los 255 caracteres")
    private String descripcion;

    @NotNull(message = "La cantidad donada es obligatoria")
    @Positive(message = "La cantidad donada debe ser mayor a 0")
    private Integer cantidadDonada;

    @NotNull(message = "El precio unitario es obligatorio")
    @Positive(message = "El precio unitario debe ser mayor a 0")
    @Digits(integer = 10, fraction = 2, message = "El precio unitario debe tener máximo 2 decimales")
    private Double precioUnitario;

    @NotNull(message = "El monto total es obligatorio")
    @Positive(message = "El monto total debe ser mayor a 0")
    @Digits(integer = 10, fraction = 2, message = "El monto total debe tener máximo 2 decimales")
    private Double montoTotal;

    @NotNull(message = "La fecha de registro es obligatoria")
    @PastOrPresent(message = "La fecha de registro no puede ser futura")
    private LocalDate fechaRegistro;
}
