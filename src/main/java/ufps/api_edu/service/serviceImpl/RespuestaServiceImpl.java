package ufps.api_edu.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ufps.api_edu.entity.Pregunta;
import ufps.api_edu.entity.Respuesta;
import ufps.api_edu.repository.RespuestaRepository;
import ufps.api_edu.service.RespuestaService;

import java.util.List;
import java.util.Optional;

@Service
public class RespuestaServiceImpl implements RespuestaService {
    @Autowired
    RespuestaRepository respuestaRepository;

    @Override
    public Respuesta obtenerRespuesta(int id) {
        return respuestaRepository.getReferenceById(id);
    }

    @Override
    public List<Respuesta> obtenerRespuestas(Pregunta pregunta) {
        return respuestaRepository.findByPreguntaAndEnabledTrueOrderById(pregunta);
    }

    @Override
    public boolean guardarRespuesta(Respuesta respuesta) {
        respuesta.setEnabled(true);
        respuestaRepository.save(respuesta);
        return true;
    }

    @Override
    public boolean eliminarRespuesta(Respuesta respuesta) {
        respuesta.setEnabled(false);
        respuestaRepository.save(respuesta);
        return true;
    }

    @Override
    public boolean establecerCorrecta(Respuesta respuesta) {
        Optional<Respuesta> optionalRespuesta = respuestaRepository.findByPreguntaAndEnabledTrueAndCorrectaTrue(respuesta.getPregunta());
        if (optionalRespuesta.isPresent()) {
            Respuesta oldCorrecta = optionalRespuesta.get();
            oldCorrecta.setCorrecta(false);
            respuestaRepository.save(oldCorrecta);
        }
        respuesta.setCorrecta(true);
        respuestaRepository.save(respuesta);
        return true;
    }
}
