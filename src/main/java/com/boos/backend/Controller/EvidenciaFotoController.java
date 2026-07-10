package com.boos.backend.Controller;

import com.boos.backend.Model.EvidenciaFoto;
import com.boos.backend.Service.IEvidenciaFotoService;
import com.boos.backend.dto.EvidenciaFotoDTO;
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
@RequestMapping("/evidencia-foto")
public class EvidenciaFotoController {
    private final IEvidenciaFotoService service;
    @Qualifier("evidenciaFotoMapper")
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<EvidenciaFotoDTO>> findAll() throws Exception{
        List<EvidenciaFotoDTO> list = service.findAll()
                .stream()
                .map(e -> modelMapper.map(e, EvidenciaFotoDTO.class))
                .toList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EvidenciaFotoDTO> findById(@PathVariable("id") Integer id) throws Exception{
        EvidenciaFoto obj = service.findById(id);
        return ResponseEntity.ok(modelMapper.map(obj, EvidenciaFotoDTO.class));
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody EvidenciaFotoDTO dto) throws Exception{
        EvidenciaFoto obj = service.save(modelMapper.map(dto, EvidenciaFoto.class));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdFoto()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<EvidenciaFotoDTO> update(@PathVariable("id") Integer id, @RequestBody EvidenciaFotoDTO dto) throws Exception{
        EvidenciaFoto obj = service.update(modelMapper.map(dto, EvidenciaFoto.class), id);
        return ResponseEntity.ok(modelMapper.map(obj, EvidenciaFotoDTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) throws Exception{
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
