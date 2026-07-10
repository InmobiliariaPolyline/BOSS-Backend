package com.boos.backend.Controller;

import com.boos.backend.Model.ParteDiario;
import com.boos.backend.Service.IParteDiarioService;
import com.boos.backend.dto.ParteDiarioDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/parte-diario")
public class ParteDiarioController {
    private final IParteDiarioService service;
    @Qualifier("parteDiarioMapper")
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<ParteDiarioDTO>> findAll() throws Exception {
        List<ParteDiarioDTO> list = service.findAll().stream().map(e -> modelMapper.map(e, ParteDiarioDTO.class)).toList();
        return ResponseEntity.ok(list);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ParteDiarioDTO> findById(@PathVariable("id") Integer id) throws Exception{
        ParteDiario obj = service.findById(id);
        return ResponseEntity.ok(modelMapper.map(obj, ParteDiarioDTO.class));
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody ParteDiarioDTO dto) throws Exception {
        ParteDiario entity = modelMapper.map(dto, ParteDiario.class);
        ParteDiario obj = service.save(entity);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdParteDiario()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ParteDiarioDTO> update(@PathVariable Integer id, @RequestBody ParteDiarioDTO dto) throws Exception {
        ParteDiario obj = service.update(modelMapper.map(dto, ParteDiario.class), id);
        return ResponseEntity.ok(modelMapper.map(obj, ParteDiarioDTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) throws Exception{
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
