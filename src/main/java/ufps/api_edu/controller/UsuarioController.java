package ufps.api_edu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ufps.api_edu.entity.Rol;
import ufps.api_edu.entity.Usuario;
import ufps.api_edu.service.UsuarioService;

import java.util.Arrays;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    UsuarioService usuarioService;

    @GetMapping
    public String verUsuarios(Model model) {
        model.addAttribute("usuarios", usuarioService.obtenerUsuarios());
        return "usuarios/verUsuarios";
    }


    @PostMapping("/guardar_rol")
    public String guardarRol(@RequestParam int id, @RequestParam String role) {
        System.out.println(role);
        Rol rol = new Rol();
        if (role.equals("PROFESOR")) {
            rol.setId(02);
            rol.setNombre("PROFESOR");
        } else {
            rol.setId(03);
            rol.setNombre("ESTUDIANTE");
        }
        Usuario usuario = usuarioService.obtenerUsuario(id);
        usuario.setRol(rol);
        if (usuarioService.actualizarUsuario(usuario)) return "redirect:/usuarios?exito";
        else return "redirect:/usuarios?error";

    }

    @GetMapping("/{id}/editar_rol")
    public String verFormularioDeEditarRol(@PathVariable int id, Model model) {
        Usuario usuario = usuarioService.obtenerUsuario(id);
        if (usuario != null) {
            model.addAttribute("id", id);
            model.addAttribute("roles", Arrays.asList("PROFESOR", "ESTUDIANTE"));
            return "usuarios/editar_rol";
        } else {
            return "redirect:/usuarios?error";
        }
    }

    @GetMapping("/{id}/bloquear_usuario")
    public String bloquearUsuario(@PathVariable int id, Model model) {
        if (usuarioService.bloquearUsuario(usuarioService.obtenerUsuario(id))) {
            return "redirect:/usuarios?exito";
        } else {
            return "redirect:/usuarios?error";
        }
    }

}