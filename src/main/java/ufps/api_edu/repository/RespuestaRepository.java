package ufps.api_edu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ufps.api_edu.entity.Pregunta;
import ufps.api_edu.entity.Respuesta;

import java.util.List;
import java.util.Optional;

@Repository
public interface RespuestaRepository extends JpaRepository<Respuesta, Integer> {

    List<Respuesta> findByPreguntaAndEnabledTrueOrderById(Pregunta pregunta);
    Optional<Respuesta> findByPreguntaAndEnabledTrueAndCorrectaTrue(Pregunta pregunta);

}
