package model;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.Locale;

public class Fornecedor {

    private long id;
    private String nome;
    private String cnpj;
    private String telefone;
    private double valorAPagar;
    private int parcelas;
    private boolean estado;
    private LocalDate dataCriacao;
    private LocalDate dataModificacao;     
    private static long incrementaId = 0;   
    //final assegura que essa referência não será alterada durante a execução do programa
    private static final Locale localeBR = new Locale("pt", "BR");
    private static final NumberFormat formatador = NumberFormat.getCurrencyInstance(localeBR);

    //GETTERS E SETTERS
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

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public double getValorAPagar() {
        return valorAPagar;
    }

    public void setValorAPagar(double valorAPagar) {
        this.valorAPagar = valorAPagar;
    }

    public int getParcelas() {
        return parcelas;
    }

    public void setParcelas(int parcelas) {
        this.parcelas = parcelas;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
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
        this.id = ++Fornecedor.incrementaId;
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

    @Override
    public String toString() {
        String m = "";
        m += "--------------- Fornecedor de Código: " + this.id + " ---------------";
        m += "\nFornecedor: " + this.nome;
        m += "\nCnpj: " + this.cnpj;
        m += "\nTelefone: " + this.telefone;
        m += "\nValor: " + formatador.format(this.valorAPagar);
        m += "\nParcelas: " + this.parcelas;
        if (this.estado == false) {
            m += "\nNão pago!\n";
        } else {
            m += "\nPago!\n";
        }
            m += "\nRegistrado no dia: " + this.getDataCriacao();
            if (!"".equals(this.getDataModificacao())) {
                m += " e modificado no dia: " + this.getDataModificacao();
            }            
        m += "\n------------------------------------------------------------------\n";
        return m;
    }
}
