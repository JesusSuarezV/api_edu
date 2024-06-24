package ufps.api_edu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ufps.api_edu.entity.Unidad;
import ufps.api_edu.service.UnidadService;

import java.util.List;

@Controller
@RequestMapping("/unidades")
public class UnidadController {

    @Autowired
    UnidadService unidadService;

    @GetMapping
    public String verUnidades(Model model) {
        List<Unidad> unidades = unidadService.obtenerUnidades();

        model.addAttribute("unidades", unidades);


        return "unidades/verUnidades";
    }

    @GetMapping("/crear_unidad")
    public String crearUnidad() {
        return "/unidades/crearUnidad";
    }

    @PostMapping("/guardar_unidad")
    public String guardarUnidad(@RequestParam String nombre, @RequestParam String descripcion) {

        Unidad unidad = new Unidad();

        unidad.setNombre(nombre);
        unidad.setDescripcion(descripcion);
        unidad.setEnabled(true);

        if (unidadService.guardarUnidad(unidad)) {
            return "redirect:/unidades?exito";
        } else return "redirect:/unidades?error";
    }


    @GetMapping("/{idUnidad}/editar_unidad")
    public String editarTema(@PathVariable int idUnidad, Model model) {
        Unidad unidad = unidadService.obtenerUnidad(idUnidad);
        model.addAttribute("unidad", unidad);
        //model.addAttribute("nombre", tema.getNombre());
        //model.addAttribute("descripcion", tema.getDescripcion());
        return "/unidades/editarUnidad";
    }

    @PostMapping("/{idUnidad}/actualizar_unidad")
    public String actualizarUnidad(@PathVariable int idUnidad, @RequestParam String nombre, @RequestParam String descripcion) {
        Unidad unidad = unidadService.obtenerUnidad(idUnidad);
        unidad.setNombre(nombre);
        unidad.setDescripcion(descripcion);

        if (unidadService.guardarUnidad(unidad)) {
            return "redirect:/unidades?exito";
        } else return "redirect:/unidades?error";
    }

    @GetMapping("/{idUnidad}/eliminar_unidad")
    public String eliminarUnidad(@PathVariable int idUnidad) {
        Unidad unidad = unidadService.obtenerUnidad(idUnidad);
        unidad.setEnabled(false);
        if (unidadService.guardarUnidad(unidad)) {
            return "redirect:/unidades?exito";
        } else return "redirect:/unidades?error";
    }

}



