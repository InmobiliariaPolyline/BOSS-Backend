package com.boos.backend.Service.Implementation;

import com.boos.backend.Model.Empleado;
import com.boos.backend.Model.Obra;
import com.boos.backend.Repository.IEmpleadosRepository;
import com.boos.backend.Repository.IGenericRepository;
import com.boos.backend.Repository.IObraRepository;
import com.boos.backend.Service.IObraService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ObraService extends GenericService<Obra, Integer> implements IObraService {
    private final IObraRepository repo;

    @Override
    protected IGenericRepository<Obra, Integer> getRepo() {
        return repo;
    }


    private final IEmpleadosRepository empleadoRepo;

    @Override
    public void agregarEmpleado(Integer idObra, Integer idEmpleado) throws Exception {
        Obra obra = repo.findById(idObra)
                .orElseThrow(() -> new Exception("Obra no encontrada"));

        Empleado empleado = empleadoRepo.findById(idEmpleado)
                .orElseThrow(() -> new Exception("Empleado no encontrado"));

        obra.getEmpleados().add(empleado);
        repo.save(obra);
    }
}
