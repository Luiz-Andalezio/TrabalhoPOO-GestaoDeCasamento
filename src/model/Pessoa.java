package model;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Pessoa {

    private long id;
    private String nome;
    private String nascimento;
    private String telefone;
    private String dataCriacao;
    private String dataModificacao;
    //private static long incrementaId = 0;

    public String getTelefone() {
        return this.telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNascimento() {
        return this.nascimento;
    }

    public void setNascimento(String nascimento) {
        if (nascimento == null) {
            this.nascimento = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        } else {
            this.nascimento = nascimento;
        }
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
        //this.id = ++Pessoa.incrementaId;
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
        m += "ID: " + this.id + "\n";
        m += "Nome: " + this.nome + "\n";
        m += "Nascimento:" + this.nascimento + "\n";
        /*
        if (this.getNascimento() != null) {
            try {
                LocalDate nascimento = LocalDate.parse(this.getNascimento(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                m += "\n\nData e Horário: " + nascimento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            } catch (Exception e) {
                m += "\n\nProblemas com a exibição da data encontrados. Contate um administrador.";
            }
        } else {
            m += "\n\nNão informado.";
        } */      
        m += "Telefone: " + this.telefone + "\n";
        m += "Criado no dia: " + this.getDataCriacao() + "\n";
        if (!"".equals(getDataModificacao()) || this.nascimento != null) {
            m += "Modificado no dia: " + this.getDataModificacao() + "\n";
        }
        return m;
    }
}
