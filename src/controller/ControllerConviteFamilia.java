package controller;

import javax.swing.JOptionPane;
import model.ConvidadoFamiliaDAO;
import model.ConvidadoIndividual;
import model.ConvidadoIndividualDAO;
import model.Evento;
import model.Pessoa;
import model.PessoaDAO;
import model.Usuario;
import view.GUI;

public class ControllerConviteFamilia {

    public void controllerCrudFamilia(GUI gui, Usuario usuarioLogado, PessoaDAO pessoadao, Pessoa pessoa, Evento evento, ConvidadoIndividual conviteIndividual, ConvidadoIndividualDAO conviteIndividualDAO, ConvidadoFamiliaDAO convidadoFamiliaDAO) {
        StringBuilder m;
        int menuFamiliaOpc = 0;

        while (menuFamiliaOpc != -1) {
            menuFamiliaOpc = gui.crudConvFamilia(usuarioLogado);
            switch (menuFamiliaOpc) {
                case 1:
                    String novoNomeDaFamilia = JOptionPane.showInputDialog("Digite o nome da família a ser convidada: \n\n0- Voltar");
                    if ("".equals(novoNomeDaFamilia)) {
                        JOptionPane.showMessageDialog(null, "Convite Família não sucedido.");
                        break;
                    }
                    if ("0".equals(novoNomeDaFamilia)) {
                        break;
                    } else {
                        m = new StringBuilder("Convite Família gerado para a família: ").append(novoNomeDaFamilia).append("\n\n").append(convidadoFamiliaDAO.convidaFamilia(novoNomeDaFamilia, evento));
                        JOptionPane.showMessageDialog(null, m);

                    }
                    break;

                case 2:
                    String s = convidadoFamiliaDAO.verConvitesFamilia();
                    if ("".equals(s)) {
                        JOptionPane.showMessageDialog(null, "Ainda não há Convites Família gerados.");
                        break;
                    }
                    m = new StringBuilder("Informe o ID do Convite Família a receber pessoas: \n\n").append(convidadoFamiliaDAO.verConvitesFamilia()).append("0- Voltar");
                    int id = Integer.parseInt(JOptionPane.showInputDialog(m));
                    if (id == 0) {
                        JOptionPane.showMessageDialog(null, "Adição de pessoas em Convite Família não sucedida.");
                        break;
                    } else {
                        m = new StringBuilder("Deseja mesmo acrescentar convites nesta família? \n\n").append(convidadoFamiliaDAO.verConvitesFamilia(id)).append("1- Sim.\n2- Não.\n\n0- Voltar.");
                        int veredito = Integer.parseInt(JOptionPane.showInputDialog(m));
                        if (veredito == 1) {
                            m = new StringBuilder("Informe o ID do Convite Individual a entrar no convite da família: ").append(convidadoFamiliaDAO.retornaConviteFamilia(id).getNomeDaFamilia()).append("\n\n-- CONVITES INDIVIDUAIS --\n\n").append(conviteIndividualDAO.verConvidados()).append("\n\n0- Voltar");
                            int id2 = Integer.parseInt(JOptionPane.showInputDialog(m));
                            if (id2 == 0) {
                                JOptionPane.showMessageDialog(null, "Operação não sucedida.");
                                break;
                            } else {
                                conviteIndividual = conviteIndividualDAO.retornaConviteIndividual(id2);
                                convidadoFamiliaDAO.recebeConviteIndividual(id2, conviteIndividual);
                                m = new StringBuilder("Convite Individual de: ").append(conviteIndividual.getPessoa().getNome()).append(" adicionado ao Convite Família da família: ").append(convidadoFamiliaDAO.retornaConviteFamilia(id).getNomeDaFamilia());
                                JOptionPane.showMessageDialog(null, m);
                                break;
                            }
                        }
                        if (veredito == 2) {
                            m = new StringBuilder("Adição de pessoas no convite da família '").append(convidadoFamiliaDAO.retornaConviteFamilia(id).getNomeDaFamilia()).append("' não sucedida.");
                            JOptionPane.showMessageDialog(null, m);
                            break;
                        }
                        if (veredito == 0) {
                            break;
                        }
                    }
                    break;

                case 3:
                    
                    break;
                
                case 4:
                    
                    break;

                case 5:
                    s = convidadoFamiliaDAO.verConvitesFamilia();
                    if ("".equals(s)) {
                        s += "Ainda não há convites feitos.";
                    }
                    JOptionPane.showMessageDialog(null, s);
                    break;

                case 6:
                    
                    break;

                case 0:
                    menuFamiliaOpc = -1;
                    break;

                default:
                    menuFamiliaOpc = 0;
                    break;
            }
        }
    }
}
