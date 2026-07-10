package com.boos.backend.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GastoCajaDTO {
    private Integer idGastoCaja;

    @NotNull(message = "La caja chica es obligatoria")
    private Integer idCajaChica;

    @NotBlank(message = "La categoría del gasto es obligatoria")
    @Size(max = 100, message = "La categoría del gasto no debe exceder los 100 caracteres")
    private String categoriaGasto;

    @NotNull(message = "El monto es obligatorio")
    @Positive(message = "El monto debe ser mayor a 0")
    @Digits(integer = 10, fraction = 2, message = "El monto debe tener máximo 2 decimales")
    private Double monto;

    @NotNull(message = "La fecha del gasto es obligatoria")
    @PastOrPresent(message = "La fecha del gasto no puede ser futura")
    private LocalDateTime fechaGasto;

    @NotBlank(message = "El concepto es obligatorio")
    @Size(max = 255, message = "El concepto no debe exceder los 255 caracteres")
    private String concepto;
}
