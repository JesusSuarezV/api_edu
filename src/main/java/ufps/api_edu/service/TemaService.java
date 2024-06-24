package ufps.api_edu.service;

import ufps.api_edu.entity.Tema;
import ufps.api_edu.entity.Unidad;

import java.util.List;

public interface TemaService {

    public List<Tema> obtenerTemas(Unidad unidad);

    public Tema obtenerTema(int id);

    public boolean guardarTema(Tema tema);

    public boolean eliminarTema(Tema tema);

}
