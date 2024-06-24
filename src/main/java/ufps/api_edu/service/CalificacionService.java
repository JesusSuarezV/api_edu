package ufps.api_edu.service;

import ufps.api_edu.entity.Calificacion;
import ufps.api_edu.entity.Tema;
import ufps.api_edu.entity.Usuario;

public interface CalificacionService {
    public Calificacion obtenerCalificacion(Usuario usuario, Tema tema);

    public float obtenerPuntaje(Usuario usuario, Tema tema);

    public boolean guardarCalificacion(Calificacion calificacion);
}
