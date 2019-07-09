package br.com.dcc193.trabalhoop.Modelo;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

/**
 * Evento
 */

@Entity
public class Evento {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @CreationTimestamp
    private Date data;
    
    @NotBlank
    private String tipo;

    @NotBlank
    private String descricaoTextual;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "atendimento_id")
    private Atendimento idatendimento;

    public Evento() {
    }

    public Evento(@NotBlank String tipo, Atendimento idatendimento, String descricaoTextual) {
        this.tipo = tipo;
        this.idatendimento = idatendimento;
        this.descricaoTextual=descricaoTextual;
    }

    @Override
    public String toString() {
        return "Evento [data=" + data + ", id=" + id + ", idatendimento=" + idatendimento + ", tipo=" + tipo + "]";
    }
    
    
}