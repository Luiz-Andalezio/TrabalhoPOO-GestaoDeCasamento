package model;

import java.time.LocalDate;

public class Evento {
    private int id;
    private LocalDate data;
    private Cerimonial cerimonial;
    private Igreja igreja;
    private Cartorio cartorio;
    private Pessoa pessoaNoivo;
    private Pessoa pessoaNoiva;
    private LocalDate dataCriacao;
    private LocalDate dataModificacao;

    //GETTERS E SETTERS
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Cerimonial getCerimonial() {
        return cerimonial;
    }

    public void setCerimonial(Cerimonial cerimonial) {
        this.cerimonial = cerimonial;
    }

    public Igreja getIgreja() {
        return igreja;
    }

    public void setIgreja(Igreja igreja) {
        this.igreja = igreja;
    }

    public Cartorio getCartorio() {
        return cartorio;
    }

    public void setCartorio(Cartorio cartorio) {
        this.cartorio = cartorio;
    }

    public Pessoa getPessoaNoivo() {
        return pessoaNoivo;
    }

    public void setPessoaNoivo(Pessoa pessoaNoivo) {
        this.pessoaNoivo = pessoaNoivo;
    }

    public Pessoa getPessoaNoiva() {
        return pessoaNoiva;
    }

    public void setPessoaNoiva(Pessoa pessoaNoiva) {
        this.pessoaNoiva = pessoaNoiva;
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
}
