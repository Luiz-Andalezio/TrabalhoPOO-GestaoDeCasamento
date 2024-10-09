package model;

import java.time.LocalDate;

public class Mensagens {

    private long id;
    private String mensagem;
    private String nomeDoMensageiro;
    private LocalDate dataCriacao;
    private LocalDate dataModificacao;
    private static long incrementaId = 0;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getNomeDoMensageiro() {
        return nomeDoMensageiro;
    }

    public void setNomeDoMensageiro(String nomeDoMensageiro) {
        this.nomeDoMensageiro = nomeDoMensageiro;
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

    public void setDataCriacao() {
        this.dataCriacao = LocalDate.now();
        this.id = ++Mensagens.incrementaId;
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

    public void setDataModificacao() {
        this.dataModificacao = LocalDate.now();
    }

    @Override
    public String toString() {
        String m = "";
        m += "--- Mensagem de ID " + this.id + " ---";
        m += "\nNome do mensageiro: " + this.getNomeDoMensageiro();
        m += "\n\nMensagem: " + this.getMensagem();
        m += "\n\nMensagem enviada no dia: " + getDataCriacao();
        if (getDataModificacao() != null) {
            m += " e modificada no dia: " + getDataModificacao();
        }
        m += "\n\n";
        return m;
    }
}
