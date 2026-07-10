package com.boos.backend.Service.Implementation;

import com.boos.backend.Model.Cliente;
import com.boos.backend.Repository.IClienteRepository;
import com.boos.backend.Repository.IGenericRepository;
import com.boos.backend.Service.IClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClienteService extends GenericService<Cliente, Integer> implements IClienteService {
    private final IClienteRepository repo;

    @Override
    protected IGenericRepository<Cliente, Integer> getRepo() {
        return repo;
    }
}
