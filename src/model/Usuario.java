package model;

import java.time.LocalDate;

public class Usuario {

    private long id;
    private Pessoa pessoa;
    private String tipo;
    private String login;
    private String senha;
    private LocalDate dataCriacao;
    private LocalDate dataModificacao;
    private static long incrementaId = 0;

    //GETTERS E SETTERS
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getDataCriacao() {
        String alteraDia = "";
        if (this.dataCriacao.getDayOfMonth() < 10) {
            alteraDia += "0";
        }
        alteraDia += this.dataCriacao.getDayOfMonth() + "/";
        if (this.dataCriacao.getMonthValue() < 10) {
            alteraDia += "0";
        }
        alteraDia += this.dataCriacao.getMonthValue() + "/" + this.dataCriacao.getYear();
        return alteraDia;
    }

    public void setDataCriacao() {
        this.dataCriacao = LocalDate.now();
        this.id = ++Usuario.incrementaId;
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
        m += "--- Usuário ID " + this.id + " ---";
        m += "\nNome da pessoa: " + this.pessoa.getNome();
        m += "\nTelefone da pessoa: " + this.pessoa.getTelefone();
        m += "\nTipo: " + this.tipo;
        m += "\nLogin: " + this.login;
        m += "\nSenha: " + this.senha;
        m += "\nUsuario criado no dia: " + this.getDataCriacao();
        if (!"".equals(this.getDataModificacao())) {
            m += " e modificado no dia: " + this.getDataModificacao();
        }
        m += "\n\n";
        return m;
    }
}
