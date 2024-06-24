package ufps.api_edu.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ufps.api_edu.entity.Pregunta;
import ufps.api_edu.entity.Tema;
import ufps.api_edu.repository.PreguntaRepository;
import ufps.api_edu.service.PreguntaService;

import java.util.List;
@Service
public class PreguntaServiceImpl implements PreguntaService {

    @Autowired
    PreguntaRepository preguntaRepository;


    @Override
    public List<Pregunta> obtenerPreguntas(Tema tema) {
        List<Pregunta> preguntas = preguntaRepository.findByTemaAndEnabledTrueOrderById(tema);
        return preguntas;
    }

    @Override
    public Pregunta obtenerPregunta(int id) {
        return preguntaRepository.getReferenceById(id);
    }

    @Override
    public boolean guardarPregunta(Pregunta pregunta) {
        pregunta.setEnabled(true);
        preguntaRepository.save(pregunta);
        return true;
    }

    @Override
    public boolean eliminarPregunta(Pregunta pregunta) {
        pregunta.setEnabled(false);
        preguntaRepository.save(pregunta);
        return true;
    }
}
