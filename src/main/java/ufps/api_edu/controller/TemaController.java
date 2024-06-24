package ufps.api_edu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ufps.api_edu.entity.Tema;
import ufps.api_edu.entity.Unidad;
import ufps.api_edu.service.*;

@Controller
@RequestMapping("/unidades/{idUnidad}/temas")
public class TemaController {

    @Autowired
    UnidadService unidadService;

    @Autowired
    TemaService temaService;

    @Autowired
    CalificacionService calificacionService;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    SesionService sesionService;
    @GetMapping
    public String verTemas(@PathVariable int idUnidad, Model model) {
        Unidad unidad = unidadService.obtenerUnidad(idUnidad);
        model.addAttribute("unidad", unidad);
        model.addAttribute("temas", temaService.obtenerTemas(unidad));
        model.addAttribute("usuario", usuarioService.obtenerUsuarioPorUsername(sesionService.getUsernameFromSession()));
        model.addAttribute("service", calificacionService);

        return "temas/verTemas";
    }

    @GetMapping("/crear_tema")
    public String crearTema(@PathVariable int idUnidad, Model model) {
        Unidad unidad = unidadService.obtenerUnidad(idUnidad);
        model.addAttribute("unidad", unidad);
        return "temas/crearTema";
    }

    @PostMapping("/guardar_tema")
    public String guardarTema(Tema tema, @PathVariable int idUnidad) {
        tema.setUnidad(unidadService.obtenerUnidad(idUnidad));
        if (temaService.guardarTema(tema)) return "redirect:/unidades/{idUnidad}/temas?exito";
        else return "redirect:/unidades/{idUnidad}/temas?error";
    }

    @GetMapping("/{idTema}/editar_tema")
    public String editarTema(@PathVariable int idTema, Model model) {
        Tema tema = temaService.obtenerTema(idTema);

        if (!tema.isEnabled()) return "redirect:/unidades/{idUnidad}/temas?error";

        model.addAttribute("tema", tema);
        return "temas/editarTema";
    }

    @PostMapping("/{idTema}/actualizar_tema")
    public String actualizarTema(@PathVariable int idTema, @RequestParam String nombre, @RequestParam String descripcion, Model model) {
        Tema tema = temaService.obtenerTema(idTema);
        tema.setNombre(nombre);
        tema.setDescripcion(descripcion);
        if (temaService.guardarTema(tema)) return "redirect:/unidades/{idUnidad}/temas?exito";
        else return "redirect:/unidades/{idUnidad}/temas?error";
    }

    @GetMapping("/{idTema}/eliminar_tema")
    public String eliminarTema(@PathVariable int idTema, @PathVariable int idUnidad) {
        if (temaService.eliminarTema(temaService.obtenerTema(idTema))) return "redirect:/unidades/{idUnidad}/temas?exito";
        else return "redirect:/unidades/{idUnidad}/temas?error";
    }


}
