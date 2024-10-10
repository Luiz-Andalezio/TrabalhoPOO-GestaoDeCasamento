package model;

import java.time.LocalDate;

public class ConvidadoFamilia {

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

    public ConvidadoIndividual getConviteIndividualByID(int id) {
        int i = 0;
        while (convitesIndividuais[i] != null && convitesIndividuais[i].getId() != id || convitesIndividuais[i] == null) {
            i++;
        }

        if (convitesIndividuais[i] != null && convitesIndividuais[i].getId() == id) {
            return convitesIndividuais[i];
        }
        return null;
    }

    public boolean getVetorConvidadosIndividuais() {
        int existe = 0;
        for (int i = 0; i < convitesIndividuais.length; ++i) {
            if (convitesIndividuais[i] != null) {
                ++existe;
            }
        }
        if (existe != 0) {
            return true;
        } else {
            return false;
        }
    }

    public void setConvidadoIndividual(int id, ConvidadoIndividual conviteIndividual) {
        this.convitesIndividuais[id - 1] = conviteIndividual;
    }

    public void setConvidadoIndividualByID(int id, ConvidadoIndividual conviteIndividual) {
        int i = 0;
        while (convitesIndividuais[i] != null && convitesIndividuais[i].getId() != id || convitesIndividuais[i] == null) {
            i++;
        }

        if (convitesIndividuais[i] != null && convitesIndividuais[i].getId() == id) {
            this.convitesIndividuais[i] = conviteIndividual;
        }
    }

    public void setConvidadoIndividualVetor(int i, ConvidadoIndividual conviteIndividual) {
        this.convitesIndividuais[i] = conviteIndividual;
    }

    public String getAcesso() {
        return acesso;
    }

    public void setAcesso(String acesso) {
        this.acesso = acesso;
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

    public void setDataCriacao() {
        this.dataCriacao = LocalDate.now();
        this.id = ++ConvidadoFamilia.incrementaId;
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
    
    public void setDataModificacao() {
        this.dataModificacao = LocalDate.now();
    }

    @Override
    public String toString() {
        String m = "";
        m += "--- Convite Família de ID: " + this.id + " ---\n";
        m += "Nome da família: " + this.nomeDaFamilia + "\n";
        m += "Codigo de confirmação: " + this.acesso + "\n\n";
        int existe = 0;
        for (int i = 0; i < convitesIndividuais.length; ++i) {
            if (convitesIndividuais[i] != null) {
                ++existe;
            }
        }
        if (existe == 0) {
            m += "Não há convidados nesta família.\n";
        } else {
            m += "Convidados:";
            for (ConvidadoIndividual convites : convitesIndividuais) {
                if (convites != null) {
                    m += "\nID do convite individual: " + convites.getId();
                    m += "\nNome: " + convites.getPessoa().getNome();
                    m += "\nTelefone: " + convites.getPessoa().getTelefone();
                    m += "\nPresença: ";
                    if (convites.isConfirmacao() == true) {
                        m += "Confirmada";
                    } else {
                        m += "Não confirmada";
                    }
                    m += "\n";
                }
            }
        }
        m += "\nConvite Família feito no dia: " + this.getDataCriacao();
        if (!"".equals(this.getDataModificacao())) {
            m += " e modificado no dia: " + this.getDataModificacao();
        }
        m += "\n--------------------------------\n\n";
        return m;
    }
}
