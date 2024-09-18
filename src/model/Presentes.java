package model;

import java.time.LocalDate;

public class Presentes {
    private int id;
    private String nome;
    private String tipo;
    private double valor;
    private Pessoa pessoa;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
    
    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = LocalDate.now();
        this.dataModificacao = LocalDate.now();
    }

    public LocalDate getDataModificacao() {
        return dataModificacao;
    }

    public void setDataModificacao(LocalDate dataModificacao) {
        this.dataModificacao = LocalDate.now();
    }   

    public String toString(/*Usuario usuario*/){
        String m = "";
        m += "\nCodigo: " + this.id;
        m += "\nNome: " + this.nome;
        m += "\nTipo: " + this.tipo;
        m += "\nValor: " + this.valor;
        /*if(!usuario.getTipo().equals("convidado")){
            m += "Comprador: " + this.pessoa.getNome();
        }*/
        return m;
    } 

    public String toString(Usuario usuario){
        String m = "";
        m += "\nCodigo: " + this.id;
        m += "\nNome: " + this.nome;
        m += "\nTipo: " + this.tipo;
        m += "\nValor: " + this.valor;
        if(!usuario.getTipo().equals("convidado")){
            m += "Comprador: " + this.pessoa.getNome();
        }
        return m;
    } 
}
