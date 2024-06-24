package ufps.api_edu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ufps.api_edu.entity.Rol;
import ufps.api_edu.entity.Usuario;
import ufps.api_edu.service.UsuarioService;

@Controller
public class LoginController {

    @Autowired
    UsuarioService usuarioService;

    @GetMapping("/iniciar_sesion")
    public String cargarLogin(){
        return "login";
    }

    @GetMapping("/registrarse")
    public String cargarRegistro(){
        return "registro";
    }

    @PostMapping("/registro")
    public String registrarUsuario(Usuario usuario){
    if(usuarioService.guardarUsuario(usuario)) return "redirect:/?exitoRegistro";
    else return "redirect:/registrarse?errorRegistro";
    }

}
