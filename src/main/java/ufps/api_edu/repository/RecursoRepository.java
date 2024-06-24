package ufps.api_edu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ufps.api_edu.entity.Recurso;
import ufps.api_edu.entity.Tema;

import java.util.List;

@Repository
public interface RecursoRepository extends JpaRepository<Recurso, Integer> {
    List<Recurso> findByTemaAndEnabledTrueOrderById(Tema tema);

}
