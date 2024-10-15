package model;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.Locale;

public class Presentes {

    private long id;
    private String nome;
    private String tipo;
    private double valor;
    private String nomeComprador;
    private LocalDate dataCriacao;
    private LocalDate dataModificacao;    
    private static long incrementaId = 0;
    //final assegura que essa referência não será alterada durante a execução do programa
    private static final Locale localeBR = new Locale("pt", "BR");
    private static final NumberFormat formatador = NumberFormat.getCurrencyInstance(localeBR);

    // GETTERS E SETTERS
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

    public String getNomeComprador() {
        return nomeComprador;
    }

    public void setNomeComprador(String nomeComprador) {
        this.nomeComprador = nomeComprador;
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
        this.id = ++Presentes.incrementaId;
    }

    public String getDataModificacao() {
        String alteraDia = "";
        if (this.dataModificacao != null) {
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

    public String toString(Usuario usuario) {
        String m = "";
        m += "\nCodigo: " + this.id;
        m += "\nNome: " + this.nome;
        m += "\nTipo: " + this.tipo;
        m += "\nValor: " + formatador.format(this.valor);
        if (!usuario.getTipo().equals("convidado")) {
            if (this.nomeComprador != null) {
                m += "\nComprador: " + this.getNomeComprador();
            }
            m += "\nRegistrado no dia: " + this.getDataCriacao();
            if (!"".equals(this.getDataModificacao())) {
                m += " e modificado no dia: " + this.getDataModificacao();
            }
        }
        return m;
    }

    @Override
    public String toString() {
        String m = "";
        m += "Presente de Código: " + this.id;
        m += "\nNome: " + this.nome;
        m += "\nTipo: " + this.tipo;
        m += "\nValor: " + formatador.format(this.valor);
        m += "\nStatus: ";
        if (this.nomeComprador != null) {
            m += "Comprado!\n";
        } else {
            m += "À venda!\n";
        }
        return m;
    }
}
