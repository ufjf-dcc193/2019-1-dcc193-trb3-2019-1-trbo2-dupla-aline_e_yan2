package br.com.dcc193.trabalhoop.Controlador;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.dcc193.trabalhoop.Modelo.Atendente;
import br.com.dcc193.trabalhoop.Repositorio.AtendenteRepositorio;
import br.com.dcc193.trabalhoop.Sesson.Sessao;

import org.springframework.web.bind.annotation.RequestMethod;



/**
 * LoginControlador
 */
@Controller
 public class LoginControlador {
     @Autowired
     AtendenteRepositorio atendenteRep;
    @RequestMapping(value="/login.html", method=RequestMethod.GET)
    public String getLoginForm(){
         return "login.html";
    }
    @RequestMapping(value="/logout.html")
    public ModelAndView getLoginForm(HttpServletRequest request){
        ModelAndView mv = new ModelAndView();
        Sessao.logout(request);
        mv.setViewName("login.html");
        return mv;
    }
    
    @RequestMapping(value = "/login.html", method = RequestMethod.POST)
    public ModelAndView login (@ModelAttribute(name="atendente") Atendente atendente, Model model, HttpServletRequest req){
        ModelAndView mv = new ModelAndView();
        String email= atendente.getEmail();
        String senha= atendente.getCodigoAcesso();
        Atendente atendenteValidado = atendenteRep.getAtendenteByEmailAndSenha(email, senha);
        if(atendenteValidado != null){
            Sessao.logar(req, atendenteValidado);
            mv.setViewName("pagina-inicial.html");
        }
        if("admin".equals(email) && "admin".equals(senha)){
            
            mv.setViewName("home.html");
            return mv;
        }
        mv.addObject("atendente", atendenteValidado);
        return mv;
    }


    
}