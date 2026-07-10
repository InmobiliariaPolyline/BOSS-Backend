package com.boos.backend.Controller;

import com.boos.backend.Model.Material;
import com.boos.backend.Service.IMaterialService;
import com.boos.backend.dto.MaterialCreateDTO;
import com.boos.backend.dto.MaterialResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/material")
public class MaterialController {

    private final IMaterialService service;

    @Qualifier("materialMapper")
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<MaterialResponseDTO>> findAll() throws Exception {
        List<MaterialResponseDTO> list = service.findAll().stream().map(e -> modelMapper.map(e, MaterialResponseDTO.class)).toList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MaterialResponseDTO> findById(@PathVariable Integer id) throws Exception {
        Material obj = service.findById(id);
        return ResponseEntity.ok(modelMapper.map(obj, MaterialResponseDTO.class));
    }

    @PostMapping
    public ResponseEntity<MaterialResponseDTO> save(@Valid @RequestBody MaterialCreateDTO dto) throws Exception {
        Material obj = service.save(modelMapper.map(dto, Material.class));
        MaterialResponseDTO dtoRespuesta = modelMapper.map(obj, MaterialResponseDTO.class);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(obj.getIdMaterial())
                .toUri();

        return ResponseEntity.created(location).body(dtoRespuesta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MaterialResponseDTO> update(@PathVariable Integer id, @Valid @RequestBody MaterialCreateDTO dto) throws Exception {
        Material obj = service.update(modelMapper.map(dto, Material.class), id);
        return ResponseEntity.ok(modelMapper.map(obj, MaterialResponseDTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) throws Exception {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/pageable")
    public ResponseEntity<Page<MaterialResponseDTO>> listPageable(Pageable pageable) throws Exception {
        Page<MaterialResponseDTO> page = service.listPage(pageable)
                .map(e -> modelMapper.map(e, MaterialResponseDTO.class));
        return ResponseEntity.ok(page);
    }

    @GetMapping("/obra/{idObra}/pageable")
    public ResponseEntity<Page<MaterialResponseDTO>> listPageableByObra(@PathVariable Integer idObra, Pageable pageable) throws Exception {
        Page<MaterialResponseDTO> page = service.listPageByObra(idObra, pageable)
                .map(e -> modelMapper.map(e, MaterialResponseDTO.class));
        return ResponseEntity.ok(page);
    }
}

