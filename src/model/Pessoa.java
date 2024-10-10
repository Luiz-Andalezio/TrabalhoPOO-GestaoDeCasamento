package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Pessoa {

    private long id;
    private String nome;
    private LocalDate nascimento;
    private String telefone;
    private LocalDate dataCriacao;
    private LocalDate dataModificacao;
    private static long incrementaId = 0;

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNascimento() {
        String alteraDia = "";
        if (this.nascimento.getDayOfMonth() < 10){
            alteraDia += "0";
        }
        alteraDia += this.nascimento.getDayOfMonth() + "/";
        if (this.nascimento.getMonthValue() < 10){
            alteraDia += "0";
        }
        alteraDia += this.nascimento.getMonthValue() + "/" + this.nascimento.getYear();
        return alteraDia;
    }

    public void setNascimento(String nascimento) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        if ("".equals(nascimento)) {
            this.nascimento = LocalDate.now();
        } else {
            this.nascimento = LocalDate.parse(nascimento, dtf);
        }
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
        this.id = ++Pessoa.incrementaId;
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

    public void setDataModificacao() {
        this.dataModificacao = LocalDate.now();
    }

    @Override
    public String toString() {
        String m = "";
        m += "ID: " + this.id + "\n";
        m += "Nome: " + this.nome + "\n";
        m += "Nascimento: " + getNascimento() + "\n";
        m += "Telefone: " + this.telefone + "\n";
        m += "Criado no dia: " + getDataCriacao() + "\n";
        if (!"".equals(getDataModificacao())) {
            m += "Modificado no dia: " + getDataModificacao() + "\n";
        }
        return m;
    }
}
