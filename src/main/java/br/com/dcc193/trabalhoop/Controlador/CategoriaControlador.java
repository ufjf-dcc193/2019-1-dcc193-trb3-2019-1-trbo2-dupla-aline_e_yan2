package br.com.dcc193.trabalhoop.Controlador;

import java.util.List;

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

import br.com.dcc193.trabalhoop.Modelo.Categoria;
import br.com.dcc193.trabalhoop.Repositorio.CategoriaRepositorio;



@Controller
@RequestMapping("/categoria")
public class CategoriaControlador {

   @Autowired
   private CategoriaRepositorio repositorio;

   @RequestMapping({ "", "/", "/index.html" })
   public ModelAndView atividadeIndex() {
       ModelAndView mv = new ModelAndView();
       mv.setViewName("categoria-index.html");
       return mv;
   }

   @GetMapping("/listar.html")
   public ModelAndView listar() {
       List<Categoria> categorias = repositorio.findAll();
       ModelAndView mv = new ModelAndView();
       mv.setViewName("lista-categoria.html");
       mv.addObject("categorias", categorias);
       return mv;

   }

   @PostMapping("/nova.html")
   public ModelAndView criar(@Valid Categoria categoria, BindingResult binding) {
       ModelAndView mv = new ModelAndView();
       if (binding.hasErrors()) {
           mv.setViewName("categoria-adicionar.html");
           mv.addObject("categoria", categoria);
           return mv;
       }

       repositorio.save(categoria);
       mv.setViewName("redirect:/categoria/listar.html");
       return mv;
   }

   @GetMapping("/nova.html")
   public ModelAndView criar() {
       ModelAndView mv = new ModelAndView();
       mv.setViewName("categoria-adicionar.html");
       mv.addObject("categoria", new Categoria("titulo","descricaoTextual"));
       return mv;
   }

   @GetMapping(value = { "/editar{id}" })
   public ModelAndView editar(@RequestParam Long id) {
       ModelAndView mv = new ModelAndView();
       Categoria categoria = repositorio.findById(id).get();
       mv.addObject("categoria",categoria);
       mv.setViewName("categoria-form-edit.html");
       return mv;
   }

   @PostMapping("/editar{id}")
   public ModelAndView editar(@PathVariable Long id, @Valid Categoria categoria, BindingResult binding) {
       ModelAndView mv = new ModelAndView();
       if (binding.hasErrors()) {
           mv.setViewName("categoria-form-edit.html");
           mv.addObject("categoria", categoria);
           return mv;
       }
       repositorio.save(categoria);
       mv.setViewName("redirect:listar.html");
       return mv;
   }

   @GetMapping(value = { "/excluir.html" })
   public ModelAndView excluir(@RequestParam Long id) {
       ModelAndView mv = new ModelAndView();
       repositorio.deleteById(id);
       mv.setViewName("redirect:/categoria/listar.html");
       return mv;
   }
   
}