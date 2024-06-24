package ufps.api_edu.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ufps.api_edu.entity.Calificacion;
import ufps.api_edu.entity.Tema;
import ufps.api_edu.entity.Usuario;
import ufps.api_edu.repository.CalificacionRepository;
import ufps.api_edu.service.CalificacionService;

import java.util.Optional;

@Controller
public class CalificacionServiceImpl implements CalificacionService {
    @Autowired
    CalificacionRepository calificacionRepository;

    @Override
    public Calificacion obtenerCalificacion(Usuario usuario, Tema tema) {
        Optional<Calificacion> calificacionOptional = calificacionRepository.findByUsuarioAndTemaAndEnabledTrue(usuario, tema);
        return calificacionOptional.orElse(null);
    }

    @Override
    public float obtenerPuntaje(Usuario usuario, Tema tema) {
        Optional<Calificacion> calificacionOptional = calificacionRepository.findByUsuarioAndTemaAndEnabledTrue(usuario, tema);
        return calificacionOptional.map(Calificacion::getPuntaje).orElse(0.0f);
    }

    @Override
    public boolean guardarCalificacion(Calificacion calificacion) {
        Optional<Calificacion> calificacionOptional = calificacionRepository.findByUsuarioAndTemaAndEnabledTrue(calificacion.getUsuario(), calificacion.getTema());
        if (calificacionOptional.isEmpty()) {
            calificacionRepository.save(calificacion);
        }
        else if(calificacionOptional.get().getPuntaje()<calificacion.getPuntaje()){
            Calificacion nuevaCalificacion = calificacionOptional.get();
            nuevaCalificacion.setPuntaje(calificacion.getPuntaje());
            calificacionRepository.save(nuevaCalificacion);

        }


        return true;
    }
}
