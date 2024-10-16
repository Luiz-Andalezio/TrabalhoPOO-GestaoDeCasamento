package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ConvidadoFamiliaDAO {

    ConvidadoFamilia[] convitesFamilia = new ConvidadoFamilia[100];

    public ConvidadoFamiliaDAO(ConvidadoIndividualDAO conviteindividualdao, Evento evento, LocalDateTime calendario) {
        ConvidadoFamilia cf1 = new ConvidadoFamilia();
        cf1.setNomeDaFamilia("Dantas");
        cf1.setConvidadoIndividualVetor(0, conviteindividualdao.retornaConviteIndividualVetor(0));
        cf1.setConvidadoIndividualVetor(1, conviteindividualdao.retornaConviteIndividualVetor(2));
        cf1.setAcesso(/*gerarAcesso(evento)*/"123");
        cf1.setDataCriacao(calendario);
        convitesFamilia[0] = cf1;

        ConvidadoFamilia cf2 = new ConvidadoFamilia();
        cf2.setNomeDaFamilia("Ribeiro");
        cf2.setAcesso(gerarAcesso(evento, calendario));
        cf2.setDataCriacao(calendario);
        convitesFamilia[1] = cf2;
    }

    public ConvidadoFamilia convidaFamilia(String novoNomeDaFamilia, Evento evento, LocalDateTime calendario) {
        ConvidadoFamilia cf = new ConvidadoFamilia();
        cf.setNomeDaFamilia(novoNomeDaFamilia);
        cf.setAcesso(gerarAcesso(evento, calendario));
        cf.setDataCriacao(calendario);

        for (int i = 0; i < convitesFamilia.length; i++) {
            if (convitesFamilia[i] == null) {
                convitesFamilia[i] = cf;
                return cf;
            }
        }
        return null;
    }

    public String atualizaAcesso(int id, Evento evento, ConvidadoFamilia conviteFamilia, LocalDateTime calendario) {
        conviteFamilia = convitesFamilia[id - 1];
        conviteFamilia.setAcesso(gerarAcesso(evento, calendario));
        conviteFamilia.setDataModificacao(calendario);
        return conviteFamilia.getAcesso();
    }

    public void recebeConviteIndividual(int id, int id2, ConvidadoIndividual novoConviteIndividual) {
        convitesFamilia[id - 1].setConvidadoIndividual(id2, novoConviteIndividual);
    }

    private String gerarAcesso(Evento evento, LocalDateTime calendario) {
        String diamesano = calendario.format(DateTimeFormatter.ofPattern("ddMMyyyy"));
        String caracteres = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder letrasAleatorias = new StringBuilder();

        for (int i = 0; i < 4; i++) {
            //(int) = "casting". Converte o valor resultante de um cálculo ou expressão para o tipo int.
            int indice = (int) (Math.random() * caracteres.length());
            letrasAleatorias.append(caracteres.charAt(indice));
        }

        String primeiroNomeNoivo = evento.getPessoaNoivo().getNome().split(" ")[0];
        String primeiroNomeNoiva = evento.getPessoaNoiva().getNome().split(" ")[0];

        return primeiroNomeNoivo + primeiroNomeNoiva + diamesano + letrasAleatorias.toString();
    }

    public boolean atualizaNomeConviteFamilia(int id, String nomeDaFamilia, LocalDateTime calendario) {
        int i = 0;
        while (convitesFamilia[i] != null && convitesFamilia[i].getId() != id || convitesFamilia[i] == null) {
            i++;
        }

        if (convitesFamilia[i] != null && convitesFamilia[i].getId() == id) {
            if (!nomeDaFamilia.equals("")) {
                convitesFamilia[i].setNomeDaFamilia(nomeDaFamilia);
            }
            convitesFamilia[i].setDataModificacao(calendario);
            return true;
        }
        return false;
    }

    public boolean atualizaPessoasConviteFamilia(int id, int id2, String nome, String telefone, String nascimento, String parentesco, LocalDateTime calendario) {
        int i = 0;
        while (convitesFamilia[i] != null && convitesFamilia[i].getId() != id || convitesFamilia[i] == null) {
            i++;
        }

        if (convitesFamilia[i] != null && convitesFamilia[i].getId() == id) {
            if (!nome.equals("")) {
                convitesFamilia[i].getConviteIndividualByID(id2).getPessoa().setNome(nome);
            }
            if (!parentesco.equals("")) {
                convitesFamilia[i].getConviteIndividualByID(id2).setParentesco(parentesco);
            }
            if (!telefone.equals("")) {
                convitesFamilia[i].getConviteIndividualByID(id2).getPessoa().setTelefone(telefone);
            }
            if (!nascimento.equals("")) {
                convitesFamilia[i].getConviteIndividualByID(id2).getPessoa().setNascimento(nascimento);
            }
            convitesFamilia[i].getConviteIndividualByID(id2).getPessoa().setDataModificacao(calendario);
            convitesFamilia[i].getConviteIndividualByID(id2).setDataModificacao(calendario);
            convitesFamilia[i].setDataModificacao(calendario);
            return true;
        }
        return false;
    }

    public boolean excluirConviteIndividualConviteFamilia(int id, int id2, LocalDateTime calendario) {
        int i = 0;
        while (convitesFamilia[i] != null && convitesFamilia[i].getId() != id || convitesFamilia[i] == null) {
            i++;
        }

        if (convitesFamilia[i] != null && convitesFamilia[i].getId() == id) {
            convitesFamilia[i].setConvidadoIndividualByID(id2, null);
            convitesFamilia[i].setDataModificacao(calendario);
            return true;
        }
        return false;
    }

    public void desfazerConviteFamilia(int id) {
        int i = 0;
        while (convitesFamilia[i] != null && convitesFamilia[i].getId() != id || convitesFamilia[i] == null) {
            i++;
        }

        if (convitesFamilia[i] != null && convitesFamilia[i].getId() == id) {
            convitesFamilia[i] = null;
        }
    }

    public boolean registrarPresenca(int id, int id2, boolean registro, LocalDateTime calendario) {
        int i = 0;
        while (convitesFamilia[i] != null && convitesFamilia[i].getId() != id || convitesFamilia[i] == null) {
            i++;
        }

        if (convitesFamilia[i] != null && convitesFamilia[i].getId() == id) {
            if (registro != false) {
                convitesFamilia[i].getConviteIndividualByID(id2).setConfirmacao(registro);
                convitesFamilia[i].getConviteIndividualByID(id2).setDataModificacao(calendario);                
                convitesFamilia[i].setDataModificacao(calendario);                
                return true;
            }
        }
        return false;
    }

    public boolean registrarPresenca(ConvidadoFamilia conviteFamilia, int id2, boolean registro, LocalDateTime calendario) {
        int i = 0;
        while (convitesFamilia[i] != null && convitesFamilia[i] != conviteFamilia || convitesFamilia[i] == null) {
            i++;
        }

        if (convitesFamilia[i] != null && convitesFamilia[i] == conviteFamilia) {
            if (registro != false) {
                convitesFamilia[i].getConviteIndividualByID(id2).setConfirmacao(registro);
                convitesFamilia[i].getConviteIndividualByID(id2).setDataModificacao(calendario);
            }
            convitesFamilia[i].setDataModificacao(calendario);
            return true;
        }
        return false;
    }

    public String verConvitesFamilia() {
        String m = "";
        for (int i = 0; i < convitesFamilia.length; i++) {
            if (convitesFamilia[i] != null) {
                m += convitesFamilia[i] + "\n";
            }
        }
        return m;
    }

    public String verConviteFamilia(int id) {
        return convitesFamilia[id - 1].toString();
    }

    public ConvidadoFamilia retornaAcessoConviteFamilia(String acesso) {
        for (ConvidadoFamilia convitefamilia : convitesFamilia) {
            if (convitefamilia != null && convitefamilia.getAcesso().equals(acesso)) {
                return convitefamilia;
            }
        }
        return null;
    }

    public ConvidadoFamilia retornaConviteFamiliaByFamilia(ConvidadoFamilia conviteFamilia) {
        int i = 0;
        while (convitesFamilia[i] != null && convitesFamilia[i] != conviteFamilia || convitesFamilia[i] == null) {
            i++;
        }

        if (convitesFamilia[i] != null && convitesFamilia[i] == conviteFamilia) {
            return convitesFamilia[i];
        }
        return null;
    }

    public ConvidadoFamilia retornaConviteFamiliaByID(int id) {
        int i = 0;
        while (convitesFamilia[i] != null && convitesFamilia[i].getId() != id || convitesFamilia[i] == null) {
            i++;
        }

        if (convitesFamilia[i] != null && convitesFamilia[i].getId() == id) {
            return convitesFamilia[i];
        }
        return null;
    }

    public ConvidadoIndividual retornaConviteIndividualByIDifNotNull(ConvidadoFamilia conviteFamilia, int id) {
        int i = 0;

        if (id == 0){
            return null;
        }
        
        while (convitesFamilia[i] != null && convitesFamilia[i] != conviteFamilia || convitesFamilia[i] == null) {
            i++;
        }

        if (convitesFamilia[i] != null && convitesFamilia[i] == conviteFamilia) {
            int y = 0;
            while (convitesFamilia[i].getConvidadoIndividualVetor(y) != null && convitesFamilia[i].getConvidadoIndividualVetor(y).getId() != id || convitesFamilia[i].getConvidadoIndividualVetor(y) == null) {
                y++;
                if (y == 100) {
                    return null;
                }
            }

            if (convitesFamilia[i].getConvidadoIndividualVetor(y) != null && convitesFamilia[i].getConvidadoIndividualVetor(y).getId() == id) {
                return convitesFamilia[i].getConvidadoIndividualVetor(y);
            }
        }
        return null;
    }

    public ConvidadoIndividual retornaConviteIndividualByIDifNotNull(int id, int id2) {
        int i = 0;
        while (convitesFamilia[i] != null && convitesFamilia[i].getId() != id || convitesFamilia[i] == null) {
            i++;
        }

        if (convitesFamilia[i] != null && convitesFamilia[i].getId() == id) {
            int y = 0;
            while (convitesFamilia[i].getConvidadoIndividualVetor(y) != null && convitesFamilia[i].getConvidadoIndividualVetor(y).getId() != id2 || convitesFamilia[i].getConvidadoIndividualVetor(y) == null) {
                y++;
                if (y == 100) {
                    return null;
                }
            }

            if (convitesFamilia[i].getConvidadoIndividualVetor(y) != null && convitesFamilia[i].getConvidadoIndividualVetor(y).getId() == id2) {
                return convitesFamilia[i].getConvidadoIndividualVetor(y);
            }
        }
        return null;
    }
}
