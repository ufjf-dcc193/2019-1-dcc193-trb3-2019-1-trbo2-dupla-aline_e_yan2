package br.com.dcc193.trabalhoop.Controlador;

import java.util.List;
import java.util.Random;

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

import br.com.dcc193.trabalhoop.Modelo.Atendente;
import br.com.dcc193.trabalhoop.Repositorio.AtendenteRepositorio;



/**
 * AtendenteControlador
 */

 @Controller
 @RequestMapping("/atendente")
public class AtendenteControlador {

    @Autowired
    private AtendenteRepositorio repositorio;

    @RequestMapping({ "", "/", "/index.html" })
    public ModelAndView atividadeIndex() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("atendente-index.html");
        return mv;
    }

    @GetMapping("/listar.html")
    public ModelAndView listar() {
        List<Atendente> atendentes = repositorio.findAll();
        ModelAndView mv = new ModelAndView();
        mv.setViewName("lista-atendente.html");
        mv.addObject("atendentes", atendentes);
        return mv;

    }

    @PostMapping("/nova.html")
    public ModelAndView criar(@Valid Atendente atendente, BindingResult binding) {
        ModelAndView mv = new ModelAndView();
        if (binding.hasErrors()) {
            mv.setViewName("atendente-adicionar.html");
            mv.addObject("atendente", atendente);
            return mv;
        }

        repositorio.save(atendente);
        mv.setViewName("redirect:/login.html");
        return mv;
    }

    @GetMapping("/nova.html")
    public ModelAndView criar() {
        ModelAndView mv = new ModelAndView();
        String codigoAcesso = String.valueOf(Long.toHexString(new Random().nextLong()));
        mv.setViewName("atendente-adicionar.html");
        mv.addObject("atendente", new Atendente("nomeCompleto",codigoAcesso,"telefone", "celular","email"));
        return mv;
    }

    @GetMapping(value = { "/editar{id}" })
    public ModelAndView editar(@RequestParam Long id) {
        ModelAndView mv = new ModelAndView();
        Atendente atendente = repositorio.findById(id).get();
        mv.addObject("atendente",atendente);
        mv.setViewName("atendente-form-edit.html");
        return mv;
    }

    @PostMapping("/editar{id}")
    public ModelAndView editar(@PathVariable Long id, @Valid Atendente atendente, BindingResult binding) {
        ModelAndView mv = new ModelAndView();
        if (binding.hasErrors()) {
            mv.setViewName("atendente-form-edit.html");
            mv.addObject("atendente", atendente);
            return mv;
        }
        repositorio.save(atendente);
        mv.setViewName("redirect:listar.html");
        return mv;
    }

    @GetMapping(value = { "/excluir.html" })
    public ModelAndView excluir(@RequestParam Long id) {
        ModelAndView mv = new ModelAndView();
        repositorio.deleteById(id);
        mv.setViewName("redirect:/atendente/listar.html");
        return mv;
    }
    
}