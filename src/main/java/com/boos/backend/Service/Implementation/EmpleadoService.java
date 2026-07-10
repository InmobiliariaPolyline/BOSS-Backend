package com.boos.backend.Service.Implementation;

import com.boos.backend.Model.Empleado;
import com.boos.backend.Model.Obra;
import com.boos.backend.Repository.IEmpleadosRepository;
import com.boos.backend.Repository.IGenericRepository;
import com.boos.backend.Service.IEmpleadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class EmpleadoService extends GenericService<Empleado, Integer> implements IEmpleadoService {
    private final IEmpleadosRepository repo;

    @Override
    protected IGenericRepository<Empleado, Integer> getRepo() { return repo; }

    @Override
    public Empleado update(Empleado empleado, Integer id) throws Exception {
        repo.findById(id).orElseThrow(() -> new Exception("Empleado no encontrado"));

        empleado.setIdEmpleado(id);
        return repo.save(empleado);
    }

    @Override
    public void delete(Integer id) throws Exception {
        Empleado empleado = repo.findById(id)
                .orElseThrow(() -> new Exception("Empleado no encontrado"));
        for (Obra obra : empleado.getObras()) {
            obra.getEmpleados().remove(empleado);
        }
        empleado.getObras().clear();

        repo.delete(empleado);
    }
}
