package br.com.dcc193.trabalhoop.Modelo;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

/**
 * Categoria
 */
@Entity
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @NotBlank
    private String titulo;

    @NotBlank
    private String descricaoTextual;

    public Categoria() {
    }

    public Categoria(@NotBlank String titulo, @NotBlank String descricaoTextual) {
        this.titulo = titulo;
        this.descricaoTextual = descricaoTextual;
    }

    @Override
    public String toString() {
        return "Categoria [Id=" + Id + ", descricaoTextual=" + descricaoTextual + ", titulo=" + titulo + "]";
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricaoTextual() {
        return descricaoTextual;
    }

    public void setDescricaoTextual(String descricaoTextual) {
        this.descricaoTextual = descricaoTextual;
    }

    

    

}