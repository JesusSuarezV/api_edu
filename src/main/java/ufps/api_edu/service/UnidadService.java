package ufps.api_edu.service;

import ufps.api_edu.entity.Unidad;

import java.util.List;

public interface UnidadService {

    public List<Unidad> obtenerUnidades();

    public Unidad obtenerUnidad(int id);

    public boolean guardarUnidad(Unidad unidad);

    public boolean eliminarUnidad(Unidad unidad);

}
