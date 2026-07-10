package com.boos.backend.Service;

import com.boos.backend.Model.Material;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IMaterialService extends IGenericService<Material, Integer> {
    Page<Material> listPage(Pageable pageable);
    Page<Material> listPageByObra(Integer idObra, Pageable pageable);
}