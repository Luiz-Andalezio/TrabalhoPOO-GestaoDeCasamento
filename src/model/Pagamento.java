package model;

import java.time.LocalDate;

public class Pagamento {
    private int id;
    private Pessoa pessoa;
    private String descricao;
    private Fornecedor fornecedor;
    private double valor;
    private String parcela;    
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getParcela() {
        return parcela;
    }

    public void setParcela(String parcela) {
        this.parcela = parcela;
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
        if (this.dataModificacao == null) {

        } else {
            if (this.dataModificacao.getDayOfMonth() < 10) {
                alteraDia += "0";
            }
            alteraDia += this.dataModificacao.getDayOfMonth() + "/";
            if (this.dataModificacao.getMonthValue() < 10) {
                alteraDia += "0";
            }
            alteraDia += this.dataModificacao.getMonthValue() + "/" + this.dataModificacao.getYear();
        }
        return alteraDia;
    }

    public void setDataModificacao(LocalDate dataModificacao) {
        this.dataModificacao = LocalDate.now();
    }
}
