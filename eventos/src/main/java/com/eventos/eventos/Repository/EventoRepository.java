package com.eventos.eventos.Repository;

import com.eventos.eventos.Models.Evento;

import org.springframework.data.repository.CrudRepository;

public interface EventoRepository extends CrudRepository<Evento, String>{

    Evento findByCodigo(long codigo); 
      
}
