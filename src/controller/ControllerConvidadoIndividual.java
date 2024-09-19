package controller;

import javax.swing.JOptionPane;
import model.PessoaDAO;
import model.Usuario;
import view.GUI;

public class ControllerConvidadoIndividual {

    public void controllerCrudConvidado(GUI gui, Usuario usuarioLogado, PessoaDAO pessoadao) {
        StringBuilder m;
        int menuPessoaOpc = 0;

        while (menuPessoaOpc != -1) {
            menuPessoaOpc = gui.crudConvIndividual(usuarioLogado);
            System.out.println(menuPessoaOpc);
            switch (menuPessoaOpc) {
                case 1:
                    m = new StringBuilder("\n Informe o nome do(a) convidado(a).");
                    String nome = JOptionPane.showInputDialog(m);

                    //m.append("\nInforme o telefone de " + nome);
                    m = new StringBuilder("\nInforme o telefone de ").append(nome);
                    String telefone = JOptionPane.showInputDialog(m);

                    m = new StringBuilder("\nInforme a data de nascimendo de ").append(nome);
                    String nascimento = JOptionPane.showInputDialog(m);

                    pessoadao.criarPessoa(nome, telefone, nascimento, pessoadao);

                    m = new StringBuilder(pessoadao.verConvidados());
                    JOptionPane.showMessageDialog(null, m);
                    break;

                case 2:
                    int id = Integer.parseInt(JOptionPane.showInputDialog(pessoadao.verConvidados() + "\nInforme o ID da pessoa que deseja atualizar: " + "\n\n0- Voltar"));
                    if (id == 0) {
                        menuPessoaOpc = -1;
                        break;
                    }

                    int veredito = Integer.parseInt(JOptionPane.showInputDialog("Deseja mesmo editar a pessoa abaixo?\n\n" + pessoadao.verPessoa(id) + "\n\n1- Sim.\n2- Não.\n\n0- Sair."));

                    if (veredito == 1) {
                        m = new StringBuilder("Informe o nome a ser atualizado: ");
                        String nomeAtt = JOptionPane.showInputDialog(m);

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
                        m = new StringBuilder("Pessoa abaixo atualizada com sucesso!\n\nAntes: \n" + pessoadao.verPessoa(id));

                        pessoadao.atualizaPessoa(nomeAtt, telefoneAtt, nascimentoAtt, id);

                        m.append("\nDepois: \n").append(pessoadao.verPessoa(id));
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
                    JOptionPane.showMessageDialog(null, pessoadao.verConvidados());
                    break;

                case 4:
                    id = Integer.parseInt(JOptionPane.showInputDialog(pessoadao.verConvidados() + "\nInforme o ID da pessoa a ser desconvidada: " + "\n\n0- Voltar"));

                    if (id == 0) {
                        menuPessoaOpc = -1;
                        break;
                    }
                    veredito = Integer.parseInt(JOptionPane.showInputDialog("Deseja mesmo desconvidar a pessoa abaixo?\n\n" + pessoadao.verPessoa(id) + "\n\n1- Sim.\n2- Não.\n\n0- Sair."));

                    if (veredito == 1) {
                        JOptionPane.showMessageDialog(null, "Pessoa abaixo desconvidada com sucesso!\n\n" + pessoadao.verPessoa());
                        pessoadao.desconvidarPessoa(id);
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
