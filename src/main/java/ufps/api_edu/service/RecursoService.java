package ufps.api_edu.service;

import ufps.api_edu.entity.Recurso;
import ufps.api_edu.entity.Tema;

import java.util.List;

public interface RecursoService {
    public Recurso obtenerRecurso(int id);
    public List<Recurso> obtenerRecursos(Tema tema);

    public boolean guardarRecurso(Recurso recurso);

    public boolean eliminarRecurso(Recurso recurso);
}
