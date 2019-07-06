package br.com.dcc193.trabalhoop.Modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * Usuario
 */

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String nomeCompleto;

    @NotBlank
    private String setor;

    @NotBlank
    private String codigoVinculo;

    @NotBlank
    private String telefone;

    @NotBlank
    private String celular;

    @NotBlank
    @Email
    private String email;

    public Usuario() {
    }

    public Usuario(@NotBlank String nomeCompleto, @NotBlank String setor, @NotBlank String codigoVinculo,
            @NotBlank String telefone, @NotBlank String celular, @NotBlank @Email String email) {
        this.nomeCompleto = nomeCompleto;
        this.setor = setor;
        this.codigoVinculo = codigoVinculo;
        this.telefone = telefone;
        this.celular = celular;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    public String getCodigoVinculo() {
        return codigoVinculo;
    }

    public void setCodigoVinculo(String codigoVinculo) {
        this.codigoVinculo = codigoVinculo;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Usuario [celular=" + celular + ", codigoVinculo=" + codigoVinculo + ", email=" + email + ", id=" + id
                + ", nomeCompleto=" + nomeCompleto + ", setor=" + setor + ", telefone=" + telefone + "]";
    }



}