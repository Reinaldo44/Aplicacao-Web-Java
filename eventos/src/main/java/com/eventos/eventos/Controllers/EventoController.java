package com.eventos.eventos.Controllers;

import com.eventos.eventos.Models.Convidado;
import com.eventos.eventos.Models.Evento;
import com.eventos.eventos.Repository.ConvidadoRepository;
import com.eventos.eventos.Repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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
    public String formEnviar(Evento evento){
        
        er.save(evento);
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
    public String detalhesEventoPost(@PathVariable("codigo") long codigo, Convidado convidado ){

        Evento evento = er.findByCodigo(codigo);
        convidado.setEvento(evento);
        cr.save(convidado);
        return "redirect:/{codigo}";
    }

}
