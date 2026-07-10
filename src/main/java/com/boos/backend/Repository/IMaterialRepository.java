package com.boos.backend.Repository;

import com.boos.backend.Model.Material;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IMaterialRepository extends IGenericRepository<Material, Integer> {
    Page<Material> findByObraIdObra(Integer idObra, Pageable pageable);
}
