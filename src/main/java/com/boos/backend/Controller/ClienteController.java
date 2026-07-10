package com.boos.backend.Controller;

import com.boos.backend.Model.Cliente;
import com.boos.backend.Service.IClienteService;
import com.boos.backend.dto.ClienteDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/cliente")

public class ClienteController {
    private final IClienteService service;
    private final ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> findAll() throws Exception{
        List<ClienteDTO> list = service.findAll().stream().map(c -> mapper.map(c, ClienteDTO.class)).toList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> findById(@PathVariable("id") Integer id) throws Exception{
        Cliente obj = service.findById(id);
        return ResponseEntity.ok(mapper.map(obj, ClienteDTO.class));
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> save(@Valid @RequestBody ClienteDTO dto) throws Exception{
        Cliente cliente = mapper.map(dto, Cliente.class);
        Cliente obj = service.save(cliente);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdCliente()).toUri();
        return ResponseEntity.created(location).body(mapper.map(obj, ClienteDTO.class));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> update(@PathVariable("id") Integer id, @Valid @RequestBody ClienteDTO dto) throws Exception{
        Cliente cliente = mapper.map(dto, Cliente.class);
        Cliente obj = service.update(cliente, id);
        return ResponseEntity.ok(mapper.map(obj, ClienteDTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) throws Exception{
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
