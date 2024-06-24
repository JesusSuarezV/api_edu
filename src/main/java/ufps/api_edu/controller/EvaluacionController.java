package ufps.api_edu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ufps.api_edu.entity.Calificacion;
import ufps.api_edu.entity.Respuesta;
import ufps.api_edu.entity.Tema;
import ufps.api_edu.service.*;

import java.util.List;

@Controller
@RequestMapping("/unidades/{idUnidad}/temas/{idTema}/evaluacion")
public class EvaluacionController {

    @Autowired
    TemaService temaService;

    @Autowired
    PreguntaService preguntaService;

    @Autowired
    RespuestaService respuestaService;

    @Autowired
    CalificacionService calificacionService;

    @Autowired
    SesionService sesionService;

    @Autowired
    UsuarioService usuarioService;

    @GetMapping
    public String realizarEvaluación(@PathVariable int idUnidad, @PathVariable int idTema, Model model) {

        //return "redirect:/unidades/{idUnidad}/temas/{idTema}/preguntas";
        Tema tema = temaService.obtenerTema(idTema);
        model.addAttribute("tema", tema);
        model.addAttribute("preguntas", preguntaService.obtenerPreguntas(tema));
        model.addAttribute("service", respuestaService);
        return "evaluacion/evaluar_tema";

    }

    @PostMapping("/calificar")
    public String calificarEvaluacion(@PathVariable int idUnidad, @PathVariable int idTema, @RequestParam (required = false) List<Integer> respuestasSeleccionadas) {
        int correctas = 0;
        if(respuestasSeleccionadas!=null){
        for (int id : respuestasSeleccionadas) {
            Respuesta respuesta = respuestaService.obtenerRespuesta(id);
            if(respuesta.isCorrecta() && respuesta.isEnabled()) correctas++;
        }
        Calificacion calificacion = new Calificacion();
        calificacion.setUsuario(usuarioService.obtenerUsuarioPorUsername(sesionService.getUsernameFromSession()));
        float puntaje = 100f * ((float) correctas/(float)preguntaService.obtenerPreguntas(temaService.obtenerTema(idTema)).size());
        calificacion.setPuntaje(puntaje);
        calificacion.setTema(temaService.obtenerTema(idTema));
        calificacion.setEnabled(true);

        Calificacion oldCalificacion = calificacionService.obtenerCalificacion(usuarioService.obtenerUsuarioPorUsername(sesionService.getUsernameFromSession()), temaService.obtenerTema(idTema));

        int nuevoPuntaje=0;
        if(oldCalificacion==null || oldCalificacion.getPuntaje()<calificacion.getPuntaje()){
            nuevoPuntaje=1;

        }


        calificacionService.guardarCalificacion(calificacion);

        return "redirect:/unidades/{idUnidad}/temas/{idTema}/evaluacion/resultados?param=" + puntaje + "&param2=" + nuevoPuntaje;}
        else return "redirect:/unidades/{idUnidad}/temas/{idTema}/evaluacion/resultados?param=" + 0.0 + "&param2=" + 0;
    }

    @GetMapping("/resultados")
    public String verResultados(@PathVariable int idUnidad, @PathVariable int idTema, @RequestParam("param") String param, @RequestParam("param2") String param2, Model model){
        model.addAttribute("puntuacion", param);
        boolean nuevoPuntaje = false;
        if(param2.equals(1+"")){
            nuevoPuntaje = true;
        }
        model.addAttribute("nuevoPuntaje", nuevoPuntaje);
        model.addAttribute("tema", temaService.obtenerTema(idTema));
        return "evaluacion/resultado";
    }


}
