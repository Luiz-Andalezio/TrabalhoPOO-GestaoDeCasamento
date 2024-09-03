package model;

public class Cerimonial {
    //private Pessoa pessoa;
    private int id;
    private String nome;
    private String funcao;

    //GETTERS E SETTERS
    /*public Pessoa getPessoa(){
        return pessoa;
    }
    
    public void setPessoa(Pessoa pessoa){
        this.pessoa = pessoa;
    }*/
    
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

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }
}
