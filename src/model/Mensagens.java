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

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao() {
        this.dataCriacao = LocalDate.now();
        this.id = ++Mensagens.incrementaId;
    }

    public LocalDate getDataModificacao() {
        return dataModificacao;
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
        m += "\n\nMensagem enviada no dia: " + this.dataCriacao;
        if (this.dataModificacao != null) {
            m += " e modificada no dia: " + this.dataModificacao;
        }
        m += "\n\n";
        return m;
    }
}
