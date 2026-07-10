package com.boos.backend.Repository;

import com.boos.backend.Model.Rol;

import java.util.Optional;

public interface IRolRepository extends IGenericRepository<Rol, Integer> {
    Optional<Rol> findByNombreRol(String nombreRol);
}

