package model;

import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Locale;

public class Pagamento {
    private long id;
    private String descricao;
    private Fornecedor fornecedor;
    private String dataCriacao;
    private String dataModificacao;
    private static long incrementaId = 0;
    private static final Locale localeBR = new Locale("pt", "BR");
    private static final NumberFormat formatador = NumberFormat.getCurrencyInstance(localeBR);

    //GETTERS E SETTERS
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public void setDataCriacaoByString(String data) {
        this.dataCriacao = data;
    }

    public void setDataModificacaoByString(String data) {
        this.dataModificacao = data;
    }

    public String toStringParameter(Pagamento pagamento, PagamentoDAO pagamentodao, FornecedorDAO fornecedordao, ParcelaDAO parceladao, PessoaDAO pessoadao) {
        String m = "";
        m += "--------------- Pagamento de ID: " + this.id + " ---------------";

        m += "\nDescrição: " + this.descricao;

        if (this.fornecedor != null) {
            m += "\n\nDados do Fornecedor:";
            m += "\nNome: " + this.fornecedor.getNome();
            m += "\nCnpj: " + this.fornecedor.getCnpj();
            m += "\nTelefone: " + this.fornecedor.getTelefone();
            m += "\nValor total: " + formatador.format(this.fornecedor.getValorAPagar());
            m += "\nEstado: ";
            if (this.fornecedor.getEstado() == true) {
                m += "Todas as parcelas pagas!\n\n";
            } else {
                m += "Faltam parcelas à pagar.\n\n";
            }
            List<Parcela> parcelas = parceladao.buscarParcelasPorPagamentoToList(this.id, pagamentodao, fornecedordao, pessoadao);
            for (Parcela parcela : parcelas) {
                if (parcela.getPagamento().getId() == this.id) {
                    m += parcela.toString() + "\n";
                }
            }
        }
        m += "Registrado no dia: " + this.getDataCriacao();
        if (this.getDataModificacao() != null) {
            m += " e modificado no dia: " + this.getDataModificacao();
        }
        m += "\n------------------------------------------------------------------\n\n";
        return m;
    }
}
