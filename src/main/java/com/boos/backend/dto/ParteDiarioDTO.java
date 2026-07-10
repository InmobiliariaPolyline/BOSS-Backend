package com.boos.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParteDiarioDTO {
    private Integer idParteDiario;
    @NotNull
    private Integer idObra;
    @NotNull
    private String nombreParte;
    @NotNull
    private LocalDateTime fechaInforme;
    @NotBlank
    private String elaboradoPor;
    @NotNull
    private Double jornadaLaboral;
    @NotNull
    private Double cantidadEjecutada;
    @NotNull
    private String unidadMedida;
    @NotNull
    private String observaciones;
    @NotNull
    private Double rendimientoReal;
    @NotNull
    private Double rendimientoEsperado;
    private List<CostoDiarioDTO> costos;
    private List<EvidenciaFotoDTO> fotos;
    private List<MovimientoMaterialDTO> movimientos;
}
