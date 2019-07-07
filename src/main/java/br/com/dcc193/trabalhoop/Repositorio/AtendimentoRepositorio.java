package br.com.dcc193.trabalhoop.Repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.dcc193.trabalhoop.Modelo.Atendimento;


/**
 * AtendimentoRepositorio
 */
@Repository
public interface AtendimentoRepositorio extends JpaRepository<Atendimento,Long>{

    
}