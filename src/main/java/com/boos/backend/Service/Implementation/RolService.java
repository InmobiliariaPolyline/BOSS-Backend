package com.boos.backend.Service.Implementation;

import com.boos.backend.Model.Rol;
import com.boos.backend.Repository.IRolRepository;
import com.boos.backend.Repository.IGenericRepository;
import com.boos.backend.Service.IRolService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RolService extends GenericService<Rol, Integer> implements IRolService {
    private final IRolRepository repo;

    @Override
    protected IGenericRepository<Rol, Integer> getRepo() {
        return repo;
    }
}
