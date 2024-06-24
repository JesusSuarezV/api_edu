package ufps.api_edu.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ufps.api_edu.entity.Rol;
import ufps.api_edu.entity.Usuario;
import ufps.api_edu.repository.UsuarioRepository;
import ufps.api_edu.service.UsuarioService;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public boolean guardarUsuario(Usuario usuario) {
        if (usuarioRepository.getUsuarioByUsername(usuario.getUsername()) == null) {


            usuario.setRol(new Rol(03, "ESTUDIANTE", true));
            String contraseñaC = passwordEncoder.encode(usuario.getPassword());
            usuario.setPassword(contraseñaC);
            usuario.setEnabled(true);
            usuarioRepository.save(usuario);
            return true;
        }
        return false;
    }

    @Override
    public int obtenerId(String correo) {
        return usuarioRepository.getUsuarioByUsername(correo).getId();
    }

    @Override
    public Usuario obtenerUsuarioPorUsername(String username) {
        return usuarioRepository.getUsuarioByUsername(username);
    }

    @Override
    public List<Usuario> obtenerUsuarios() {
        return usuarioRepository.findUsuariosNoSadmin();
    }

    @Override
    public Usuario obtenerUsuario(int id) {
        return usuarioRepository.getReferenceById(id);
    }

    @Override
    public boolean bloquearUsuario(Usuario usuario) {
        usuario.setEnabled(false);
        usuarioRepository.save(usuario);
        return true;
    }

    @Override
    public boolean actualizarUsuario(Usuario usuario) {
        usuarioRepository.save(usuario);
        return true;
    }
}
