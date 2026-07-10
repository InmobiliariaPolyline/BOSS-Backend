package com.boos.backend.Controller;

import com.boos.backend.Model.Rol;
import com.boos.backend.Service.IRolService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/rol")
public class RolController {
    private final IRolService service;

    @GetMapping
    public ResponseEntity<List<Rol>> findAll() throws Exception{
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rol> findById(@PathVariable("id") Integer id) throws Exception{
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody Rol rol) throws Exception{
        Rol obj = service.save(rol);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdRol()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Rol> update(@PathVariable("id") Integer id, @RequestBody Rol rol) throws Exception{
        return ResponseEntity.ok(service.update(rol,id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) throws Exception{
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
