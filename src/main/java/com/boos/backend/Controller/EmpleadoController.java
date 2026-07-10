package com.boos.backend.Controller;

import com.boos.backend.Model.Empleado;
import com.boos.backend.Service.IEmpleadoService;
import com.boos.backend.dto.EmpleadoCreateDTO;
import com.boos.backend.dto.EmpleadoResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/empleados")
@RequiredArgsConstructor
public class EmpleadoController {

    private final IEmpleadoService service;

    @Qualifier("empleadoMapper")
    private final ModelMapper empleadoMapper;

    @GetMapping
    public ResponseEntity<List<EmpleadoResponseDTO>> findAll() throws Exception {
        List<EmpleadoResponseDTO> list = service.findAll().stream()
                .map(e -> empleadoMapper.map(e, EmpleadoResponseDTO.class))
                .toList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpleadoResponseDTO> findById(@PathVariable Integer id) throws Exception {
        Empleado obj = service.findById(id);
        return ResponseEntity.ok(empleadoMapper.map(obj, EmpleadoResponseDTO.class));
    }

    @PostMapping
    public ResponseEntity<EmpleadoResponseDTO> save(@Valid @RequestBody EmpleadoCreateDTO dto) throws Exception {
        Empleado empleado = empleadoMapper.map(dto, Empleado.class);
        Empleado obj = service.save(empleado);
        EmpleadoResponseDTO responseDto = empleadoMapper.map(obj, EmpleadoResponseDTO.class);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(obj.getIdEmpleado())
                .toUri();
        return ResponseEntity.created(location).body(responseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmpleadoResponseDTO> update(@PathVariable Integer id, @Valid @RequestBody EmpleadoCreateDTO dto) throws Exception {
        Empleado empleado = empleadoMapper.map(dto, Empleado.class);
        Empleado obj = service.update(empleado, id);
        return ResponseEntity.ok(empleadoMapper.map(obj, EmpleadoResponseDTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) throws Exception {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/hateoas/{id}")
    public EntityModel<EmpleadoResponseDTO> findByHateoas(@PathVariable Integer id) throws Exception {
        Empleado obj = service.findById(id);
        EmpleadoResponseDTO dto = empleadoMapper.map(obj, EmpleadoResponseDTO.class);
        EntityModel<EmpleadoResponseDTO> entityModel = EntityModel.of(dto);

        WebMvcLinkBuilder link1 = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EmpleadoController.class).findById(id));
        WebMvcLinkBuilder link2 = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EmpleadoController.class).findAll());

        // Se cambió mockCreateDto por 'null' para que HATEOAS arme el link sin lanzar fallos
        WebMvcLinkBuilder link3 = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EmpleadoController.class).update(id, null));
        WebMvcLinkBuilder link4 = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EmpleadoController.class).delete(id));

        entityModel.add(link1.withRel("empleado-self-info"));
        entityModel.add(link2.withRel("empleado-all-info"));
        entityModel.add(link3.withRel("empleado-update-info"));
        entityModel.add(link4.withRel("empleado-delete-info"));

        return entityModel;
    }
}

