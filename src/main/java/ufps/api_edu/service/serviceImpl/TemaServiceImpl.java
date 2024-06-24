package ufps.api_edu.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ufps.api_edu.entity.Tema;
import ufps.api_edu.entity.Unidad;
import ufps.api_edu.repository.TemaRepository;
import ufps.api_edu.service.TemaService;

import java.util.List;
@Controller
public class TemaServiceImpl implements TemaService {

    @Autowired
    TemaRepository temaRepository;

    @Override
    public List<Tema> obtenerTemas(Unidad unidad) {

        List<Tema> temas = temaRepository.findByUnidadAndEnabledTrueOrderByNombre(unidad);

        return temas;
    }

    @Override
    public Tema obtenerTema(int id) {
        Tema tema = temaRepository.getReferenceById(id);
        return tema;
    }

    @Override
    public boolean guardarTema(Tema tema) {
        tema.setEnabled(true);
        temaRepository.save(tema);
        return true;
    }

    @Override
    public boolean eliminarTema(Tema tema) {
        tema.setEnabled(false);
        temaRepository.save(tema);

        return true;
    }
}
