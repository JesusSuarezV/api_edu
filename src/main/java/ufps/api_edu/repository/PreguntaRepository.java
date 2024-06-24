package ufps.api_edu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ufps.api_edu.entity.Pregunta;
import ufps.api_edu.entity.Tema;

import java.util.List;

@Repository
public interface PreguntaRepository extends JpaRepository<Pregunta, Integer> {

    List<Pregunta> findByTemaAndEnabledTrueOrderById(Tema tema);


}
