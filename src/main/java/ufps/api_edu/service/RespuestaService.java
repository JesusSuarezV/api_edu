package ufps.api_edu.service;

import ufps.api_edu.entity.Pregunta;
import ufps.api_edu.entity.Respuesta;

import java.util.List;

public interface RespuestaService {
    public Respuesta obtenerRespuesta(int id);

    public List<Respuesta> obtenerRespuestas(Pregunta pregunta);

    public boolean guardarRespuesta (Respuesta respuesta);

    public boolean eliminarRespuesta (Respuesta respuesta);

    public boolean establecerCorrecta (Respuesta respuesta);
}
