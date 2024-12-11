package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;

import data_base_connector.ConnectionFactory;

public class ConvidadoFamiliaDAO {

    public ConvidadoFamiliaDAO(ConvidadoIndividualDAO conviteindividualdao, Evento evento, LocalDateTime calendario) {
    }

    public ConvidadoFamilia adicionaParcelaBanco(ConvidadoFamilia elemento) {

        String sql = "insert into convidado_familia "
                + "(nome_familia,acesso,dataCriacao)"
                + " values (?,?,?)";

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, elemento.getNomeDaFamilia());
            stmt.setString(2, elemento.getNomeDaFamilia());
            stmt.setString(3, elemento.getDataCriacao());

            stmt.executeUpdate();

            // Recupera o ID gerado automaticamente
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    elemento.setId(rs.getLong(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        return elemento;
    }

    public Parcela atualizaParcelaBanco(Parcela elemento) {

        String sql = "update parcela set id_pagamento_fk = ?, id_pessoa_pagante_fk = ?, parcela = ?, parcela_data = ?, valor_parcela = ?, estado_pagamento = ?, dataModificacao = ? where id_parcela = ?";

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, elemento.getNomeDaFamilia());
            stmt.setString(2, elemento.getNomeDaFamilia());
            stmt.setString(3, elemento.getDataCriacao());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        return elemento;
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

    /*
    =>Exiba um relatório formatado com a lista total de convidados confirmados.
    Some o total de pessoas/pontos.
    Esta lista pode ser usada na portaria da festa para o controle dos convidados permitidos.
    Siga os critérios a seguir:
    1 - Crianças até 8 anos não contam.
    2 - Crianças de 9 a 13 anos contam como 50% do valor do adulto.
    3 – Pessoas com 14 anos ou mais contam como adulto.
    4 - Fornecedores contam como 50% do valor do adulto.
     */
    public boolean verificaListaConfirmados() {
        int i = 0;
        while (i < convitesFamilia.length) {
            if (convitesFamilia[i] != null) {
                int j = 0;
                while (j < convitesFamilia[i].getTamanhoVetorConvidadoIndividual()) {
                    if (convitesFamilia[i].getConvidadoIndividualVetor(j) != null && convitesFamilia[i].getConvidadoIndividualVetor(j).getConfirmacao() != false) {
                        return true;
                    }
                    j++;
                }
            }
            i++;
        }
        return false;
    }

    public int listaPontosTotais(LocalDateTime calendario) {
        int pontos = 0;

        int i = 0;
        while (i < convitesFamilia.length) {
            if (convitesFamilia[i] != null) {
                int j = 0;
                while (j < convitesFamilia[i].getTamanhoVetorConvidadoIndividual()) {
                    if (convitesFamilia[i].getConvidadoIndividualVetor(j) != null && convitesFamilia[i].getConvidadoIndividualVetor(j).getConfirmacao() != false) {
                        int ponto = 10;
                        if ("Fornecedores".equals(convitesFamilia[i].getNomeDaFamilia())) {
                            ponto = ponto / 2;
                        } else {
                            DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                            int idade = Period.between(LocalDate.parse(convitesFamilia[i].getConvidadoIndividualVetor(j).getPessoa().getNascimento(), formatador), calendario.toLocalDate()).getYears();
                            if (idade <= 8) {
                            } else if (idade >= 9 && idade <= 13) {
                                ponto = ponto / 2;
                                pontos = pontos + ponto;
                            } else if (idade >= 14) {
                                pontos = pontos + ponto;
                            }
                        }
                    }
                    j++;
                }
            }
            i++;
        }
        return pontos;
    }

    public String atualizaAcesso(int id, Evento evento, ConvidadoFamilia conviteFamilia, LocalDateTime calendario) {
        conviteFamilia = convitesFamilia[id - 1];
        conviteFamilia.setAcesso(gerarAcesso(evento, calendario));
        conviteFamilia.setDataModificacao(calendario);
        return conviteFamilia.getAcesso();
    }

    public boolean recebeConviteIndividual(int id, ConvidadoIndividual novoConviteIndividual) {
        int i = 0;
        while (convitesFamilia[i] != null && convitesFamilia[i].getId() != id || convitesFamilia[i] == null) {
            i++;
        }

        if (convitesFamilia[i] != null && convitesFamilia[i].getId() == id) {
                convitesFamilia[i].setConvidadoIndividualByID(novoConviteIndividual);
                return true;
        }
        return false;
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

    public boolean atualizaFornecedorConviteFamilia(int id, int id2, String nome, String telefone, LocalDateTime calendario) {
        int i = 0;
        while (convitesFamilia[i] != null && convitesFamilia[i].getId() != id || convitesFamilia[i] == null) {
            i++;
        }

        if (convitesFamilia[i] != null && convitesFamilia[i].getId() == id) {
            if (!nome.equals("")) {
                convitesFamilia[i].getConviteIndividualByID(id2).getPessoa().setNome(nome);
            }
            if (!telefone.equals("")) {
                convitesFamilia[i].getConviteIndividualByID(id2).getPessoa().setTelefone(telefone);
            }
            convitesFamilia[i].getConviteIndividualByID(id2).getPessoa().setDataModificacao(calendario);
            convitesFamilia[i].getConviteIndividualByID(id2).setDataModificacao(calendario);
            convitesFamilia[i].setDataModificacao(calendario);
            return true;
        }
        return false;
    }

    public boolean excluirPessoaConviteFamilia(int id, int id2) {
        int i = 0;
        while (convitesFamilia[i] != null && convitesFamilia[i].getId() != id || convitesFamilia[i] == null) {
            i++;
        }

        if (convitesFamilia[i] != null && convitesFamilia[i].getId() == id) {
            convitesFamilia[i].excluiConvidadoIndividualByID(id2);
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

    public String verConvitesFamiliaEFornecedor() {
        String m = "";
        for (int i = 0; i < convitesFamilia.length; i++) {
            if (convitesFamilia[i] != null) {
                m += convitesFamilia[i];
            }
        }
        return m;
    }

    public String verConvitesFamilia() {
        String m = "";
        for (int i = 0; i < convitesFamilia.length; i++) {
            if (convitesFamilia[i] != null) {
                if (!"Fornecedores".equals(convitesFamilia[i].getNomeDaFamilia())) {
                    m += convitesFamilia[i];
                }
            }
        }
        return m;
    }

    public ConvidadoFamilia retornaAcessoConviteFamilia(String acesso) {
        int i = 0;
        while (convitesFamilia[i] != null && convitesFamilia[i].getAcesso() == null || convitesFamilia[i] != null && !convitesFamilia[i].getAcesso().equals(acesso) || convitesFamilia[i] == null) {
            i++;
        }

        if (convitesFamilia[i] != null && convitesFamilia[i].getAcesso().equals(acesso)) {
            return convitesFamilia[i];
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

    public ConvidadoFamilia retornaConviteByID(int id) {
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

        if (id == 0) {
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

    public ConvidadoFamilia retornaConviteFamiliaVetor(int i) {
        return convitesFamilia[i];
    }

    public int retornaTamanhoVetorConvidadoFamilia() {
        int i = convitesFamilia.length;
        return i;
    }
}
