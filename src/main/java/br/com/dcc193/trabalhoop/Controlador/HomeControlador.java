package br.com.dcc193.trabalhoop.Controlador;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.dcc193.trabalhoop.Modelo.Atendente;
import br.com.dcc193.trabalhoop.Repositorio.AtendenteRepositorio;
import br.com.dcc193.trabalhoop.Sesson.*;

/**
 * HomeControlador
 */
@Controller
public class HomeControlador {
    @Autowired
    AtendenteRepositorio atendenteRep;

    @RequestMapping({ "", "/", "/login","/home" })
    public ModelAndView index(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        Atendente atendente = Sessao.getAtendenteLogado(request,atendenteRep);
        if (atendente !=null){
            mv.setViewName("pagina-inicial.html");
            mv.addObject("atendente", atendente);
        }else{
            mv.setViewName("login.html");
        }
        return mv;
    }

}