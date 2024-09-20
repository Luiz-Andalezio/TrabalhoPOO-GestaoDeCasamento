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

                    pessoa = pessoadao.criarPessoa(novoNome, novoTelefone, novoNascimento);
                    conviteindividualdao.recebePessoa(pessoa, novoParentesco);

                    JOptionPane.showMessageDialog(null, conviteindividualdao.verConvidados());
                    break;

                case 2:
                    int id = Integer.parseInt(JOptionPane.showInputDialog(conviteindividualdao.verConvidados() + "\nInforme o ID do convite da pessoa que deseja editar: " + "\n\n0- Voltar"));
                    if (id == 0) {
                        menuPessoaOpc = -1;
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
                        /*
                        if (nomeAtt == "" && telefoneAtt == "" && nascimentoAtt == ""){
                            JOptionPane.showMessageDialog(null, "Atualizações não foram realizadas...");
                            break;
                        }
                         */

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
                        menuPessoaOpc = -1;
                        break;
                    }

                case 3:
                    JOptionPane.showMessageDialog(null, conviteindividualdao.verConvidados());
                    break;

                case 4:
                    id = Integer.parseInt(JOptionPane.showInputDialog(conviteindividualdao.verConvidados() + "\nInforme o ID do convite a ser desfeito: " + "\n\n0- Voltar"));

                    if (id == 0) {
                        menuPessoaOpc = -1;
                        break;
                    }
                    veredito = Integer.parseInt(JOptionPane.showInputDialog("Deseja mesmo desconvidar a pessoa do convite abaixo?\n\n" + conviteindividualdao.verConvite(id) + "\n\n1- Sim.\n2- Não.\n\n0- Sair."));

                    if (veredito == 1) {
                        JOptionPane.showMessageDialog(null, "Convite da pessoa abaixo desfeito com sucesso!\n\n" + conviteindividualdao.verConvite(id));
                        conviteindividualdao.desfazerConvite(id);
                        //pessoadao.excluirPessoa(id);
                    }
                    if (veredito == 2) {
                        JOptionPane.showMessageDialog(null, "Exclusão não sucedida...");
                        break;
                    }
                    if (veredito == 0) {
                        menuPessoaOpc = -1;
                        break;
                    }
                    break;

                case 5:
                    menuPessoaOpc = -1;
                    break;

                case 0:
                    menuPessoaOpc = -1;
                    break;
                    
                default:
                    menuPessoaOpc = 0;
                    break;
            }
        }
    }
}
