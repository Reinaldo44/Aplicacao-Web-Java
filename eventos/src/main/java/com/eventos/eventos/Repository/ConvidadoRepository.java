package com.eventos.eventos.Repository;

import com.eventos.eventos.Models.Convidado;
import com.eventos.eventos.Models.Evento;

import org.springframework.data.repository.CrudRepository;

public interface ConvidadoRepository extends CrudRepository<Convidado, String>{

    Iterable<Convidado> findByEvento(Evento evento);

}
