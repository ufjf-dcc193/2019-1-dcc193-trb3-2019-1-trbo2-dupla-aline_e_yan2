package br.com.dcc193.trabalhoop.Repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.dcc193.trabalhoop.Modelo.Atendente;



/**
 * AtendenteRepositorio
 */
@Repository
public interface AtendenteRepositorio extends JpaRepository<Atendente,Long> {

    @Query("SELECT a FROM Atendente a WHERE a.email =:email and a.codigoAcesso =:codigoAcesso")
    Atendente getAtendenteByEmailAndSenha(@Param("email") String email,
     @Param("codigoAcesso") String codigoAcesso);
    
}