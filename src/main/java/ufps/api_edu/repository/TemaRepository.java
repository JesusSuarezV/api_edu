package ufps.api_edu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ufps.api_edu.entity.Tema;
import ufps.api_edu.entity.Unidad;

import java.util.List;
@Repository
public interface TemaRepository extends JpaRepository<Tema, Integer> {

    List<Tema> findByUnidadAndEnabledTrueOrderByNombre(Unidad unidad);


}
