package br.com.dcc193.trabalhoop.Repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.dcc193.trabalhoop.Modelo.Evento;



/**
 * EventoRepositorio
 */
@Repository
public interface EventoRepositorio extends JpaRepository<Evento,Long> {

    
}