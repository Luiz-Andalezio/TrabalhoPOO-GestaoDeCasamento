package model;

import java.time.LocalDate;

public class Fornecedor {
    private int id;
    private String nome;
    private String cnpj;
    private String telefone;
    private double valorAPagar;
    private double parcelas;
    private boolean estado;
    private LocalDate dataCriacao;
    private LocalDate dataModificacao;

    //GETTERS E SETTERS
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public double getValorAPagar() {
        return valorAPagar;
    }

    public void setValorAPagar(double valorAPagar) {
        this.valorAPagar = valorAPagar;
    }

    public double getParcelas() {
        return parcelas;
    }

    public void setParcelas(double parcelas) {
        this.parcelas = parcelas;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
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
