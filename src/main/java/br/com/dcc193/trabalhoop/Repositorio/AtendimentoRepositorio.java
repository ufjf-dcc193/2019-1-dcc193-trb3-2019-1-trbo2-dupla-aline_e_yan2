package br.com.dcc193.trabalhoop.Repositorio;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.dcc193.trabalhoop.Modelo.Atendente;
import br.com.dcc193.trabalhoop.Modelo.Atendimento;


/**
 * AtendimentoRepositorio
 */
@Repository
public interface AtendimentoRepositorio extends JpaRepository<Atendimento,Long>{

    @Query("SELECT a FROM Atendimento a WHERE a.idAtendente =:atendente and a.dataCriacao =:data")
    Atendimento getAtendimentoByAtemdemteAndData(@Param("atendente") Atendente iAtendente,
     @Param("data") Date data);

}