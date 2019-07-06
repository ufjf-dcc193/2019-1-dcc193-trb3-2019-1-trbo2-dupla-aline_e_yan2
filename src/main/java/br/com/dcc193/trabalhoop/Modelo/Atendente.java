package br.com.dcc193.trabalhoop.Modelo;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

/**
 * Atendente
 */

@Entity
public class Atendente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String nomeCompleto;

    @NotBlank
    private String nomeAcesso;

    @NotBlank
    private String telefone;

    @NotBlank
    private String celular;

    @NotBlank
    private String email;

    public Atendente() {
    }

    public Atendente(@NotBlank String nomeCompleto, @NotBlank String nomeAcesso, @NotBlank String telefone,
            @NotBlank String celular, @NotBlank String email) {
        this.nomeCompleto = nomeCompleto;
        this.nomeAcesso = nomeAcesso;
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

    public String getNomeAcesso() {
        return nomeAcesso;
    }

    public void setNomeAcesso(String nomeAcesso) {
        this.nomeAcesso = nomeAcesso;
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
        return "Atendente [celular=" + celular + ", email=" + email + ", id=" + id + ", nomeAcesso=" + nomeAcesso
                + ", nomeCompleto=" + nomeCompleto + ", telefone=" + telefone + "]";
    }
    
}