package br.com.dcc193.trabalhoop.Repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.dcc193.trabalhoop.Modelo.*;



/**
 * EventoRepositorio
 */
@Repository
public interface EventoRepositorio extends JpaRepository<Evento,Long> {

    List<Evento> findByIdatendimento(Atendimento idatendimento);
}