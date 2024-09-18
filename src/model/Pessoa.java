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

    // METODOS
    //GETTERS E SETTERS
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

    public LocalDate getNascimento() {
        return nascimento;
    }

    public void setNascimento(String nascimento) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        if (nascimento == null) {
            this.nascimento = LocalDate.now();
        } else {
            this.nascimento = LocalDate.parse(nascimento, dtf);
        }
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao() {
        this.dataCriacao = LocalDate.now();
        this.id = ++Pessoa.incrementaId;
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
        m += "Id: " + this.id + "\n";
        m += "Nome: " + this.nome + "\n";
        m += "Nascimento: " + this.nascimento + "\n";
        m += "Telefone: " + this.telefone + "\n";
        m += "Criado no dia: " + this.dataCriacao + "\n";
        if (this.dataModificacao != null) {
            m += "Modificado no dia: " + this.dataModificacao + "\n";
        }
        return m;
    }
}
