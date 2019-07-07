package br.com.dcc193.trabalhoop.Controlador;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.dcc193.trabalhoop.Modelo.Atendente;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;



/**
 * LoginControlador
 */
@Controller
 public class LoginControlador {

    @RequestMapping({ "", "/", "/login" })
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("login.html");
        return mv;
    }


    @RequestMapping(value="/login.html", method=RequestMethod.GET)
    public String getLoginForm(){
        return "login.html";
    }
    
    @RequestMapping(value = "/login.html", method = RequestMethod.POST)
    public String login (@ModelAttribute(name="atendente") Atendente atendente, Model model){
  
        String email= atendente.getEmail();
        String senha= atendente.getCodigoAcesso();

        if("admin".equals(email) && "admin".equals(senha)){
            return "home.html";
        }
      model.addAttribute("invalido",true);
      return "login.html";
    }


    
}