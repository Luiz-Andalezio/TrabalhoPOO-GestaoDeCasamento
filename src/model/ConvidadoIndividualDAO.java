
package model;

public class ConvidadoIndividualDAO {

    ConvidadoIndividual[] convidados = new ConvidadoIndividual[100];

    public ConvidadoIndividualDAO() {
    }

    public boolean recebePessoa(Pessoa novaPessoa, String parentesco) {
        for (int i = 0; i < convidados.length; i++) {
            if (convidados[i] == null) {
                convidados[i] = new ConvidadoIndividual();
                convidados[i].setPessoa(novaPessoa);
                convidados[i].setParentesco(parentesco);
                convidados[i].setConfirmacao(false);
                convidados[i].setDataCriacao();
                return true;
            }
        }
        return false;
    }

    public boolean atualizaConviteIndividual(String nome, String telefone, String nascimento, String parentesco, int id) {
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
            convidados[i].getPessoa().setDataModificacao();
            convidados[i].setDataModificacao();
            return true;
        }
        return false;
    }

    /* 
    public boolean atualizaConviteIndividual(String parentesco, String nome) {
        int i = 0;
        while (convidados[i] != null && !convidados[i].getPessoa().getNome().equals(nome)) {
            i++;
        }

        if (convidados[i] != null && convidados[i].getPessoa().getNome().equals(nome)) {
            if (!parentesco.equals("")) {
                convidados[i].setParentesco(parentesco);
            }
            convidados[i].setDataModificacao();
            return true;
        }
        return false;
    }
    */

    /* 
    public boolean atualizaConviteIndividual(String parentesco, int id) {
        int i = 0;
        while (convidados[i] != null && convidados[i].getId() != id) {
            i++;
        }

        if (convidados[i] != null && convidados[i].getId() == id) {
            if (!parentesco.equals("")) {
                convidados[i].setParentesco(parentesco);
            }
            convidados[i].setDataModificacao();
            return true;
        }
        return false;
    }*/
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

    public ConvidadoIndividual verConvite(int id) {
        return convidados[id - 1];
    }

    public String verConvite(String nome) {
        String m = "";
        for (int i = 0; i < convidados.length; i++) {
            if (convidados[i].getPessoa().getNome().equals(nome)) {
                m += convidados[i].toString() + "\n";
            }
        }
        return m;
    }

    /* 
    public String verConvidados() {
        String m = "";
        for (ConvidadoIndividual convidadoindividual : convidados) {
            if (convidadoindividual != null) {
                m += convidadoindividual.toString();
            }
            return m;
        }
        return null;
    }
    */
    public ConvidadoIndividual retornaConviteIndividual(int id) {
        return convidados[id-1];
    }
}
