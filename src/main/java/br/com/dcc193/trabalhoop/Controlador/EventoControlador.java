
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

import br.com.dcc193.trabalhoop.Modelo.Atendimento;
import br.com.dcc193.trabalhoop.Modelo.Evento;
import br.com.dcc193.trabalhoop.Repositorio.AtendenteRepositorio;
import br.com.dcc193.trabalhoop.Repositorio.AtendimentoRepositorio;
import br.com.dcc193.trabalhoop.Repositorio.EventoRepositorio;



/**
 * EventoControlador
 */

 @Controller
 @RequestMapping("/evento")
public class EventoControlador {

    @Autowired
    private AtendimentoRepositorio atenRepositorio;

    @Autowired
    private EventoRepositorio eventoRepositorio;


    @GetMapping("/listar{id}")
    public ModelAndView listar(@RequestParam Long id) {
        Atendimento atendimento = atenRepositorio.findById(id).get();
        List<Evento> eventos = eventoRepositorio.findByIdatendimento(atendimento);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("evento-listar.html");
        mv.addObject("eventos", eventos);
        return mv;
  }
}