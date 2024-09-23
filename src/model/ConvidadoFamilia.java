package model;

import java.time.LocalDate;

public class ConvidadoFamilia {

    public ConvidadoFamilia() {
    }
    private long id;
    private String nomeDaFamilia;
    private ConvidadoIndividual[] convitesIndividuais = new ConvidadoIndividual[100];
    private String acesso;
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

    public String getNomeDaFamilia() {
        return nomeDaFamilia;
    }

    public void setNomeDaFamilia(String nomeDaFamilia) {
        this.nomeDaFamilia = nomeDaFamilia;
    }

    public ConvidadoIndividual getConvidadoIndividual(int id) {
        return convitesIndividuais[id - 1];
    }

    public void setConvidadoIndividual(int id, ConvidadoIndividual conviteIndividual) {
        this.convitesIndividuais[id - 1] = conviteIndividual;
    }

    public String getAcesso() {
        return acesso;
    }

    public void setAcesso(String acesso) {
        this.acesso = acesso;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao() {
        this.dataCriacao = LocalDate.now();
        this.id = ++ConvidadoFamilia.incrementaId;
    }

    public LocalDate getDataModificacao() {
        return dataModificacao;
    }

    public void setDataModificacao() {
        this.dataModificacao = LocalDate.now();
    }

    @Override
    public String toString() {
        String m = "";
        m += "--- Convite Família de ID: " + this.id + " ---\n";
        m += "Codigo de confirmação: " + this.acesso + "\n\n";
        m += "Convidados:";
        for (ConvidadoIndividual convite : convitesIndividuais) {
            if (convite != null) {
                m += "\nNome: " + convite.getPessoa().getNome();
                m += "\nTelefone:" + convite.getPessoa().getTelefone();
                m += "\nConfirmação:";
                if (convite.isConfirmacao() == true) {
                    m += "Confirmado";
                } else {
                    m += "Não confirmado";
                }
                m += "\n";
            }
        }
        m += "\nConvite Família feito no dia: " + this.getDataCriacao();
        if (this.getDataModificacao() != null) {
            m += " e modificado no dia: " + this.getDataModificacao();
        }
        m += "\n--------------------------------\n\n";
        return m;
    }
}
