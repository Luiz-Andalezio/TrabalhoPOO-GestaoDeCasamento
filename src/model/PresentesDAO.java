package model;

public class PresentesDAO {

    Presentes[] presentes = new Presentes[10];

    public PresentesDAO() {

        Presentes presente1 = new Presentes();
        presente1.setNome("Geladeira");
        presente1.setTipo("Eletrodoméstico");
        presente1.setValor(1500);
        presente1.setDataCriacao();

        Presentes presente2 = new Presentes();
        presente2.setNome("Máquina de Lavar");
        presente2.setTipo("Eletrodoméstico");
        presente2.setValor(1200);
        presente2.setDataCriacao();

        Presentes presente3 = new Presentes();
        presente3.setNome("Micro-ondas");
        presente3.setTipo("Eletrodoméstico");
        presente3.setValor(600);
        presente3.setDataCriacao();

        Presentes presente4 = new Presentes();
        presente4.setNome("Conjunto de Panelas");
        presente4.setTipo("Cozinha");
        presente4.setValor(300);
        presente4.setDataCriacao();

        /*
        Presentes presente5 = new Presentes();
        presente5.setNome("Jogo de Cama");
        presente5.setTipo("Roupa de Cama");
        presente5.setValor(200);
        presente5.setDataCriacao();

        Presentes presente6 = new Presentes();
        presente6.setNome("TV 42 polegadas");
        presente6.setTipo("Eletrônico");
        presente6.setValor(1800);
        presente6.setDataCriacao();

        Presentes presente7 = new Presentes();
        presente7.setNome("Aparelho de Jantar");
        presente7.setTipo("Mesa");
        presente7.setValor(400);
        presente7.setDataCriacao();

        Presentes presente8 = new Presentes();
        presente8.setNome("Cafeteira");
        presente8.setTipo("Eletrodoméstico");
        presente8.setValor(250);
        presente8.setDataCriacao();

        Presentes presente9 = new Presentes();
        presente9.setNome("Ventilador");
        presente9.setTipo("Eletrodoméstico");
        presente9.setValor(150);
        presente9.setDataCriacao();

        Presentes presente10 = new Presentes();
        presente10.setNome("Torradeira");
        presente10.setTipo("Eletrodoméstico");
        presente10.setValor(100);
        presente10.setDataCriacao();
        */

        presentes[0] = presente1;
        presentes[1] = presente2;
        presentes[2] = presente3;
        presentes[3] = presente4;/* 
        presentes[4] = presente5;
        presentes[5] = presente6;
        presentes[6] = presente7;
        presentes[7] = presente8;
        presentes[8] = presente9;
        presentes[9] = presente10;*/
    }

    public Presentes registrarPresente(String nome, String tipo, double valor) {
        Presentes p = new Presentes();
        p.setNome(nome);
        p.setTipo(tipo);
        p.setValor(valor);
        p.setDataCriacao();

        for (int v = 0; v < presentes.length; v++) {
            if (presentes[v] == null) {
                presentes[v] = p;
                return p;
            }
        }
        return null;
    }

    public boolean atualizaPresente(String nomeAtt, String tipoAtt, double valorAtt, int id) {
        int i = 0;
        while (presentes[i] != null && presentes[i].getId() != id || presentes[i] == null) {
            i++;
        }
        //equals() with null check (temary) - Same shit of: (presentes[i].getNome() != nome)
        if (presentes[i] != null && presentes[i].getId() == id) {
            if (!nomeAtt.equals("")) {
                presentes[i].setNome(nomeAtt);
            }
            if (!tipoAtt.equals("")) {
                presentes[i].setTipo(tipoAtt);
            }
            presentes[i].setValor(valorAtt);
            presentes[i].setDataModificacao();
            return true;
        }
        return false;
    }

    public void excluirPresente(int id) {
        int i = 0;
        while (presentes[i] != null && presentes[i].getId() != id || presentes[i] == null) {
            i++;
        }

        if (presentes[i] != null && presentes[i].getId() == id) {
            presentes[i] = null;
        }
    }

    public boolean verificaPresente(int id) {
        int i = 0;
        while (presentes[i] != null && presentes[i].getId() != id || presentes[i] == null) {
            i++;
        }

        if (presentes[i] != null && presentes[i].getId() == id) {
            if (presentes[i].getNomeComprador() != null) {
                return true;
            }
        }
        return false;
    }

    public boolean compraPresente(String nomeComprador, int id) {
        int i = 0;
        while (presentes[i] != null && presentes[i].getId() != id || presentes[i] == null) {
            i++;
        }

        if (presentes[i] != null && presentes[i].getId() == id) {
            presentes[i].setNomeComprador(nomeComprador);
            presentes[i].setDataModificacao();
            return true;
        }
        return false;
    }

    public String verPresentesComprados() {
        String m = "";
        for (int i = 0; i < presentes.length; i++) {
            if (presentes[i] != null && presentes[i].getNomeComprador() != null) {
                m += presentes[i].toString() + "\n";
            }
        }
        return m;
    }

    /*
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
     */
    public String verPresentesConvidado(/*Usuario usuarioLogado*/) {
        String m = "";
        for (int i = 0; i < presentes.length; i++) {
            if (presentes[i] != null) {
                m += presentes[i].toString(/*usuarioLogado*/) + "\n";
            }
        }
        return m;
    }

    public Presentes verPresentesConvidado(int id) {
        return presentes[id - 1];
    }

    public String verPresentesAdmin(Usuario usuarioLogado) {
        String m = "";
        for (int i = 0; i < presentes.length; i++) {
            if (presentes[i] != null) {
                m += presentes[i].toString(usuarioLogado) + "\n";
            }
        }
        return m;
    }
}
