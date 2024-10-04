package controller;

import javax.swing.JOptionPane;
import model.Evento;
import model.Mensagens;
import model.MensagensDAO;
import model.Pessoa;
import model.PessoaDAO;
import model.Usuario;
import model.UsuarioDAO;
import view.GUI;

public class ControllerMensagens {

    public void controllerCrudMensagens(GUI gui, Usuario usuarioLogado, UsuarioDAO usuariodao, PessoaDAO pessoadao, Pessoa pessoa, Evento evento, Mensagens mensagens, MensagensDAO mensagensdao) {
        StringBuilder m;
        int menuCrudMensagemOpc = 0;

        while (menuCrudMensagemOpc != -1) {
            menuCrudMensagemOpc = gui.crudMensagem(usuarioLogado, evento);
            switch (menuCrudMensagemOpc) {
                case 1:
                    //Editar mensagem
                    /*
                    String s = mensagensdao.verMensagens();
                    if (!"".equals(s)) {
                        int id = Integer.parseInt(JOptionPane.showInputDialog(s + "\nInforme o ID da mensagem a ser editada: \n\n0- Voltar"));
                        if (id != 0) {
                            int veredito = JOptionPane.showConfirmDialog(null, "Deseja mesmo editar a mensagem do convite abaixo?\n\n" + mensagensdao.verMensagem(id), "Confirmar Edição", JOptionPane.YES_NO_OPTION);
                            if (veredito == JOptionPane.YES_OPTION) {
                                String nomeDoMensageiroAtt = JOptionPane.showInputDialog("Informe o novo nome do mensageiro: ");
                                String mensagemAtt = JOptionPane.showInputDialog("\nDigite a mensagem a ser subtituida: ");

                                if (!"".equals(nomeDoMensageiroAtt) || !"".equals(mensagemAtt)) {
                                    m = new StringBuilder("Convite atualizado com sucesso!\n\nAntes: \n" + mensagensdao.verMensagem(id));
                                    mensagensdao.atualizaMensagem(nomeDoMensageiroAtt, mensagemAtt, id);
                                    m.append("\nAgora: \n").append(mensagensdao.verMensagem(id));
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
                     */

                    //case 2:
                    //Exibir mensagens
                    String s = mensagensdao.verMensagens();
                    if ("".equals(s)) {
                        s = "Ainda não há mensagens enviadas.";
                    }
                    JOptionPane.showMessageDialog(null, s);
                    break;

                //case 3:
                case 2:
                    //Excluir mensagens
                    s = mensagensdao.verMensagens();
                    if (!"".equals(s)) {
                        int id = Integer.parseInt(JOptionPane.showInputDialog(s + "\nInforme o ID da mensagem a ser excluida: \n\n0- Voltar"));
                        if (id != 0) {
                            int veredito = JOptionPane.showConfirmDialog(null, "Deseja mesmo excluir a mensagem abaixo?\n\n" + mensagensdao.verMensagem(id), "Confirmar Exclusão", JOptionPane.YES_NO_OPTION);
                            if (veredito == JOptionPane.YES_OPTION) {
                                JOptionPane.showMessageDialog(null, "Mensagem excluída com sucesso!\n\n" + mensagensdao.verMensagem(id));
                                mensagensdao.excluiMensagem(id);
                            } else {
                                JOptionPane.showMessageDialog(null, "Exclusão não sucedida...");
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Ainda não há mensagens enviadas.");
                    }
                    break;

                case 0:
                    //Voltar
                    menuCrudMensagemOpc = -1;
                    break;

                default:
                    menuCrudMensagemOpc = -1;
                    break;
            }
        }
    }    
}
