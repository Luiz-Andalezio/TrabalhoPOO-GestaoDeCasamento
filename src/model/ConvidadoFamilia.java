package model;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class ConvidadoFamilia {

    private long id;
    private String nomeDaFamilia;
    private String acesso;
    private String dataCriacao;
    private String dataModificacao;
    private static long incrementaId = 0;

    //GETTERS E SETTERS
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNomeDaFamilia() {
        return nomeDaFamilia;
    }

    public void setNomeDaFamilia(String nomeDaFamilia) {
        this.nomeDaFamilia = nomeDaFamilia;
    }

    public String getAcesso() {
        return acesso;
    }

    public void setAcesso(String acesso) {
        this.acesso = acesso;
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
        this.id = ++ConvidadoFamilia.incrementaId;
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

    @Override
    public String toString() {
        String m = "";
        if (!"Fornecedores".equals(this.nomeDaFamilia)) {
            m += "------------------ Convite Família de ID: " + this.id + " ------------------\n";
            m += "Nome da família: " + this.nomeDaFamilia + "\n";
            m += "Codigo de confirmação: " + this.acesso + "\n\n";
        } else {
            m += "---------------- Convite Fornecedor de ID: " + this.id + " ----------------\n";
        }
        if (!"Fornecedores".equals(nomeDaFamilia)) {
            m += "\nConvite Família feito no dia: " + this.getDataCriacao();
        } else {
            m += "\n\nConvite Fornecedor feito no dia: " + this.getDataCriacao();
        }
        if (this.getDataModificacao() != null) {
            m += " e modificado no dia: " + this.getDataModificacao();
        }
        if (!"Fornecedores".equals(this.nomeDaFamilia)) {
            m += "\n------------------------------------------------------------------------\n\n";
        } else {
            m += "\n------------------------------------------------------------------------\n\n";
        }
        return m;
    }
}
