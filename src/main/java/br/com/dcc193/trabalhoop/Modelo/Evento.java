package br.com.dcc193.trabalhoop.Modelo;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "atendimento_id")
    private Atendimento idatendimento;

    public Evento() {
    }

    public Evento(@NotBlank String tipo, Atendimento idatendimento) {
        this.tipo = tipo;
        this.idatendimento = idatendimento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Atendimento getIdatendimento() {
        return idatendimento;
    }

    public void setIdatendimento(Atendimento idatendimento) {
        this.idatendimento = idatendimento;
    }
    
    @Override
    public String toString() {
        return "Evento [data=" + data + ", id=" + id + ", idatendimento=" + idatendimento + ", tipo=" + tipo + "]";
    }
}