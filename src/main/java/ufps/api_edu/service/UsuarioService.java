package ufps.api_edu.service;

import ufps.api_edu.entity.Usuario;

import java.util.List;

public interface UsuarioService {
    public boolean guardarUsuario(Usuario usuario);

    public int obtenerId(String correo);

    Usuario obtenerUsuarioPorUsername(String username);

    List<Usuario> obtenerUsuarios();

    Usuario obtenerUsuario(int id);

    public boolean bloquearUsuario(Usuario usuario);

    public boolean actualizarUsuario(Usuario usuario);


}