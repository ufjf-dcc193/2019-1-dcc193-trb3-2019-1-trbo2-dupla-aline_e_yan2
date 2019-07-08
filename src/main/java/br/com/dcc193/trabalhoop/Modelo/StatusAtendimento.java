package br.com.dcc193.trabalhoop.Modelo;

/**
 * StatusAtendimento
 */
public enum StatusAtendimento {

    REVISAO(0,"Revisão"),
    ABERTO(1,"Aberto"),
    BLOQUEADO(2,"Bloqueado"),
    EMANDAMENTO(3,"Em Andamento"),
    FECHADO(4,"Fechado");

    
    private Integer id;
    private String descricaoStatus;


    private StatusAtendimento(Integer id, String descricaoStatus) {
        this.id = id;
        this.descricaoStatus = descricaoStatus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricaoStatus() {
        return descricaoStatus;
    }

    public void setDescricaoStatus(String descricaoStatus) {
        this.descricaoStatus = descricaoStatus;
    }

    private StatusAtendimento() {
    }

}