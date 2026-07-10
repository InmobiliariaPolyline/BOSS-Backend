package com.boos.backend.Service.Implementation;

import com.boos.backend.Model.Usuario;
import com.boos.backend.Repository.IUsuariosRepository;
import com.boos.backend.Repository.IGenericRepository;
import com.boos.backend.Service.IUsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService extends GenericService<Usuario, Integer> implements IUsuarioService {
    private final IUsuariosRepository repo;
    private final PasswordEncoder bcrypt;

    @Override
    protected IGenericRepository<Usuario, Integer> getRepo() {
        return repo;
    }

    @Override
    public Usuario save(Usuario usuario) throws Exception {
        if (usuario.getPassword() != null) {
            usuario.setPassword(bcrypt.encode(usuario.getPassword()));
        }
        return super.save(usuario);
    }

    @Override
    public Usuario update(Usuario usuario, Integer id) throws Exception {
        Usuario existing = repo.findById(id).orElse(null);
        if (existing != null) {
            // Si la contraseña no es nula y no empieza con el prefijo de bcrypt "$2a$", la encriptamos.
            if (usuario.getPassword() != null && !usuario.getPassword().startsWith("$2a$")) {
                usuario.setPassword(bcrypt.encode(usuario.getPassword()));
            } else if (usuario.getPassword() == null) {
                usuario.setPassword(existing.getPassword());
            }
        } else if (usuario.getPassword() != null) {
            usuario.setPassword(bcrypt.encode(usuario.getPassword()));
        }
        return super.update(usuario, id);
    }

    @Override
    public Usuario findOneByUsername(String username) {
        return repo.findOneByUsername(username);
    }

    @Override
    public void changePassword(String username, String newPassword) {
        repo.changePassword(username, bcrypt.encode(newPassword));
    }
}
