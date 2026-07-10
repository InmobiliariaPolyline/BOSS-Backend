package com.boos.backend.Service.Implementation;

import com.boos.backend.Model.Donacion;
import com.boos.backend.Repository.IDonacionRepository;
import com.boos.backend.Repository.IGenericRepository;
import com.boos.backend.Service.IDonacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DonacionService extends GenericService<Donacion, Integer> implements IDonacionService {
    private final IDonacionRepository repo;

    @Override
    protected IGenericRepository<Donacion, Integer> getRepo() {return repo;}
}
