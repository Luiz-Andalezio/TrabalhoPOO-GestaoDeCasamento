package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ConvidadoFamiliaDAO {

    ConvidadoFamilia[] convitesFamilia = new ConvidadoFamilia[100];

    public ConvidadoFamiliaDAO() {
    }

    public ConvidadoFamilia convidaFamilia(String novoNomeDaFamilia, Evento evento) {
        ConvidadoFamilia cf = new ConvidadoFamilia();
        cf.setNomeDaFamilia(novoNomeDaFamilia);
        cf.setAcesso(gerarAcesso(evento));
        cf.setDataCriacao();

        for (int i = 0; i < convitesFamilia.length; i++) {
            if (convitesFamilia[i] == null) {
                convitesFamilia[i] = cf;
                return cf;
            }
        }
        return null;
    }

    public void recebeConviteIndividual(int id, ConvidadoIndividual novoConviteIndividual) {
        convitesFamilia[id - 1].setConvidadoIndividual(id, novoConviteIndividual);
    }

    /*
    private String gerarAcesso(Evento evento, String diamesano) {
        String letrasAleatorias = gerarLetrasAleatorias(4);
        return evento.getPessoaNoivo().getNome() + evento.getPessoaNoiva().getNome() + diamesano + letrasAleatorias;
    }

    private String gerarLetrasAleatorias(int tamanho) {
        StringBuilder letras = new StringBuilder();
        String caracteres = "abcdefghijklmnopqrstuvwxyz";
        for (int i = 0; i < tamanho; i++) {
            int indice = (int) (Math.random() * caracteres.length());
            letras.append(caracteres.charAt(indice));
        }
        return letras.toString();
    }*/

    public String gerarAcesso(Evento evento) { 
        String diamesano = LocalDate.now().format(DateTimeFormatter.ofPattern("ddMMyyyy"));
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

    public void registrarPresenca(int id, int registro) {
        if (registro == 1) {
            convitesFamilia[id - 1].getConvidadoIndividual(id).setConfirmacao(true);
        } else if (registro == 2) {
            convitesFamilia[id - 1].getConvidadoIndividual(id).setConfirmacao(false);
        }
    }

    public boolean atualizaConviteFamilia(String nomeDaFamilia, String nome, String telefone, String nascimento, String parentesco, int id) {
        int i = 0;
        while (convitesFamilia[i] != null && convitesFamilia[i].getId() != id || convitesFamilia[i] == null) {
            i++;
        }

        if (convitesFamilia[i] != null && convitesFamilia[i].getId() == id) {
            if (!nomeDaFamilia.equals("")) {
                convitesFamilia[i].setNomeDaFamilia(nomeDaFamilia);
            }
            if (!nome.equals("")) {
                convitesFamilia[i].getConvidadoIndividual(id).getPessoa().setNome(nome);
            }
            if (!parentesco.equals("")) {
                convitesFamilia[i].getConvidadoIndividual(id).setParentesco(parentesco);
            }
            if (!telefone.equals("")) {
                convitesFamilia[i].getConvidadoIndividual(id).getPessoa().setTelefone(telefone);
            }
            if (!nascimento.equals("")) {
                convitesFamilia[i].getConvidadoIndividual(id).getPessoa().setNascimento(nascimento);
            }
            convitesFamilia[i].getConvidadoIndividual(id).getPessoa().setDataModificacao();
            convitesFamilia[i].getConvidadoIndividual(id).setDataModificacao();
            convitesFamilia[i].setDataModificacao();
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

    public String verConvitesFamilia() {
        String m = "";
        for (int i = 0; i < convitesFamilia.length; i++) {
            if (convitesFamilia[i] != null) {
                m += convitesFamilia[i] + "\n";
            }
        }
        return m;
    }

    public String verConvitesFamilia(int id) {
        return convitesFamilia[id - 1].toString();
    }

    public ConvidadoFamilia retornaConviteFamilia(int id) {
        return convitesFamilia[id - 1];
    }
}
