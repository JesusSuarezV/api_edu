package ufps.api_edu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ufps.api_edu.entity.Pregunta;
import ufps.api_edu.entity.Tema;
import ufps.api_edu.service.PreguntaService;
import ufps.api_edu.service.TemaService;

import java.util.List;

@Controller
@RequestMapping("/unidades/{idUnidad}/temas/{idTema}/preguntas")
public class PreguntaController {

    @Autowired
    PreguntaService preguntaService;

    @Autowired
    TemaService temaService;

    @GetMapping
    public String verPreguntas(@PathVariable int idTema, Model model) {
        Tema tema = temaService.obtenerTema(idTema);
        List<Pregunta> preguntas = preguntaService.obtenerPreguntas(tema);

        model.addAttribute("tema", tema);
        model.addAttribute("preguntas", preguntas);

        return "preguntas/verPreguntas";

    }

    @GetMapping("/crear_pregunta")
    public String crearPregunta(@PathVariable int idTema, Model model) {
        Tema tema = temaService.obtenerTema(idTema);
        model.addAttribute("tema", tema);
        return "preguntas/crearPregunta";

    }

    @PostMapping("/guardar_pregunta")
    public String guardarPregunta(Pregunta pregunta, @PathVariable int idTema, @PathVariable int idUnidad, Model model) {
        pregunta.setTema(temaService.obtenerTema(idTema));
        if (preguntaService.guardarPregunta(pregunta))
            return "redirect:/unidades/{idUnidad}/temas/{idTema}/preguntas?exito";
        else return "redirect:/unidades/{idUnidad}/temas/{idTema}/preguntas?error";
    }



    @GetMapping("/{idPregunta}/editar_pregunta")
    public String editarPregunta(@PathVariable int idPregunta, Model model) {
        Pregunta pregunta = preguntaService.obtenerPregunta(idPregunta);
        if(!pregunta.isEnabled()) return "redirect:/unidades/{idUnidad}/temas/{idTema}/preguntas?error";
        model.addAttribute("pregunta", pregunta);
        return "preguntas/editarPregunta";

    }

    @PostMapping("/{idPregunta}/actualizar_pregunta")
    public String actualizarPregunta(Pregunta pregunta, @PathVariable int idTema, @PathVariable int idUnidad) {
        if (preguntaService.guardarPregunta(pregunta))
            return "redirect:/unidades/{idUnidad}/temas/{idTema}/preguntas?exito";
        else return "redirect:/unidades/{idUnidad}/temas/{idTema}/preguntas?error";
    }

    @GetMapping("/{idPregunta}/eliminar_pregunta")
    public String eliminarPregunta(@PathVariable int idPregunta, @PathVariable int idTema, @PathVariable int idUnidad)  {
        if (preguntaService.eliminarPregunta(preguntaService.obtenerPregunta(idPregunta)))
            return "redirect:/unidades/{idUnidad}/temas/{idTema}/preguntas?exito";
        else return "redirect:/unidades/{idUnidad}/temas/{idTema}/preguntas?error";
    }

}
