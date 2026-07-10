package com.boos.backend.Repository;

import com.boos.backend.Model.Usuario;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface IUsuariosRepository extends IGenericRepository<Usuario, Integer> {
    Usuario findOneByUsername(String username);

    @Transactional
    @Modifying
    @Query("UPDATE Usuario u SET u.password = :password WHERE u.username = :username")
    void changePassword(@Param("username") String username, @Param("password") String newPassword);
    boolean existsByUsername(String username);
}
