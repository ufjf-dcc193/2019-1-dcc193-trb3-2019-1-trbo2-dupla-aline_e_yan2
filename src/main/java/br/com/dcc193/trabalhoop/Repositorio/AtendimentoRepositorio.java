package br.com.dcc193.trabalhoop.Repositorio;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.dcc193.trabalhoop.Modelo.Atendente;
import br.com.dcc193.trabalhoop.Modelo.Atendimento;
import br.com.dcc193.trabalhoop.Modelo.Categoria;
import br.com.dcc193.trabalhoop.Modelo.Usuario;


/**
 * AtendimentoRepositorio
 */
@Repository
public interface AtendimentoRepositorio extends JpaRepository<Atendimento,Long>{

    @Query("SELECT a FROM Atendimento a WHERE a.idAtendente =:atendente and a.dataCriacao =:data")
    Atendimento getAtendimentoByAtemdemteAndData(@Param("atendente") Atendente iAtendente,
     @Param("data") Date data);

    List<Atendimento> findByStatusNotLikeOrderByIdCategoria(String status);

    @Query("SELECT a FROM Atendimento a WHERE a.idAtendente =:atendente and a.status !=:status")
    List<Atendimento> getAtendimentoByAtemdemteAndStatus(@Param("atendente") Atendente iAtendente,
     @Param("status") String status);
     
    @Query("SELECT a FROM Atendimento a WHERE a.idCategoria=:categoria and a.status!= 'Fechado'")
    List<Atendimento> getTodosAtendimentoDeStatusDiferenteDe(@Param("categoria") Categoria idCategoria);

    @Query("SELECT a FROM Atendimento a WHERE a.idAtendente=:atendente and a.status!= 'Fechado'")
    List<Atendimento> getTodosAtendimentosDiferentesDeFechado(@Param("atendente") Atendente atendente);

    List<Atendimento> findByIdUsuario(Usuario idUsuario);
    List<Atendimento> findByIdAtendente(Atendente idAtendente);
    List<Atendimento> findByIdCategoria(Categoria idCategoria);
}