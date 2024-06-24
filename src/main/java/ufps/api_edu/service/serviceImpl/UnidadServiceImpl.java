package ufps.api_edu.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ufps.api_edu.entity.Unidad;
import ufps.api_edu.repository.UnidadRepository;
import ufps.api_edu.service.UnidadService;

import java.util.List;

@Service
public class UnidadServiceImpl implements UnidadService {

    @Autowired
    UnidadRepository unidadRepository;

    @Override
    public List<Unidad> obtenerUnidades() {
        return unidadRepository.findByEnabledTrueOrderByNombre();
    }

    @Override
    public Unidad obtenerUnidad(int id) {
       return unidadRepository.getReferenceById(id);
    }

    @Override
    public boolean guardarUnidad(Unidad unidad) {

        unidadRepository.save(unidad);

        return true;
    }

    @Override
    public boolean eliminarUnidad(Unidad unidad) {
        unidadRepository.save(unidad);

        return true;
    }
}
