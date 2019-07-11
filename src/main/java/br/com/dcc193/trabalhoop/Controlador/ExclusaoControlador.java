package br.com.dcc193.trabalhoop.Controlador;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.dcc193.trabalhoop.Modelo.Atendente;
import br.com.dcc193.trabalhoop.Modelo.Atendimento;
import br.com.dcc193.trabalhoop.Modelo.Categoria;
import br.com.dcc193.trabalhoop.Modelo.Evento;
import br.com.dcc193.trabalhoop.Modelo.Usuario;
import br.com.dcc193.trabalhoop.Repositorio.AtendenteRepositorio;
import br.com.dcc193.trabalhoop.Repositorio.AtendimentoRepositorio;
import br.com.dcc193.trabalhoop.Repositorio.CategoriaRepositorio;
import br.com.dcc193.trabalhoop.Repositorio.EventoRepositorio;
import br.com.dcc193.trabalhoop.Repositorio.UsuarioRepositorio;

/**
 * ExclusaoControlador
 */

@Controller
@RequestMapping("/exclusao")
public class ExclusaoControlador {

    @Autowired
    private AtendimentoRepositorio atRepositorio;
    @Autowired
    private AtendenteRepositorio atendRepositorio;
    @Autowired
    private UsuarioRepositorio userRepositorio;
    @Autowired
    private CategoriaRepositorio catRepositorio;
    @Autowired
    private EventoRepositorio evRepositorio;


    @GetMapping("/atendente{idAntigo}{idNovo}.html")
    public ModelAndView atendente(@RequestParam Long idAntigo, @RequestParam Long idNovo) {
        ModelAndView mv = new ModelAndView();
        Atendente atendenteAntigo = atendRepositorio.findById(idAntigo).get();
        Atendente atendenteNovo = atendRepositorio.findById(idNovo).get();
        for (Atendimento atendimento : atRepositorio.findByIdAtendente(atendenteAntigo)) {
            String format, alteracao;
            format = "Exclusão de atendente \n";
            format +="A seguinte alteração foi realizada.\n";
            alteracao ="Alteração de atendente";
            Evento evento = new Evento(alteracao, atendimento);
            alteracao+=": "+atendenteAntigo.getNomeCompleto()+"para -->"
            +atendenteNovo.getNomeCompleto()+"\n";
            evento.setDescricaoTextual(format+alteracao);
            evRepositorio.save(evento);
            atendimento.setIdAtendente(atendenteNovo);
            atRepositorio.save(atendimento);
        }
        atendRepositorio.deleteById(idAntigo);
        mv.setViewName("redirect:/atendente/listar.html");
        return mv;
    }

    @GetMapping("/categoria{idAntigo}{idNovo}.html")
    public ModelAndView categoria(@RequestParam Long idAntigo, @RequestParam Long idNovo) {
        ModelAndView mv = new ModelAndView();
        Categoria categoriaAntiga = catRepositorio.findById(idAntigo).get();
        Categoria categoriaNova = catRepositorio.findById(idNovo).get();
        for (Atendimento atendimento : atRepositorio.findByIdCategoria(categoriaAntiga)) {
            String format, alteracao;
            format = "Exclusão de categoria \n";
            format +="A seguinte alteração foi realizada.\n";
            alteracao="Alteração de categoria";
            Evento evento = new Evento(alteracao, atendimento);
            alteracao+=": "+categoriaAntiga.getTitulo()+"para -->"
            +categoriaNova.getTitulo()+"\n";
            evento.setDescricaoTextual(format+alteracao);
            evRepositorio.save(evento);
            atendimento.setIdCategoria(categoriaNova);
            atRepositorio.save(atendimento);
        }
        catRepositorio.deleteById(idAntigo);
        mv.setViewName("redirect:/categoria/listar.html");
        return mv;
    }
    @GetMapping("/usuario{id}.html")
    public ModelAndView usuario(@RequestParam Long idUsuario) {
        ModelAndView mv = new ModelAndView();
        Usuario usuario = userRepositorio.findById(idUsuario).get();
        for (Atendimento atendimento : atRepositorio.findByIdUsuario(usuario)) {
            String format, alteracao;
            format = "Exclusão de usuario \n";
            format +="A seguinte alteração foi realizada.\n";
            alteracao="Alteração de usuario";
            Evento evento = new Evento(alteracao, atendimento);
            alteracao+=": "+usuario.getNomeCompleto()+"para --> Não indentificado\n";
            evento.setDescricaoTextual(format+alteracao);
            evRepositorio.save(evento);
            atendimento.setIdUsuario(null);
            atRepositorio.save(atendimento);
        }
        userRepositorio.deleteById(idUsuario);
        mv.setViewName("redirect:/usuario/listar.html");
        return mv;
    }    

}