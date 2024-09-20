package model;

import java.time.LocalDate;

public class ConvidadoIndividual {

    private long id;
    private Pessoa pessoa;
    private String parentesco;
    private boolean confirmacao;
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

    public String getParentesco() {
        return parentesco;
    }

    public void setParentesco(String parentesco) {
        this.parentesco = parentesco;
    }

    public boolean isConfirmacao() {
        return confirmacao;
    }

    public void setConfirmacao(boolean confirmacao) {
        this.confirmacao = confirmacao;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao() {
        this.dataCriacao = LocalDate.now();
        this.id = ++ConvidadoIndividual.incrementaId;
    }

    public LocalDate getDataModificacao() {
        return dataModificacao;
    }

    public void setDataModificacao() {
        this.dataModificacao = LocalDate.now();
    }    

    @Override
    public String toString(){
        String m = "";
        m += "--- Convite Individual de ID " + this.id + " ---\n";   
        m += "Informações da pessoa: \n";
        //m += "ID: " + this.pessoa.getId()+ "\n";       
        m += "Nome: " + this.pessoa.getNome()+ "\n";              
        m += "Parentesco: " + this.parentesco + "\n"; 
        m += "Telefone: " + this.pessoa.getTelefone()+ "\n";  
        m += "Data de nascimento: " + this.pessoa.getNascimento()+ "\n";
        m += "Convite feito no dia: " + this.getDataCriacao();
        if (this.getDataModificacao() != null) {
            m += " e modificado no dia: " + this.getDataModificacao();
        }
        m += "\n";              
    return m;
    }
}
