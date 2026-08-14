package com.boos.backend.util;

import com.boos.backend.Model.*;
import com.boos.backend.dto.*;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class MapperConfig {

    @Primary
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();

        mapper.getConfiguration().setSkipNullEnabled(true);

        mapper.createTypeMap(ParteDiario.class, ParteDiarioDTO.class)
                .setPostConverter(ctx -> {
                    ParteDiario src = ctx.getSource();
                    ParteDiarioDTO dest = ctx.getDestination();
                    if (src.getObra() != null) {
                        dest.setIdObra(src.getObra().getIdObra());
                    }
                    return dest;
                });

        return mapper;
    }

    @Bean("parteDiarioMapper")
    public ModelMapper parteDiarioMapper() {
        ModelMapper mapper = new ModelMapper();

        mapper.createTypeMap(ParteDiario.class, ParteDiarioDTO.class)
                .setPostConverter(ctx -> {
                    ParteDiario src = ctx.getSource();
                    ParteDiarioDTO dest = ctx.getDestination();
                    if (src.getObra() != null) {
                        dest.setIdObra(src.getObra().getIdObra());
                    }
                    return dest;
                });

        mapper.createTypeMap(ParteDiarioDTO.class, ParteDiario.class)
                .setPostConverter(ctx -> {
                    ParteDiarioDTO src = ctx.getSource();
                    ParteDiario dest = ctx.getDestination();
                    if (src.getIdObra() != null) {
                        Obra obra = dest.getObra();
                        if (obra == null) {
                            obra = new Obra();
                            dest.setObra(obra);
                        }
                        obra.setIdObra(src.getIdObra());
                    }
                    return dest;
                });

        return mapper;
    }



    @Bean("detalleCostoMapper")
    public ModelMapper detalleCostoMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.createTypeMap(CostoDiario.class, CostoDiarioDTO.class)
                .setPostConverter(ctx -> {
                    CostoDiario src = ctx.getSource();
                    CostoDiarioDTO dest = ctx.getDestination();
                    dest.setCostoTotal(src.getCostoTotal());
                    if (src.getParteDiario() != null) {
                        dest.setIdParteDiario(src.getParteDiario().getIdParteDiario());
                    }
                    return dest;
                });
                
        mapper.createTypeMap(CostoDiarioDTO.class, CostoDiario.class)
                .setPostConverter(ctx -> {
                    CostoDiarioDTO src = ctx.getSource();
                    CostoDiario dest = ctx.getDestination();
                    if (src.getIdParteDiario() != null) {
                        ParteDiario parte = new ParteDiario();
                        parte.setIdParteDiario(src.getIdParteDiario());
                        dest.setParteDiario(parte);
                    }
                    return dest;
                });

        return mapper;
    }
    @Bean("empleadoMapper")
    public ModelMapper empleadoMapper() {
        ModelMapper mapper = new ModelMapper();

        // REGLA 1: De Entidad Empleado hacia EmpleadoResponseDTO (Para listados y consultas GET)
        mapper.createTypeMap(Empleado.class, EmpleadoResponseDTO.class)
                .setPostConverter(ctx -> {
                    EmpleadoResponseDTO dest = ctx.getDestination();
                    Empleado src = ctx.getSource();

                    // Aseguramos manualmente el mapeo de cada campo para que Jackson lo serialice perfectamente
                    dest.setIdEmpleado(src.getIdEmpleado());
                    dest.setDni(src.getDni());
                    dest.setNombres(src.getNombres());
                    dest.setApellidos(src.getApellidos());
                    dest.setCargo(src.getCargo());
                    dest.setTelefono(src.getTelefono());
                    dest.setCorreoElectronico(src.getCorreoElectronico());
                    dest.setDireccion(src.getDireccion());
                    dest.setObservaciones(src.getObservaciones());
                    dest.setEstado(src.isEstado()); // .isEstado() si es primitivo boolean

                    return dest;
                });

        // REGLA 2: De EmpleadoCreateDTO hacia Entidad Empleado (Para inserciones POST y actualizaciones PUT)
        mapper.createTypeMap(EmpleadoCreateDTO.class, Empleado.class)
                .setPostConverter(ctx -> {
                    EmpleadoCreateDTO src = ctx.getSource();
                    Empleado dest = ctx.getDestination();

                    dest.setDni(src.getDni());
                    dest.setNombres(src.getNombres());
                    dest.setApellidos(src.getApellidos());
                    dest.setCargo(src.getCargo());
                    dest.setTelefono(src.getTelefono());
                    dest.setCorreoElectronico(src.getCorreoElectronico());
                    dest.setDireccion(src.getDireccion());
                    dest.setObservaciones(src.getObservaciones());
                    dest.setEstado(src.getEstado());
                    return dest;
                });
        return mapper;
    }
    @Bean("materialMapper")
    public ModelMapper materialMapper() {
        ModelMapper mapper = new ModelMapper();

        mapper.createTypeMap(Material.class, MaterialResponseDTO.class)
                .setPostConverter(ctx -> {
                    Material src = ctx.getSource();
                    MaterialResponseDTO dest = ctx.getDestination();
                    if (src.getObra() != null) {
                        dest.setIdObra(src.getObra().getIdObra());
                    }
                    return dest;
                });
        mapper.createTypeMap(MaterialCreateDTO.class, Material.class)
                .setPostConverter(ctx -> {
                    MaterialCreateDTO src = ctx.getSource();
                    Material dest = ctx.getDestination();

                    if (src.getIdObra() != null) {
                        Obra obra = new Obra();
                        obra.setIdObra(src.getIdObra());
                        dest.setObra(obra);
                    }
                    return dest;
                });
        return mapper;
    }

    @Bean("evidenciaFotoMapper")
    public ModelMapper evidenciaFotoMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.createTypeMap(EvidenciaFoto.class, EvidenciaFotoDTO.class)
                .setPostConverter(ctx -> {
                    EvidenciaFoto src = ctx.getSource();
                    EvidenciaFotoDTO dest = ctx.getDestination();
                    if (src.getParteDiario() != null) {
                        dest.setIdParteDiario(src.getParteDiario().getIdParteDiario());
                    }
                    return dest;
                });

        mapper.createTypeMap(EvidenciaFotoDTO.class, EvidenciaFoto.class)
                .setPostConverter(ctx -> {
                    EvidenciaFotoDTO src = ctx.getSource();
                    EvidenciaFoto dest = ctx.getDestination();
                    if (src.getIdParteDiario() != null) {
                        ParteDiario parte = new ParteDiario();
                        parte.setIdParteDiario(src.getIdParteDiario());
                        dest.setParteDiario(parte);
                    }
                    return dest;
                });
        return mapper;
    }

    @Bean("movimientoMaterialMapper")
    public ModelMapper movimientoMaterialMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.createTypeMap(MovimientoMaterial.class, MovimientoMaterialDTO.class)
                .setPostConverter(ctx -> {
                    MovimientoMaterial src = ctx.getSource();
                    MovimientoMaterialDTO dest = ctx.getDestination();
                    if (src.getParteDiario() != null) {
                        dest.setIdParteDiario(src.getParteDiario().getIdParteDiario());
                    }
                    if (src.getMaterial() != null) {
                        dest.setIdMaterial(src.getMaterial().getIdMaterial());
                    }
                    return dest;
                });

        mapper.createTypeMap(MovimientoMaterialDTO.class, MovimientoMaterial.class)
                .setPostConverter(ctx -> {
                    MovimientoMaterialDTO src = ctx.getSource();
                    MovimientoMaterial dest = ctx.getDestination();
                    if (src.getIdParteDiario() != null) {
                        ParteDiario parte = new ParteDiario();
                        parte.setIdParteDiario(src.getIdParteDiario());
                        dest.setParteDiario(parte);
                    }
                    if (src.getIdMaterial() != null) {
                        Material material = new Material();
                        material.setIdMaterial(src.getIdMaterial());
                        dest.setMaterial(material);
                    }
                    return dest;
                });
        return mapper;
    }

    @Bean("obraMapper")
    public ModelMapper obraMapper() {
        ModelMapper mapper = new ModelMapper();

        mapper.createTypeMap(Obra.class, ObraDTO.class)
                .setPostConverter(ctx -> {
                    Obra src = ctx.getSource();
                    ObraDTO dest = ctx.getDestination();
                    if (src.getCliente() != null) {
                        dest.setIdCliente(src.getCliente().getIdCliente());
                    }
                    return dest;
                });

        mapper.createTypeMap(ObraDTO.class, Obra.class)
                .setPostConverter(ctx -> {
                    ObraDTO src = ctx.getSource();
                    Obra dest = ctx.getDestination();
                    if (src.getIdCliente() != null) {
                        Cliente cliente = dest.getCliente();
                        if (cliente == null) {
                            cliente = new Cliente();
                            dest.setCliente(cliente);
                        }
                        cliente.setIdCliente(src.getIdCliente());
                    }
                    return dest;
                });

        return mapper;
    }

    @Bean("obraArchivoMapper")
    public ModelMapper obraArchivoMapper() {
        ModelMapper mapper = new ModelMapper();

        mapper.createTypeMap(ObraArchivo.class, ObraArchivoDTO.class)
                .setPostConverter(ctx -> {
                    ObraArchivo src = ctx.getSource();
                    ObraArchivoDTO dest = ctx.getDestination();
                    if (src.getObra() != null) {
                        dest.setIdObra(src.getObra().getIdObra());
                    }
                    return dest;
                });

        mapper.createTypeMap(ObraArchivoDTO.class, ObraArchivo.class)
                .setPostConverter(ctx -> {
                    ObraArchivoDTO src = ctx.getSource();
                    ObraArchivo dest = ctx.getDestination();
                    if (src.getIdObra() != null) {
                        Obra obra = dest.getObra();
                        if (obra == null) {
                            obra = new Obra();
                            dest.setObra(obra);
                        }
                        obra.setIdObra(src.getIdObra());
                    }
                    return dest;
                });

        return mapper;
    }
}
