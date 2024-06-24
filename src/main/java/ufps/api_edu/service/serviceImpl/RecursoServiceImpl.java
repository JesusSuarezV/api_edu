package ufps.api_edu.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ufps.api_edu.entity.Recurso;
import ufps.api_edu.entity.Tema;
import ufps.api_edu.repository.RecursoRepository;
import ufps.api_edu.service.RecursoService;

import java.util.List;
@Service
public class RecursoServiceImpl implements RecursoService {

    @Autowired
    RecursoRepository recursoRepository;

    @Override
    public Recurso obtenerRecurso(int id) {
        return recursoRepository.getReferenceById(id);
    }

    @Override
    public List<Recurso> obtenerRecursos(Tema tema) {
        return recursoRepository.findByTemaAndEnabledTrueOrderById(tema);
    }

    @Override
    public boolean guardarRecurso(Recurso recurso) {
        recurso.setEnabled(true);
        recursoRepository.save(recurso);
        return true;
    }

    @Override
    public boolean eliminarRecurso(Recurso recurso) {
        recurso.setEnabled(false);
        recursoRepository.save(recurso);
        return true;
    }
}
