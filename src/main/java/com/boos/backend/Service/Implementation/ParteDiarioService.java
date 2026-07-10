package com.boos.backend.Service.Implementation;

import com.boos.backend.Model.ParteDiario;
import com.boos.backend.Repository.IParteDiarioRepository;
import com.boos.backend.Repository.IGenericRepository;
import com.boos.backend.Service.IParteDiarioService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ParteDiarioService extends GenericService<ParteDiario, Integer> implements IParteDiarioService {

    private final IParteDiarioRepository repo;

    @Override
    protected IGenericRepository<ParteDiario, Integer> getRepo() {
        return repo;
    }

    @Override
    @Transactional
    public ParteDiario save(ParteDiario parteDiario) throws Exception {
        if (parteDiario.getCostos() != null) {
            parteDiario.getCostos().forEach(c -> c.setParteDiario(parteDiario));
        }
        if (parteDiario.getFotos() != null) {
            parteDiario.getFotos().forEach(f -> f.setParteDiario(parteDiario));
        }
        if (parteDiario.getMovimientos() != null) {
            parteDiario.getMovimientos().forEach(m -> m.setParteDiario(parteDiario));
        }
        return repo.save(parteDiario);
    }
}
