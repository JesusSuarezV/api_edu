package ufps.api_edu.service;

import org.springframework.stereotype.Service;
import ufps.api_edu.entity.Pregunta;
import ufps.api_edu.entity.Tema;

import java.util.List;
@Service
public interface PreguntaService {

    public List<Pregunta> obtenerPreguntas(Tema tema);

    public Pregunta obtenerPregunta(int id);

    public boolean guardarPregunta(Pregunta pregunta);

    public boolean eliminarPregunta(Pregunta pregunta);

}
