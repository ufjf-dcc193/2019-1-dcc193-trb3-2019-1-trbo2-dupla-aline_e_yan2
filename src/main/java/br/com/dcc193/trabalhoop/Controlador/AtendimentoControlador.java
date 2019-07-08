package br.com.dcc193.trabalhoop.Controlador;




import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.dcc193.trabalhoop.Modelo.Atendimento;

import br.com.dcc193.trabalhoop.Repositorio.AtendenteRepositorio;
import br.com.dcc193.trabalhoop.Repositorio.AtendimentoRepositorio;
import br.com.dcc193.trabalhoop.Repositorio.CategoriaRepositorio;
import br.com.dcc193.trabalhoop.Repositorio.UsuarioRepositorio;

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

   

    @RequestMapping({ "", "/", "/home" })
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("atendimento-index.html");
        return mv;
    }

 
    @PostMapping(value = "/admin.html")
    public ModelAndView criar(@Valid Atendimento atendimento, BindingResult binding) {
        ModelAndView mv = new ModelAndView();
        if (binding.hasErrors()) {
            mv.setViewName("atendimento-gestao.html");
            mv.addObject("atendimento", atendimento);
            mv.addObject("atopcoes",atendRepositorio.findAll());
            mv.addObject("catopcoes",catRepositorio.findAll());
            mv.addObject("usinopcoes",usinRepositorio.findAll());
            return mv;
        }
        atRepositorio.save(atendimento);
        mv.setViewName("redirect:/atendimento/index.html");
        return mv;
    }

    @GetMapping("/admin.html")
    public ModelAndView criar() {
        Atendimento atendimento= new Atendimento();
        ModelAndView mv = new ModelAndView();
        mv.setViewName("atendimento-gestao.html");
        mv.addObject("atendimento",atendimento);
        return mv;
    }
    
 

}