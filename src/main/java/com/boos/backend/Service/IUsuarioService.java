package com.boos.backend.Service;

import com.boos.backend.Model.Usuario;

public interface IUsuarioService extends IGenericService<Usuario, Integer> {
    Usuario findOneByUsername(String username);
    void changePassword(String username, String password);
}
