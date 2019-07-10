package br.com.dcc193.trabalhoop.Controlador;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
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
import br.com.dcc193.trabalhoop.Modelo.Atendimento;
import br.com.dcc193.trabalhoop.Modelo.Categoria;
import br.com.dcc193.trabalhoop.Modelo.Evento;
import br.com.dcc193.trabalhoop.Modelo.Usuario;
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
        eventoDeAbertura.setDescricaoTextual("Abertura do Evento.");
        evRepositorio.save(eventoDeAbertura);
        
        mv.setViewName("redirect:/atendimento/listar.html");
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

    @GetMapping("/listar.html")
    public ModelAndView listar() {
        ModelAndView mv = new ModelAndView();
        List<Atendimento> atendimentos = atRepositorio.findAll();
        mv.setViewName("atendimento-listar.html");
        mv.addObject("atendimentos", atendimentos);
        return mv;
 
    }
    @GetMapping(value = { "/editar{id}" })
    public ModelAndView editar(@RequestParam Long id) {
        ModelAndView mv = new ModelAndView();
        Atendimento atendimento = atRepositorio.findById(id).get();
        
        String eventos="";
        for (Evento evento : evRepositorio.findByIdatendimento(atendimento)) {
            eventos+= evento.toString();
        }
        mv.addObject("atopcoes",atendRepositorio.findAll());
        mv.addObject("catopcoes",catRepositorio.findAll());
        mv.addObject("usinopcoes",usinRepositorio.findAll());
        mv.addObject("eventos", eventos);
        mv.addObject("novaDescricaoTextual", "Descreva o que esta fazendo");
        mv.addObject("atendimento",atendimento);
        mv.setViewName("atendimento-editar.html");
        return mv;
    }
    @PostMapping("/editar{id}")
    public ModelAndView editar(@PathVariable Long id, @Valid Atendimento atendimento,
    BindingResult binding, String novaDescricaoTextual, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        if (binding.hasErrors()) {
            mv.setViewName("usuario-form-edit.html");
            mv.addObject("atendimento", atendimento);
            return mv;
        }
        Atendimento atendimentoVelho = atRepositorio.findById(id).get();
        atendimento.setDataCriacao(atendimentoVelho.getDataCriacao());
        concatenaDescricao(atendimentoVelho, atendimento, novaDescricaoTextual, request);
        atRepositorio.save(atendimento);
        List<Atendimento> atendimentos = atRepositorio.findAll();
        mv.setViewName("atendimento-listar.html");
        mv.addObject("atendimentos", atendimentos);
        return mv;
    }
    
    private void concatenaDescricao(Atendimento a, Atendimento atendimento, 
    String novaDescricao, HttpServletRequest request) {
        Atendente atendente =Sessao.getAtendenteLogado(request, atendRepositorio); 
        String format, alteracao, descricaoDoAtendimento;
        descricaoDoAtendimento = "Descrição do atendimento: "+novaDescricao+"\n";
        format = "Autor: "+atendente.getNomeCompleto()+"\n";
        format +="A seguinte alteração foi realizada.\n";
        if(!a.getStatus().equals(atendimento.getStatus())){
            alteracao = "Alteração de status";
            Evento evento = new Evento(alteracao, atendimento);
            alteracao+=": "+a.getStatus()+"para --->"+atendimento.getStatus()+"\n";
            evento.setDescricaoTextual(format+alteracao+descricaoDoAtendimento);
            evRepositorio.save(evento);
        }if(!a.getIdAtendente().equals(atendimento.getIdAtendente())){
            alteracao ="Alteração de atendente";
            Evento evento = new Evento(alteracao, atendimento);
            alteracao+=": "+a.getIdAtendente().getNomeCompleto()+"para ->"
            +atendimento.getIdAtendente().getNomeCompleto()+"\n";
            evento.setDescricaoTextual(format+alteracao+descricaoDoAtendimento);
            evRepositorio.save(evento);
        }if(a.getIdUsuario()==null && atendimento.getIdUsuario()!= null){
            alteracao="Alteração de usuario";
            Evento evento = new Evento(alteracao, atendimento);
            alteracao+=":Não indentificado para ->"
            +atendimento.getIdUsuario().getNomeCompleto()+"\n";
            evento.setDescricaoTextual(format+alteracao+descricaoDoAtendimento);
            evRepositorio.save(evento);
        }else if(a.getIdUsuario()!=null && atendimento.getIdUsuario()== null){
            alteracao="Alteração de usuario";
            Evento evento = new Evento(alteracao, atendimento);
            alteracao+=":"+a.getIdUsuario().getNomeCompleto()
            +" para -> Não indentificado \n";
            evento.setDescricaoTextual(format+alteracao+descricaoDoAtendimento);
            evRepositorio.save(evento);
        }else if(a.getIdUsuario()!=null && atendimento.getIdUsuario() != null){
            if(!a.getIdUsuario().equals(atendimento.getIdUsuario())) {
                alteracao="Alteração de usuario";
                Evento evento = new Evento(alteracao, atendimento);
                alteracao+=": "+a.getIdUsuario().getNomeCompleto()+"para ->"
                +atendimento.getIdUsuario().getNomeCompleto()+"\n";
                evento.setDescricaoTextual(format+alteracao+descricaoDoAtendimento);
                evRepositorio.save(evento);
            
            }
        }
        if(!a.getIdCategoria().equals(atendimento.getIdCategoria())){
            alteracao="Alteração de categoria";
            Evento evento = new Evento(alteracao, atendimento);
            alteracao+=": "+a.getIdCategoria().getTitulo()+"para ->"
            +atendimento.getIdCategoria().getTitulo()+"\n";
            evento.setDescricaoTextual(format+alteracao+descricaoDoAtendimento);
            evRepositorio.save(evento);
        }
    }
    @GetMapping("/listar-atendimento-categoria{id}")
    public ModelAndView listarAtendimentoCategoria(@RequestParam Long id) {
        Categoria categoria = catRepositorio.findById(id).get();
        List<Atendimento> atendimentos = atRepositorio
                    .getTodosAtendimentoDeStatusDiferenteDe(categoria); 
        ModelAndView mv = new ModelAndView();
        mv.setViewName("lista-atendimento-categoria.html");
        mv.addObject("atendimentos", atendimentos);
        return mv;
    }
    
    

}