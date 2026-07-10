package com.boos.backend.Service.Implementation;

import com.boos.backend.Model.EvidenciaFoto;
import com.boos.backend.Repository.IEvidenciaFotoRepository;
import com.boos.backend.Repository.IGenericRepository;
import com.boos.backend.Service.IEvidenciaFotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EvidenciaFotoService extends GenericService<EvidenciaFoto, Integer> implements IEvidenciaFotoService {

    private final IEvidenciaFotoRepository repo;
    @Override
    protected IGenericRepository<EvidenciaFoto, Integer> getRepo() {
        return repo;
    }
}
