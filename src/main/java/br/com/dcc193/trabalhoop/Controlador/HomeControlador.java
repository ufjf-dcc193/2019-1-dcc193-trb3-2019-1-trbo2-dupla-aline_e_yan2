package br.com.dcc193.trabalhoop.Controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * HomeControlador
 */
@Controller
public class HomeControlador {

    @RequestMapping({ "", "/", "/home" })
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("pagina-inicial.html");
        return mv;
    }
}