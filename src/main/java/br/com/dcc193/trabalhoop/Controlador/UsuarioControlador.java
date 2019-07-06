package br.com.dcc193.trabalhoop.Controlador;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.dcc193.trabalhoop.Modelo.Usuario;
import br.com.dcc193.trabalhoop.Repositorio.UsuarioRepositorio;


/**
 * usuarioControlador
 */

 @Controller
 @RequestMapping("/usuario")
public class UsuarioControlador {

    @Autowired
    private UsuarioRepositorio repositorio;

    @RequestMapping({ "", "/", "/index.html" })
    public ModelAndView atividadeIndex() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("usuario-index.html");
        return mv;
    }

    @GetMapping("/listar.html")
    public ModelAndView listar() {
        List<Usuario> usuarios = repositorio.findAll();
        ModelAndView mv = new ModelAndView();
        mv.setViewName("lista-usuario.html");
        mv.addObject("usuario", usuarios);
        return mv;

    }

    @PostMapping("/nova.html")
    public ModelAndView criar(@Valid Usuario usuario, BindingResult binding) {
        ModelAndView mv = new ModelAndView();
        if (binding.hasErrors()) {
            mv.setViewName("usuario-adicionar.html");
            mv.addObject("usuario", usuario);
            return mv;
        }

        repositorio.save(usuario);
        mv.setViewName("redirect:/login.html");
        return mv;
    }

    @GetMapping("/nova.html")
    public ModelAndView criar() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("usuario-adicionar.html");
        mv.addObject("usuario", new Usuario("nomeCompleto","setor","codigoVinculo","telefone","celular","email"));
        return mv;
    }

    @GetMapping(value = { "/editar{id}" })
    public ModelAndView editar(@RequestParam Long id) {
        ModelAndView mv = new ModelAndView();
        Usuario usuario = repositorio.findById(id).get();
        mv.addObject("usuario",usuario);
        mv.setViewName("usuario-form-edit.html");
        return mv;
    }

    @PostMapping("/editar{id}")
    public ModelAndView editar(@PathVariable Long id, @Valid Usuario usuario, BindingResult binding) {
        ModelAndView mv = new ModelAndView();
        if (binding.hasErrors()) {
            mv.setViewName("usuario-form-edit.html");
            mv.addObject("usuario", usuario);
            return mv;
        }
        repositorio.save(usuario);
        mv.setViewName("redirect:listar.html");
        return mv;
    }

    @GetMapping(value = { "/excluir.html" })
    public ModelAndView excluir(@RequestParam Long id) {
        ModelAndView mv = new ModelAndView();
        repositorio.deleteById(id);
        mv.setViewName("redirect:/usuario/listar.html");
        return mv;
    }
    
}