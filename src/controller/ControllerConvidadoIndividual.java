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
                    // Gerar convites
                    String novoNome = JOptionPane.showInputDialog("\n Informe o nome do(a) convidado(a): ");
                    String novoParentesco = JOptionPane.showInputDialog("\nInforme o parentesco de " + novoNome);
                    String novoTelefone = JOptionPane.showInputDialog("\nInforme o telefone de " + novoNome);
                    String novoNascimento = JOptionPane.showInputDialog("\nInforme a data de nascimento de " + novoNome);

                    if (!"".equals(novoNome) || !"".equals(novoParentesco) || !"".equals(novoTelefone) || !"".equals(novoNascimento)) {
                        pessoa = pessoadao.criarPessoa(novoNome, novoTelefone, novoNascimento);
                        conviteindividualdao.recebePessoa(pessoa, novoParentesco);
                        JOptionPane.showMessageDialog(null, conviteindividualdao.verConvidados());
                    } else {
                        JOptionPane.showMessageDialog(null, "Nenhum dado enviado: convite não gerado...");
                    }
                    break;

                case 2:
                    // Editar convites individuais
                    String s = conviteindividualdao.verConvidados();
                    if (!"".equals(s)) {
                        int id = Integer.parseInt(JOptionPane.showInputDialog(s + "\nInforme o ID do convite da pessoa que deseja editar: \n\n0- Voltar"));
                        if (id != 0) {
                            int veredito = JOptionPane.showConfirmDialog(null, "Deseja mesmo editar a pessoa do convite abaixo?\n\n" + conviteindividualdao.verConvite(id), "Confirmar Edição", JOptionPane.YES_NO_OPTION);
                            if (veredito == JOptionPane.YES_OPTION) {
                                String nomeAtt = JOptionPane.showInputDialog("Informe o nome a ser atualizado: ");
                                String parentescoAtt = JOptionPane.showInputDialog("\nInforme o parentesco a ser atualizado: ");
                                String telefoneAtt = JOptionPane.showInputDialog("\nInforme o telefone a ser atualizado: ");
                                String nascimentoAtt = JOptionPane.showInputDialog("\nInforme a data de nascimento a ser atualizada: ");

                                if (!"".equals(nomeAtt) || !"".equals(parentescoAtt) || !"".equals(telefoneAtt) || !"".equals(nascimentoAtt)) {
                                    m = new StringBuilder("Convite atualizado com sucesso!\n\nAntes: \n" + conviteindividualdao.verConvite(id));
                                    conviteindividualdao.atualizaConviteIndividual(nomeAtt, telefoneAtt, nascimentoAtt, parentescoAtt, id);
                                    m.append("\nAgora: \n").append(conviteindividualdao.verConvite(id));
                                    JOptionPane.showMessageDialog(null, m);
                                } else {
                                    JOptionPane.showMessageDialog(null, "Nenhum dado enviado: atualizações não foram realizadas...");
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "Edição não sucedida...");
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Ainda não há convites feitos.");
                    }
                    break;

                case 3:
                    // Exibir convites individuais
                    s = conviteindividualdao.verConvidados();
                    if ("".equals(s)) {
                        s = "Ainda não há convites feitos.";
                    }
                    JOptionPane.showMessageDialog(null, s);
                    break;

                case 4:
                    // Desfazer convites
                    s = conviteindividualdao.verConvidados();
                    if (!"".equals(s)) {
                        int id = Integer.parseInt(JOptionPane.showInputDialog(s + "\nInforme o ID do convite a ser desfeito: \n\n0- Voltar"));
                        if (id != 0) {
                            int veredito = JOptionPane.showConfirmDialog(null, "Deseja mesmo desconvidar a pessoa do convite abaixo?\n\n" + conviteindividualdao.verConvite(id), "Confirmar Exclusão", JOptionPane.YES_NO_OPTION);
                            if (veredito == JOptionPane.YES_OPTION) {
                                JOptionPane.showMessageDialog(null, "Convite desfeito com sucesso!\n\n" + conviteindividualdao.verConvite(id));
                                conviteindividualdao.desfazerConviteIndividual(id);
                            } else {
                                JOptionPane.showMessageDialog(null, "Exclusão não sucedida...");
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Ainda não há convites feitos.");
                    }
                    break;

                case 0:
                    // Voltar
                    menuPessoaOpc = -1;
                    break;

                default:
                    menuPessoaOpc = -1;
                    break;
            }
        }
    }
}
