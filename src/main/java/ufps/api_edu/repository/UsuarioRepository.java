package ufps.api_edu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ufps.api_edu.entity.Usuario;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    List<Usuario> findByEnabledTrueOrderByUsername();
    Usuario getUsuarioByUsername(String username);

    @Query("SELECT u FROM Usuario u WHERE u.rol.id <> 1 AND u.enabled = true ORDER BY u.username")
    List<Usuario> findUsuariosNoSadmin();
}
