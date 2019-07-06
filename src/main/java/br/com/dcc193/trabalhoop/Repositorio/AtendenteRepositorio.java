package br.com.dcc193.trabalhoop.Repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.dcc193.trabalhoop.Modelo.Atendente;


/**
 * AtendenteRepositorio
 */
@Repository
public interface AtendenteRepositorio extends JpaRepository<Atendente,Long> {

    
}