package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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

    public String getData() {
        String alteraDia = "";
        if (this.data.getDayOfMonth() < 10) {
            alteraDia += "0";
        }
        alteraDia += this.data.getDayOfMonth() + "/";
        if (this.data.getMonthValue() < 10) {
            alteraDia += "0";
        }
        alteraDia += this.data.getMonthValue() + "/" + this.data.getYear();
        return alteraDia;
    }

    public void setData(String data) {        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        if(data == null){
        this.data = LocalDate.now();
        }else{
            this.data = LocalDate.parse(data, dtf);
        }
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
    
    @Override
    public String toString(){
        String m = "";

        m += "------ Boas Vindas ao Casamento de " + this.pessoaNoivo.getNome() + " e " + this.pessoaNoiva.getNome() + " ------";
        m += "\n------------------------   Data: " + this.getData() + "   ------------------------";

        return m;
    }
}
