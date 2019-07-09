package br.com.dcc193.trabalhoop.Sesson;
import br.com.dcc193.trabalhoop.Modelo.*;
import br.com.dcc193.trabalhoop.Repositorio.AtendenteRepositorio;

import java.text.SimpleDateFormat;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Sessao {
    public static final String atendenteLogin = "usuarioLogado";

    public static final Atendente getAtendenteLogado(HttpServletRequest request, AtendenteRepositorio atendenteRep){
        
        HttpSession session = request.getSession(true);
        if (session != null) {
            return (Atendente)session.getAttribute("atendente");
            
        }
        
        return null;
    }

    public static final void logout(HttpServletRequest request){
        HttpSession session = request.getSession(true);
        if (session != null) {
            session.invalidate();
        }
    }
    public static final void logar(HttpServletRequest request, Atendente atendente){
        HttpSession session = request.getSession();
        session.setAttribute("atendente", atendente);
        session.setAttribute("idAtendente", atendente.getId());

    }
    
}