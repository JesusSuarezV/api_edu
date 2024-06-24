package ufps.api_edu.controller;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ufps.api_edu.entity.Recurso;
import ufps.api_edu.entity.Tema;
import ufps.api_edu.service.RecursoService;
import ufps.api_edu.service.TemaService;

import java.io.FileNotFoundException;
import java.io.IOException;

@Controller
@RequestMapping("/unidades/{idUnidad}/temas/{idTema}/recursos")
public class RecursoController {

    @Autowired
    TemaService temaService;

    @Autowired
    RecursoService recursoService;

    @GetMapping
    public String verRecursos(@PathVariable int idTema, Model model) {
        Tema tema = temaService.obtenerTema(idTema);
        model.addAttribute("tema", tema);
        model.addAttribute("recursos", recursoService.obtenerRecursos(tema));
        return "recursos/verRecursos";

    }

    @PostMapping("/subir_recurso")
    public String subirRecurso(@PathVariable int idTema, @PathVariable int idUnidad, @RequestParam("archivo") MultipartFile archivo) throws IOException {
        try {
            Recurso recurso = new Recurso();
            String nombre = archivo.getOriginalFilename();

            int extensionIndex = nombre.lastIndexOf('.');

            if (extensionIndex > 0) {
                String nombreSinExtension = nombre.substring(0, extensionIndex);
                recurso.setNombre(nombreSinExtension);
            } else {
                recurso.setNombre(nombre);
            }


            recurso.setArchivo(archivo.getBytes());
            recurso.setTipo(FilenameUtils.getExtension(nombre));
            recurso.setEnabled(true);
            recurso.setTema(temaService.obtenerTema(idTema));
            recursoService.guardarRecurso(recurso);
            return "redirect:/unidades/{idUnidad}/temas/{idTema}/recursos?exito";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "redirect:/unidades/{idUnidad}/temas/{idTema}/recursos?error";
        }
    }

    @GetMapping("/{idRecurso}/eliminar_recurso")
    public String eliminarRecursos(@PathVariable int idRecurso, @PathVariable int idTema, @PathVariable int idUnidad) {

        if (recursoService.eliminarRecurso(recursoService.obtenerRecurso(idRecurso)))
            return "redirect:/unidades/{idUnidad}/temas/{idTema}/recursos?exito";
        else return "redirect:/unidades/{idUnidad}/temas/{idTema}/recursos?error";

    }

    @GetMapping("/{idRecurso}/descargar_recurso")
    public ResponseEntity<ByteArrayResource> descargarRecurso(@PathVariable int idRecurso) throws IOException {
        Recurso recurso = recursoService.obtenerRecurso(idRecurso);

        byte[] recursoBytes = recurso.getArchivo();
        ByteArrayResource resource = new ByteArrayResource(recursoBytes);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + recurso.getNombre() + "." + recurso.getTipo());
        headers.add(HttpHeaders.CONTENT_TYPE, "application/vnd.openxmlformats-officedocument.wordprocessingml.document");

        return ResponseEntity.ok()
                .headers(headers)
                .body(resource);
    }
}
