package com.eventos.eventos.Controllers;

import javax.validation.Valid;
import com.eventos.eventos.Models.Convidado;
import com.eventos.eventos.Models.Evento;
import com.eventos.eventos.Repository.ConvidadoRepository;
import com.eventos.eventos.Repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class EventoController {

    @Autowired
    private EventoRepository er;

    @Autowired
    private ConvidadoRepository cr;
    
    @RequestMapping( value="/cadastrarEvento", method=RequestMethod.GET)
    public String form(){
       
        return "eventos/formEvento";
    }

    @RequestMapping( value="/cadastrarEvento", method=RequestMethod.POST)
    public String formEnviar(@Valid Evento evento, BindingResult result, RedirectAttributes atribute){
        
        if(result.hasErrors()){
            atribute.addFlashAttribute("mensagem", "Verifique os campos!");
            return "redirect:/cadastrarEvento";
         }

        er.save(evento);
        atribute.addFlashAttribute("mensagem", "Dados salvos com sucesso!");
        return "redirect:/cadastrarEvento";
    }
    
    @RequestMapping("/eventos")
    public ModelAndView listarEventos(){

        ModelAndView viewEventos = new ModelAndView("index");
        Iterable<Evento> eventos = er.findAll();
        viewEventos.addObject("eventos", eventos);
        return viewEventos;
    }

    @RequestMapping(value="/{codigo}", method=RequestMethod.GET)
    public ModelAndView detalhesEvento(@PathVariable("codigo") long codigo){

        Evento eventos = er.findByCodigo(codigo);
        ModelAndView mView = new ModelAndView("eventos/detalhesEvento");
        mView.addObject("evento", eventos);

       Iterable<Convidado> convidados = cr.findByEvento(eventos);
        mView.addObject("convidados", convidados);
        return mView;
    }

   @RequestMapping(value="/{codigo}", method=RequestMethod.POST)
    public String detalhesEventoPost(@PathVariable("codigo") long codigo, @Valid Convidado convidado, BindingResult result, RedirectAttributes atribute ){

        if(result.hasErrors()){
           atribute.addFlashAttribute("mensagem", "Verifique os campos!");
           return "redirect:/{codigo}";
        }

        Evento evento = er.findByCodigo(codigo);
        convidado.setEvento(evento);
        cr.save(convidado);
        atribute.addFlashAttribute("mensagem", "Dados salvos com sucesso!");
        return "redirect:/{codigo}";

    }

}
