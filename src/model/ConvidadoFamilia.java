package model;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class ConvidadoFamilia {

    private long id;
    private String nomeDaFamilia;
    private ConvidadoIndividual[] convitesIndividuais = new ConvidadoIndividual[100];
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

    public ConvidadoIndividual getConvidadoIndividual(int id) {
        return convitesIndividuais[id - 1];
    }

    public ConvidadoIndividual getConvidadoIndividualVetor(int i) {
        return convitesIndividuais[i];
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

    @Override
    public String toString() {
        String m = "";
        m += "-------------- Convite Família de ID: " + this.id + " --------------\n";
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
        if (this.getDataModificacao() != null) {
            m += " e modificado no dia: " + this.getDataModificacao();
        }
        m += "\n----------------------------------------------------------------\n\n";
        return m;
    }
}
