package com.boos.backend.dto;

import com.boos.backend.Model.TipoCosto;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CostoDiarioDTO {
    private Integer idCosto;
    
    @NotNull(message = "El id del parte diario es obligatorio")
    private Integer idParteDiario;

    @NotNull(message = "El tipo de costo es obligatorio")
    private TipoCosto tipo;

    @NotBlank(message = "La descripción es obligatoria")
    private String descripcion;

    @NotNull(message = "La cantidad es obligatoria")
    @Positive(message = "La cantidad debe ser mayor a cero")
    private Double cantidad;

    @NotNull(message = "El costo unitario es obligatorio")
    @Digits(integer = 10, fraction = 2)
    private Double costoUnitario;

    @NotNull(message = "El costo total es obligatorio")
    @Digits(integer = 10, fraction = 2)
    private Double costoTotal;

}
