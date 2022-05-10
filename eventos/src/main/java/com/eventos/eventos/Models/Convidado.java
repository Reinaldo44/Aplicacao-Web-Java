package com.eventos.eventos.Models;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
public class Convidado {

    @Id
    @NotBlank
    private String rg;

    @NotEmpty
    private String nomeConvidado;

    @ManyToOne
   
    private Evento evento;

    public String getRg() {
        return rg;
    }
    public Evento getEvento() {
        return evento;
    }
    public void setEvento(Evento evento) {
        this.evento = evento;
    }
    public String getNomeConvidado() {
        return nomeConvidado;
    }
    public void setNomeConvidado(String nomeConvidado) {
        this.nomeConvidado = nomeConvidado;
    }
    public void setRg(String rg) {
        this.rg = rg;
    }
    
}
