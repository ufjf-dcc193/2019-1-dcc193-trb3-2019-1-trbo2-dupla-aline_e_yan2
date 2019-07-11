package br.com.dcc193.trabalhoop.Repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.dcc193.trabalhoop.Modelo.Categoria;



/**
 * CategoriaRepositorio
 */
@Repository
public interface CategoriaRepositorio extends JpaRepository<Categoria,Long> {

    @Query("SELECT c FROM Categoria c WHERE c.id!=:idCategoria")
    List<Categoria> pegarCategoriaDiferenteDeId(@Param("idCategoria") Long id);
    
}