package com.boos.backend.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CajaChicaDTO {
    private Integer idCajaChica;

    @NotNull(message = "La obra es obligatoria")
    private Integer idObra;

    @NotNull(message = "El monto inicial es obligatorio")
    @PositiveOrZero(message = "El monto inicial no puede ser negativo")
    @Digits(integer = 10, fraction = 2, message = "El monto inicial debe tener máximo 2 decimales")
    private Double montoInicial;

    @NotNull(message = "El saldo actual es obligatorio")
    @PositiveOrZero(message = "El saldo actual no puede ser negativo")
    @Digits(integer = 10, fraction = 2, message = "El saldo actual debe tener máximo 2 decimales")
    private Double saldoActual;
}
