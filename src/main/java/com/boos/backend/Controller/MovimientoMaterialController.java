package com.boos.backend.Controller;

import com.boos.backend.dto.MovimientoMaterialDTO;
import com.boos.backend.Model.MovimientoMaterial;
import com.boos.backend.Service.IMovimientoMaterialService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/movimiento-material")
public class MovimientoMaterialController {

    private final IMovimientoMaterialService service;
    @Qualifier("movimientoMaterialMapper")
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<MovimientoMaterialDTO>> findAll() throws Exception {
        List<MovimientoMaterialDTO> list = service.findAll()
                .stream()
                .map(e -> modelMapper.map(e, MovimientoMaterialDTO.class))
                .toList();

        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovimientoMaterialDTO> findById(@PathVariable Integer id) throws Exception {
        MovimientoMaterial obj = service.findById(id);
        return ResponseEntity.ok(modelMapper.map(obj, MovimientoMaterialDTO.class));
    }

    @PostMapping
    public ResponseEntity<MovimientoMaterialDTO> save(@RequestBody MovimientoMaterialDTO dto) throws Exception {
        MovimientoMaterial obj = service.save(modelMapper.map(dto, MovimientoMaterial.class));
        MovimientoMaterialDTO dtoRespuesta = modelMapper.map(obj,MovimientoMaterialDTO.class);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(obj.getIdMovimiento())
                .toUri();

        return ResponseEntity.created(location).body(dtoRespuesta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovimientoMaterialDTO> update(@PathVariable Integer id, @RequestBody MovimientoMaterialDTO dto) throws Exception {
        MovimientoMaterial objInput = modelMapper.map(dto, MovimientoMaterial.class);
        MovimientoMaterial objActualizado = service.update(objInput, id);
        return ResponseEntity.ok(modelMapper.map(objActualizado, MovimientoMaterialDTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) throws Exception {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}