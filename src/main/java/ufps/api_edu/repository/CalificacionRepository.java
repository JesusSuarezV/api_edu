package ufps.api_edu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ufps.api_edu.entity.Calificacion;
import ufps.api_edu.entity.Tema;
import ufps.api_edu.entity.Usuario;

import java.util.Optional;

public interface CalificacionRepository extends JpaRepository<Calificacion, Integer> {
    Optional<Calificacion> findByUsuarioAndTemaAndEnabledTrue(Usuario usuario, Tema tema);
}
