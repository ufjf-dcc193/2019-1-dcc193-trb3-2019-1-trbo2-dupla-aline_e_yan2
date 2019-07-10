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

import br.com.dcc193.trabalhoop.Modelo.Atendimento;
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
        atendimento.setDescricaoTextual(formatarDescricao(atendimento));
        atRepositorio.save(atendimento);
        Evento eventoDeAbertura = new Evento("Abertura",
                 atRepositorio.getAtendimentoByAtemdemteAndData(
                     Sessao.getAtendenteLogado(request, atendRepositorio),
                      atendimento.getDataCriacao()));
        evRepositorio.save(eventoDeAbertura);
        
        mv.setViewName("redirect:/atendimento/");
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
        List<Atendimento> atendimentos = atRepositorio.findAll();
        ModelAndView mv = new ModelAndView();
        mv.setViewName("atendimento-listar.html");
        mv.addObject("atendimentos", atendimentos);
        return mv;
 
    }
    @GetMapping(value = { "/editar{id}" })
    public ModelAndView editar(@RequestParam Long id) {
        ModelAndView mv = new ModelAndView();
        Atendimento atendimento = atRepositorio.getOne(id);
        mv.addObject("atopcoes",atendRepositorio.findAll());
        mv.addObject("catopcoes",catRepositorio.findAll());
        mv.addObject("usinopcoes",usinRepositorio.findAll());
        mv.addObject("novaDescricaoTextual", "Acrescente mais itens na descrição");
        mv.addObject("atendimento",atendimento);
        mv.setViewName("atendimento-editar.html");
        return mv;
    }
    @PostMapping("/editar{id}")
    public ModelAndView editar(@PathVariable Long id, @Valid Atendimento atendimento,
    BindingResult binding, String novaDescricaoTextual) {
        ModelAndView mv = new ModelAndView();
        if (binding.hasErrors()) {
            mv.setViewName("usuario-form-edit.html");
            mv.addObject("atendimento", atendimento);
            return mv;
        }
        //
        // falta concatenar a nova descrição e marcar o q trocou
        //
        atRepositorio.save(atendimento);
        mv.setViewName("redirect:/usuario/listar.html");
        return mv;
    }
    private String formatarDescricao(Atendimento a){
        String format = "Status do atendimento: "+a.getStatus()+"\n";
        format+="Atendente: "+a.getIdAtendente().getNomeCompleto()+"\n";
        if(a.getIdUsuario()!= null)
            format+="Usuario: "+a.getIdUsuario().getNomeCompleto()+"\n";
        format+="Descrição do atendimento: "+a.getDescricaoTextual()+"\n";
        format+="-------------------------"+"\n";        
        return format;
    }
    //
    //pegando o cont de atendimentos por usuarios 
    //
   /* private List<Integer> atendimentos(){
        List<Integer> atendimentos = new ArrayList<>();
        for (Usuario u : atRepositorio.getListUsuariosInAtendimento()) {
            atendimentos.add(atRepositorio.countAtendimentoByidUsuario(u));                    
        }
        return atendimentos;
    }*/

}