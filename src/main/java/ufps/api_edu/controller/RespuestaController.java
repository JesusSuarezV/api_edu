package ufps.api_edu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ufps.api_edu.entity.Pregunta;
import ufps.api_edu.entity.Respuesta;
import ufps.api_edu.service.PreguntaService;
import ufps.api_edu.service.RespuestaService;

@Controller
@RequestMapping("/unidades/{idUnidad}/temas/{idTema}/preguntas/{idPregunta}/respuestas")
public class RespuestaController {

    @Autowired
    PreguntaService preguntaService;

    @Autowired
    RespuestaService respuestaService;

    @GetMapping
    public String verRespuestas(Model model, @PathVariable int idPregunta) {
        Pregunta pregunta = preguntaService.obtenerPregunta(idPregunta);
        model.addAttribute("pregunta", pregunta);
        model.addAttribute("respuestas", respuestaService.obtenerRespuestas(pregunta));
        return "respuestas/verRespuestas";
    }

    @GetMapping("/crear_respuesta")
    public String crearRespuesta(Model model, @PathVariable int idPregunta) {
        model.addAttribute("pregunta", preguntaService.obtenerPregunta(idPregunta));
        return "respuestas/crearRespuesta";
    }

    @PostMapping("/guardar_respuesta")
    public String guardarRespuesta(Respuesta respuesta, @PathVariable int idPregunta, @PathVariable int idTema, @PathVariable int idUnidad) {
        respuesta.setPregunta(preguntaService.obtenerPregunta(idPregunta));
        if (respuestaService.guardarRespuesta(respuesta))
            return "redirect:/unidades/{idUnidad}/temas/{idTema}/preguntas/{idPregunta}/respuestas?exito";
        else return "redirect:/unidades/{idUnidad}/temas/{idTema}/preguntas/{idPregunta}/respuestas?error";

    }

    @GetMapping("/{idRespuesta}/editar_respuesta")
    public String editarRespuesta(Model model, @PathVariable int idRespuesta) {
        model.addAttribute("respuesta", respuestaService.obtenerRespuesta(idRespuesta));
        return "respuestas/editarRespuesta";
    }

    @PostMapping("/{idRespuesta}/actualizar_respuesta")
    public String actualizarRespuesta(Respuesta respuesta, @PathVariable int idPregunta, @PathVariable int idTema, @PathVariable int idUnidad) {
        if (respuestaService.guardarRespuesta(respuesta))
            return "redirect:/unidades/{idUnidad}/temas/{idTema}/preguntas/{idPregunta}/respuestas?exito";
        else return "redirect:/unidades/{idUnidad}/temas/{idTema}/preguntas/{idPregunta}/respuestas?error";

    }

    @GetMapping("/{idRespuesta}/eliminar_respuesta")
    public String eliminarRespuesta(@PathVariable int idRespuesta, @PathVariable int idPregunta, @PathVariable int idTema, @PathVariable int idUnidad) {
        if (respuestaService.eliminarRespuesta(respuestaService.obtenerRespuesta(idRespuesta)))
            return "redirect:/unidades/{idUnidad}/temas/{idTema}/preguntas/{idPregunta}/respuestas?exito";
        else return "redirect:/unidades/{idUnidad}/temas/{idTema}/preguntas/{idPregunta}/respuestas?error";

    }

    @GetMapping("/{idRespuesta}/establecer_correcta")
    public String establecerCorrecta(@PathVariable int idRespuesta, @PathVariable int idPregunta, @PathVariable int idTema, @PathVariable int idUnidad) {
        if (respuestaService.establecerCorrecta(respuestaService.obtenerRespuesta(idRespuesta)))
            return "redirect:/unidades/{idUnidad}/temas/{idTema}/preguntas/{idPregunta}/respuestas?exito";
        else return "redirect:/unidades/{idUnidad}/temas/{idTema}/preguntas/{idPregunta}/respuestas?error";

    }

}
