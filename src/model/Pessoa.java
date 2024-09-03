package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Pessoa {

    private int id;
    private String nome;
    private int idade;
    private LocalDate nascimento;
    private String telefone;
    private LocalDate dataCriacao;
    private LocalDate dataModificacao;
    
    // METODOS
    
    //GETTERS E SETTERS
    public String getTelefone(){
        return telefone;
    }

    public void setTelefone(String telefone){
        this.telefone = telefone;
    }
    
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

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public LocalDate getNascimento() {
        return nascimento;
    }

    public void setNascimento(String nascimento) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.nascimento = LocalDate.parse(nascimento, dtf);
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
    
    @Override
    public String toString(){
        String m = "";
        m += "Id: " + this.id + "\n";
        m += "Nome: " + this.nome + "\n";
        m += "Idade: " + this.idade + "\n";
        m += "Nascimento: " + this.nascimento + "\n";
        m += "Telefone: " + this.telefone + "\n";
        m += "Criado no dia: " + this.dataCriacao + "\n";
        m += "Modificado no dia: " + this.dataModificacao + "\n";
        return m;
    }
}
