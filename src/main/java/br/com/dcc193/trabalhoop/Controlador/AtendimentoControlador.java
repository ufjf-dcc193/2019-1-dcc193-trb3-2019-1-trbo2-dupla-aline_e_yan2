package br.com.dcc193.trabalhoop.Controlador;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.dcc193.trabalhoop.Modelo.Atendimento;
import br.com.dcc193.trabalhoop.Modelo.Evento;
import br.com.dcc193.trabalhoop.Repositorio.AtendenteRepositorio;
import br.com.dcc193.trabalhoop.Repositorio.AtendimentoRepositorio;
import br.com.dcc193.trabalhoop.Repositorio.CategoriaRepositorio;
import br.com.dcc193.trabalhoop.Repositorio.EventoRepositorio;
import br.com.dcc193.trabalhoop.Repositorio.UsuarioRepositorio;
import br.com.dcc193.trabalhoop.Sesson.Sessao;

/**
 * AtendimentoControlador
 */

@Controller
@RequestMapping("/atendimento")
public class AtendimentoControlador {

    @Autowired
    private AtendimentoRepositorio atRepositorio;
    @Autowired
    private AtendenteRepositorio atendRepositorio;
    @Autowired
    private UsuarioRepositorio usinRepositorio;
    @Autowired
    private CategoriaRepositorio catRepositorio;
    @Autowired
    private EventoRepositorio evRepositorio;

   

    @RequestMapping({ "", "/", "/home" })
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("atendimento-index.html");
        return mv;
    }

 
    @PostMapping(value = "/admin")
    public ModelAndView criar(@Valid Atendimento atendimento,
             BindingResult binding, HttpServletRequest request) {
                 
        ModelAndView mv = new ModelAndView();
        if (binding.hasErrors()) {
            mv.setViewName("atendimento-gestao.html");
            mv.addObject("atendimento", atendimento);
            mv.addObject("atopcoes",atendRepositorio.findAll());
            mv.addObject("catopcoes",catRepositorio.findAll());
            mv.addObject("usinopcoes",usinRepositorio.findAll());
            return mv;
        }
        atendimento.setStatus("Revisao");
        atRepositorio.save(atendimento);
        Evento eventoDeAbertura = new Evento("Abertura",
                 atRepositorio.getAtendimentoByAtemdemteAndData(
                     Sessao.getAtendenteLogado(request, atendRepositorio),
                      atendimento.getDataCriacao()));
        evRepositorio.save(eventoDeAbertura);
        
        mv.setViewName("redirect:/atendimento/admin.html");
        return mv;
    }

    @GetMapping("/admin.html")
    public ModelAndView criar() {
        Atendimento atendimento= new Atendimento();
        ModelAndView mv = new ModelAndView();
        atendimento.setDescricaoTextual("Coloque uma descrição");
        mv.addObject("atopcoes",atendRepositorio.findAll());
        mv.addObject("catopcoes",catRepositorio.findAll());
        mv.addObject("usinopcoes",usinRepositorio.findAll());
        mv.setViewName("atendimento-gestao.html");
        mv.addObject("atendimento",atendimento);
        return mv;
    }

}