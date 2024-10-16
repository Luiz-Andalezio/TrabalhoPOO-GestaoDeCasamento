package model;

import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Locale;

public class Pagamento {
    private long id;
    private Pessoa pessoa;
    private String descricao;
    private Fornecedor fornecedor;
    private double valor;
    private String parcela;    
    private String dataCriacao;
    private String dataModificacao;
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

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getParcela() {
        return parcela;
    }

    public void setParcela(String parcela) {
        this.parcela = parcela;
    }
    
    public String getDataCriacao() {
        return this.dataCriacao;
    }

    public void setDataCriacao(LocalDateTime calendario) {
        String concatenaDataHorario = "";

        if (calendario != null) {
            if (calendario.getDayOfMonth() < 10) {
                concatenaDataHorario += "0";
            }
            concatenaDataHorario += calendario.getDayOfMonth() + "/";

            if (calendario.getMonthValue() < 10) {
                concatenaDataHorario += "0";
            }
            concatenaDataHorario += calendario.getMonthValue() + "/";

            concatenaDataHorario += calendario.getYear() + " ";

            LocalDateTime horarioAtualizado = calendario.with(LocalTime.now());

            if (horarioAtualizado.getHour() < 10) {
                concatenaDataHorario += "0";
            }
            concatenaDataHorario += horarioAtualizado.getHour() + ":";

            if (horarioAtualizado.getMinute() < 10) {
                concatenaDataHorario += "0";
            }
            concatenaDataHorario += horarioAtualizado.getMinute() + ":";

            if (horarioAtualizado.getSecond() < 10) {
                concatenaDataHorario += "0";
            }
            concatenaDataHorario += horarioAtualizado.getSecond();
        }        
        this.dataCriacao = concatenaDataHorario;
        this.id = ++Pagamento.incrementaId;
    }

    public String getDataModificacao() {
        return this.dataModificacao;
    }

    public void setDataModificacao(LocalDateTime calendario) {
        String concatenaDataHorario = "";

        if (calendario != null) {
            if (calendario.getDayOfMonth() < 10) {
                concatenaDataHorario += "0";
            }
            concatenaDataHorario += calendario.getDayOfMonth() + "/";

            if (calendario.getMonthValue() < 10) {
                concatenaDataHorario += "0";
            }
            concatenaDataHorario += calendario.getMonthValue() + "/";

            concatenaDataHorario += calendario.getYear() + " ";

            LocalDateTime horarioAtualizado = calendario.with(LocalTime.now());

            if (horarioAtualizado.getHour() < 10) {
                concatenaDataHorario += "0";
            }
            concatenaDataHorario += horarioAtualizado.getHour() + ":";

            if (horarioAtualizado.getMinute() < 10) {
                concatenaDataHorario += "0";
            }
            concatenaDataHorario += horarioAtualizado.getMinute() + ":";

            if (horarioAtualizado.getSecond() < 10) {
                concatenaDataHorario += "0";
            }
            concatenaDataHorario += horarioAtualizado.getSecond();
        }

        this.dataModificacao = concatenaDataHorario;
    }
    /* 
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
            if (this.getDataModificacao() != null) {
                m += " e modificado no dia: " + this.getDataModificacao();
            }            
        m += "\n------------------------------------------------------------------\n";
        return m;
    }*/
}
