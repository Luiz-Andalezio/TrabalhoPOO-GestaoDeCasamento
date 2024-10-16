
package model;

import java.time.LocalDateTime;

public class ConvidadoIndividualDAO {

    ConvidadoIndividual[] convidados = new ConvidadoIndividual[100];

    public ConvidadoIndividualDAO(PessoaDAO pessoadao, LocalDateTime dataInicial) {
        ConvidadoIndividual ci1 = new ConvidadoIndividual();
        ci1.setPessoa(pessoadao.retornaPessoa(5));
        ci1.setParentesco("Tia");
        ci1.setConfirmacao(false);
        ci1.setDataCriacao(dataInicial);
        convidados[0] = ci1;

        ConvidadoIndividual ci2 = new ConvidadoIndividual();
        ci2.setPessoa(pessoadao.retornaPessoa(6));
        ci2.setParentesco("Avô");
        ci2.setConfirmacao(false);
        ci2.setDataCriacao(dataInicial);
        convidados[1] = ci2;

        ConvidadoIndividual ci3 = new ConvidadoIndividual();
        ci3.setPessoa(pessoadao.retornaPessoa(7));
        ci3.setParentesco("Bisavô");
        ci3.setConfirmacao(false);
        ci3.setDataCriacao(dataInicial);
        convidados[2] = ci3;
    }

    public boolean recebePessoa(Pessoa novaPessoa, String parentesco, LocalDateTime calendario) {
        for (int i = 0; i < convidados.length; i++) {
            if (convidados[i] == null) {
                convidados[i] = new ConvidadoIndividual();
                convidados[i].setPessoa(novaPessoa);
                convidados[i].setParentesco(parentesco);
                convidados[i].setConfirmacao(false);
                convidados[i].setDataCriacao(calendario);
                return true;
            }
        }
        return false;
    }

    public boolean atualizaConviteIndividual(String nome, String telefone, String nascimento, String parentesco, int id, LocalDateTime calendario) {
        int i = 0;
        while (convidados[i] != null && convidados[i].getId() != id || convidados[i] == null) {
            i++;
        }

        if (convidados[i] != null && convidados[i].getId() == id) {
            if (!nome.equals("")) {
                convidados[i].getPessoa().setNome(nome);
            }
            if (!parentesco.equals("")) {
                convidados[i].setParentesco(parentesco);
            }
            if (!telefone.equals("")) {
                convidados[i].getPessoa().setTelefone(telefone);
            }
            if (!nascimento.equals("")) {
                convidados[i].getPessoa().setNascimento(nascimento);
            }
            convidados[i].getPessoa().setDataModificacao(calendario);
            convidados[i].setDataModificacao(calendario);
            return true;
        }
        return false;
    }

    public String verConvidados() {
        String m = "";
        for (int i = 0; i < convidados.length; i++) {
            if (convidados[i] != null) {
                m += convidados[i].toString() + "\n";
            }
        }
        return m;
    }

    public void desfazerConviteIndividual(int id) {
        int i = 0;
        while (convidados[i] != null && convidados[i].getId() != id || convidados[i] == null) {
            i++;
        }

        if (convidados[i] != null && convidados[i].getId() == id) {
            convidados[i] = null;
        }
    }

    public ConvidadoIndividual verConviteIndividualByPessoaID(int id) {
        int i = 0;
        while (convidados[i] != null && convidados[i].getPessoa().getId() != id || convidados[i] == null) {
            i++;
        }

        if (convidados[i] != null && convidados[i].getPessoa().getId() == id) {
            return convidados[i];
        }
        return null;
    }
    
    public ConvidadoIndividual verConviteIndividualByID(int id) {
        int i = 0;
        while (convidados[i] != null && convidados[i].getId() != id || convidados[i] == null) {
            i++;
        }

        if (convidados[i] != null && convidados[i].getId() == id) {
            return convidados[i];
        }
        return null;
    }

    public ConvidadoIndividual retornaConviteIndividualVetor(int i) {
        return convidados[i];
    }
}
