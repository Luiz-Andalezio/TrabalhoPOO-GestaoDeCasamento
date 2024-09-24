package controller;

import javax.swing.JOptionPane;
import model.ConvidadoIndividualDAO;
import model.Pessoa;
import model.PessoaDAO;
import model.Usuario;
import view.GUI;

public class ControllerConvidadoIndividual {

    public void controllerCrudConvidado(GUI gui, Usuario usuarioLogado, PessoaDAO pessoadao, Pessoa pessoa, ConvidadoIndividualDAO conviteindividualdao) {
        StringBuilder m;
        int menuPessoaOpc = 0;

        while (menuPessoaOpc != -1) {
            menuPessoaOpc = gui.crudConvIndividual(usuarioLogado);
            System.out.println(menuPessoaOpc);
            switch (menuPessoaOpc) {
                case 1:
                    String novoNome = JOptionPane.showInputDialog("\n Informe o nome do(a) convidado(a): ");

                    String novoParentesco = JOptionPane.showInputDialog("\nInforme o parentesco de " + novoNome);

                    String novoTelefone = JOptionPane.showInputDialog("\nInforme o telefone de " + novoNome);

                    String novoNascimento = JOptionPane.showInputDialog("\nInforme a data de nascimendo de " + novoNome);

                    if ("".equals(novoNome) && "".equals(novoParentesco) && "".equals(novoNascimento) && "".equals(novoNascimento)) {
                        JOptionPane.showMessageDialog(null, "Nenhum dado enviado: convite não gerado.");
                        break;
                    }

                    pessoa = pessoadao.criarPessoa(novoNome, novoTelefone, novoNascimento);
                    conviteindividualdao.recebePessoa(pessoa, novoParentesco);

                    JOptionPane.showMessageDialog(null, conviteindividualdao.verConvidados());
                    break;

                case 2:
                    String s;
                    s = conviteindividualdao.verConvidados();
                    int id;
                    if ("".equals(s)) {
                        s = "Ainda não há convites feitos.";
                        JOptionPane.showMessageDialog(null, s);
                        break;
                    } else {
                        id = Integer.parseInt(JOptionPane.showInputDialog(s + "\nInforme o ID do convite da pessoa que deseja editar: " + "\n\n0- Voltar"));
                    }

                    if (id == 0) {
                        break;
                    }

                    int veredito = Integer.parseInt(JOptionPane.showInputDialog("Deseja mesmo editar a pessoa do convite abaixo?\n\n" + conviteindividualdao.verConvite(id) + "\n\n1- Sim.\n2- Não.\n\n0- Sair."));

                    if (veredito == 1) {
                        m = new StringBuilder("Informe o nome a ser atualizado: ");
                        String nomeAtt = JOptionPane.showInputDialog(m);

                        m = new StringBuilder("\nInforme o parentesco a ser atualizado: ");
                        String parentescoAtt = JOptionPane.showInputDialog(m);

                        m = new StringBuilder("\nInforme o telefone a ser atualizado: ");
                        String telefoneAtt = JOptionPane.showInputDialog(m);

                        m = new StringBuilder("\nInforme a data de nascimento a ser atualizada: ");
                        String nascimentoAtt = JOptionPane.showInputDialog(m);

                        if ("".equals(nomeAtt) && "".equals(parentescoAtt) && "".equals(telefoneAtt) && "".equals(nascimentoAtt)) {
                            JOptionPane.showMessageDialog(null, "Nenhum dado enviado: atualizações não foram realizadas...");
                            break;
                        }

                        m = new StringBuilder("Convite abaixo atualizado com sucesso!\n\nAntes: \n" + conviteindividualdao.verConvite(id));

                        conviteindividualdao.atualizaConviteIndividual(nomeAtt, telefoneAtt, nascimentoAtt, parentescoAtt, id);

                        m.append("\nDepois: \n").append(conviteindividualdao.verConvite(id));

                        JOptionPane.showMessageDialog(null, m);
                        break;
                    }
                    if (veredito == 2) {
                        JOptionPane.showMessageDialog(null, "Edição não sucedida...");
                        break;
                    }
                    if (veredito == 0) {
                        break;
                    }

                case 3:
                    s = conviteindividualdao.verConvidados();
                    if ("".equals(s)) {
                        s += "Ainda não há convites feitos.";

                    }
                    JOptionPane.showMessageDialog(null, s);
                    break;

                case 4:
                    s = conviteindividualdao.verConvidados();
                    if ("".equals(s)) {
                        s = "Ainda não há convites feitos.";
                        JOptionPane.showMessageDialog(null, s);
                        break;
                    } else {
                        id = Integer.parseInt(JOptionPane.showInputDialog(s + "\nInforme o ID do convite a ser desfeito: " + "\n\n0- Voltar"));
                    }

                    if (id == 0) {
                        break;
                    }

                    veredito = Integer.parseInt(JOptionPane.showInputDialog("Deseja mesmo desconvidar a pessoa do convite abaixo?\n\n" + conviteindividualdao.verConvite(id) + "\n\n1- Sim.\n2- Não.\n\n0- Sair."));

                    if (veredito == 1) {
                        JOptionPane.showMessageDialog(null, "Convite da pessoa abaixo desfeito com sucesso!\n\n" + conviteindividualdao.verConvite(id));
                        conviteindividualdao.desfazerConviteIndividual(id);
                        //pessoadao.excluirPessoa(id);
                    }
                    if (veredito == 2) {
                        JOptionPane.showMessageDialog(null, "Exclusão não sucedida...");
                        break;
                    }
                    if (veredito == 0) {
                        break;
                    }
                    break;

                case 0:
                    menuPessoaOpc = -1;
                    break;

                default:
                    menuPessoaOpc = -1;
                    break;
            }
        }
    }
}
