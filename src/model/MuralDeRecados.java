package model;

import java.time.LocalDate;

public class MuralDeRecados {
    private int id;
    private Pessoa pessoa;
    private String comentario;
    private LocalDate dataCriacao;
    private LocalDate dataModificacao;
    
    //GETTERS E SETTERS
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
        
    public String getDataCriacao() {
        String alteraDia = "";
        if (this.dataCriacao.getDayOfMonth() < 10){
            alteraDia += "0";
        }
        alteraDia += this.dataCriacao.getDayOfMonth() + "/";
        if (this.dataCriacao.getMonthValue() < 10){
            alteraDia += "0";
        }
        alteraDia += this.dataCriacao.getMonthValue() + "/" + this.dataCriacao.getYear();
        return alteraDia;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = LocalDate.now();
        this.dataModificacao = LocalDate.now();
    }

    public String getDataModificacao() {
        String alteraDia = "";
        if (this.dataModificacao.getDayOfMonth() < 10){
            alteraDia += "0";
        }
        alteraDia += this.dataModificacao.getDayOfMonth() + "/";
        if (this.dataModificacao.getMonthValue() < 10){
            alteraDia += "0";
        }
        alteraDia += this.dataModificacao.getMonthValue() + "/" + this.dataModificacao.getYear();
        return alteraDia;
    }

    public void setDataModificacao(LocalDate dataModificacao) {
        this.dataModificacao = LocalDate.now();
    }  
}
