package controller;

import javax.swing.JOptionPane;
import model.ConvidadoFamilia;
import model.ConvidadoFamiliaDAO;
import model.ConvidadoIndividual;
import model.ConvidadoIndividualDAO;
import model.Evento;
import model.Pessoa;
import model.PessoaDAO;
import model.Usuario;
import view.GUI;

public class ControllerConviteFamilia {

    public void controllerCrudFamilia(GUI gui, Usuario usuarioLogado, PessoaDAO pessoadao, Pessoa pessoa, Evento evento, ConvidadoIndividual conviteIndividual, ConvidadoIndividualDAO conviteIndividualDAO, ConvidadoFamilia convidadoFamilia, ConvidadoFamiliaDAO convidadoFamiliaDAO) {
        StringBuilder m;
        int menuFamiliaOpc = 0;

        while (menuFamiliaOpc != -1) {
            menuFamiliaOpc = gui.crudConvFamilia(usuarioLogado);
            switch (menuFamiliaOpc) {
                case 1:
                    //Gerar Convite Família
                    String novoNomeDaFamilia = JOptionPane.showInputDialog("Digite o nome da família a ser convidada: \n\n0- Voltar");

                    if ("".equals(novoNomeDaFamilia)) {
                        JOptionPane.showMessageDialog(null, "Nenhum dado enviado: Convite Família não gerado...");
                    } else if ("0".equals(novoNomeDaFamilia)) {
                    } else {
                        m = new StringBuilder("Convite Família gerado para a família: ")
                                .append(novoNomeDaFamilia)
                                .append("\n\n")
                                .append(convidadoFamiliaDAO.convidaFamilia(novoNomeDaFamilia, evento));
                        JOptionPane.showMessageDialog(null, m);
                    }
                    break;

                case 2:
                    //Gerar novos códigos de acesso para Convites Família
                    String s = convidadoFamiliaDAO.verConvitesFamilia();

                    if ("".equals(s)) {
                        JOptionPane.showMessageDialog(null, "Ainda não há Convites Família gerados.");
                    } else {
                        m = new StringBuilder("Informe o ID do Convite Família a receber um novo código de acesso: \n\n")
                                .append(convidadoFamiliaDAO.verConvitesFamilia())
                                .append("0- Voltar");

                        int id = Integer.parseInt(JOptionPane.showInputDialog(m));

                        if (id == 0) {
                            JOptionPane.showMessageDialog(null, "Atualização de código de acesso não sucedida.");
                        } else {
                            m = new StringBuilder("Deseja mesmo atualizar o código de acesso desta família? \n\n")
                                    .append(convidadoFamiliaDAO.verConviteFamilia(id));

                            int veredito = JOptionPane.showConfirmDialog(null, m.append(""), "Confirmar Atualização", JOptionPane.YES_NO_OPTION);

                            if (veredito == JOptionPane.YES_OPTION) {
                                m = new StringBuilder("Código de acesso da família ")
                                        .append(convidadoFamiliaDAO.retornaConviteFamilia(id).getNomeDaFamilia())
                                        .append(" atualizado com sucesso!\n\nAntes: \n")
                                        .append(convidadoFamiliaDAO.retornaConviteFamilia(id).getAcesso());

                                convidadoFamilia = convidadoFamiliaDAO.retornaConviteFamilia(id);
                                convidadoFamiliaDAO.atualizaAcesso(id, evento, convidadoFamilia);

                                m.append("\n\nAgora: \n").append(convidadoFamiliaDAO.retornaConviteFamilia(id).getAcesso());
                                JOptionPane.showMessageDialog(null, m);
                            } else {
                                m = new StringBuilder("Adição de pessoas no convite da família ")
                                        .append(convidadoFamiliaDAO.retornaConviteFamilia(id).getNomeDaFamilia())
                                        .append(" não sucedida.");
                                JOptionPane.showMessageDialog(null, m);
                            }
                        }
                    }
                    break;

                case 3:
                    //Adicionar pessoas em Convites Família
                    s = convidadoFamiliaDAO.verConvitesFamilia();

                    if ("".equals(s)) {
                        JOptionPane.showMessageDialog(null, "Ainda não há Convites Família gerados.");
                    } else {
                        m = new StringBuilder("Informe o ID do Convite Família a receber pessoas: \n\n")
                                .append(convidadoFamiliaDAO.verConvitesFamilia())
                                .append("0- Voltar");

                        int id = Integer.parseInt(JOptionPane.showInputDialog(m));

                        if (id == 0) {
                            JOptionPane.showMessageDialog(null, "Adição de pessoas em Convite Família não sucedida.");
                        } else {
                            m = new StringBuilder("Deseja mesmo acrescentar convites nesta família? \n\n")
                                    .append(convidadoFamiliaDAO.verConviteFamilia(id));

                            int veredito = JOptionPane.showConfirmDialog(null, m.append(""), "Confirmar Adição", JOptionPane.YES_NO_OPTION);

                            if (veredito == JOptionPane.YES_OPTION) {
                                m = new StringBuilder("Informe o ID do Convite Individual a entrar no convite da família: ")
                                        .append(convidadoFamiliaDAO.retornaConviteFamilia(id).getNomeDaFamilia())
                                        .append("\n\n-- CONVITES INDIVIDUAIS --\n\n")
                                        .append(conviteIndividualDAO.verConvidados())
                                        .append("\n0- Voltar");

                                int id2 = Integer.parseInt(JOptionPane.showInputDialog(m));

                                if (id2 == 0) {
                                    JOptionPane.showMessageDialog(null, "Operação não sucedida.");
                                } else {
                                    veredito = JOptionPane.showConfirmDialog(null, "Deseja mesmo adicionar " + conviteIndividualDAO.retornaConviteIndividual(id2).getPessoa().getNome() + " no Convite Família abaixo?\n\n" + convidadoFamiliaDAO.verConviteFamilia(id), "Confirmar Edição", JOptionPane.YES_NO_OPTION);

                                    if (veredito == JOptionPane.YES_OPTION) {
                                        conviteIndividual = conviteIndividualDAO.retornaConviteIndividual(id2);
                                        convidadoFamiliaDAO.recebeConviteIndividual(id, id2, conviteIndividual);

                                        m = new StringBuilder("Convite Individual de: ")
                                                .append(conviteIndividual.getPessoa().getNome())
                                                .append(" adicionado ao Convite Família da família: ")
                                                .append(convidadoFamiliaDAO.retornaConviteFamilia(id).getNomeDaFamilia())
                                                .append("\n\nNovo estado do convite: \n\n")
                                                .append(convidadoFamiliaDAO.verConviteFamilia(id));

                                        JOptionPane.showMessageDialog(null, m);
                                    } else {
                                        m = new StringBuilder("Adição de pessoas no convite da família '")
                                                .append(convidadoFamiliaDAO.retornaConviteFamilia(id).getNomeDaFamilia())
                                                .append("' não sucedida.");

                                        JOptionPane.showMessageDialog(null, m);
                                    }
                                }
                            }
                        }
                    }
                    break;

                case 4:
                    //Excluir pessoas em Convites Família
                    s = convidadoFamiliaDAO.verConvitesFamilia();
                    String s2 = conviteIndividualDAO.verConvidados();

                    if ("".equals(s)) {
                        JOptionPane.showMessageDialog(null, "Ainda não há Convites Família gerados.");
                    } else if ("".equals(s2)) {
                        JOptionPane.showMessageDialog(null, "Não há convidados em nenhum dos Convites Família. Impossível realizar exclusões.\n\n" + convidadoFamiliaDAO.verConvitesFamilia());
                    } else {
                        int continuarAtt = 0;
                        int id = Integer.parseInt(JOptionPane.showInputDialog("Insira o ID do Convite Família a ter convites individuais excluídos.\n\n" + s + "\n0- Voltar"));

                        if (id == 0) {
                        } else if (convidadoFamiliaDAO.retornaConviteFamilia(id) == null || !convidadoFamiliaDAO.retornaConviteFamilia(id).getVetorConvidadosIndividuais()) {
                            JOptionPane.showMessageDialog(null, "Não há convidados neste Convite Família para serem excluídos.\n\n" + convidadoFamiliaDAO.verConviteFamilia(id));
                        } else {
                            while (continuarAtt != -1) {
                                m = new StringBuilder("Deseja continuar para excluir convidados deste Convite Família?\n\n" + convidadoFamiliaDAO.retornaConviteFamilia(id));
                                int continuar = JOptionPane.showConfirmDialog(null, m.toString(), "Confirmar Exclusão", JOptionPane.YES_NO_OPTION);

                                if (continuar == JOptionPane.YES_OPTION) {
                                    int conviteID = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID do convite individual da pessoa a ser excluída do Convite Família:\n\n" + convidadoFamiliaDAO.retornaConviteFamilia(id) + "\n\n0- Voltar"));

                                    if (conviteID == 0) {
                                        JOptionPane.showMessageDialog(null, "Exclusão de convidados não sucedida...");
                                        continuarAtt = -1;
                                    } else {
                                        int veredito = JOptionPane.showConfirmDialog(null, "Deseja mesmo excluir a pessoa abaixo?\n\n" + conviteIndividualDAO.verConvite(conviteID), "Confirmar Exclusão", JOptionPane.YES_NO_OPTION);

                                        if (veredito == JOptionPane.YES_OPTION) {
                                            JOptionPane.showMessageDialog(null, "Convidado abaixo excluído com sucesso!\n\n" + conviteIndividualDAO.verConvite(conviteID));
                                            convidadoFamiliaDAO.excluirConviteIndividualConviteFamilia(id, conviteID);
                                        } else {
                                            JOptionPane.showMessageDialog(null, "Exclusão não realizada...");
                                        }
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(null, "Exclusão de convidados não continuada...");
                                    continuarAtt = -1;
                                }
                            }
                            JOptionPane.showMessageDialog(null, "Exclusões finalizadas!\n\n" + convidadoFamiliaDAO.retornaConviteFamilia(id));
                        }
                    }
                    break;

                case 5:
                    //Editar Convites Família
                    s = convidadoFamiliaDAO.verConvitesFamilia();

                    if ("".equals(s)) {
                        s = "Ainda não há Convites Família gerados.";
                        JOptionPane.showMessageDialog(null, s);
                    } else {
                        int id = Integer.parseInt(JOptionPane.showInputDialog("Insira o ID do Convite Família a ser atualizado.\n\n" + s + "\n0- Voltar"));

                        if (id == 0) {
                        } else {
                            JOptionPane.showMessageDialog(null, "Caso queira pular uma edição, deixe a caixa vazia e pressione enter.");
                            m = new StringBuilder("Insira o novo nome a ser atualizado para a família ")
                                    .append(convidadoFamiliaDAO.retornaConviteFamilia(id).getNomeDaFamilia())
                                    .append(" de ID ")
                                    .append(convidadoFamiliaDAO.retornaConviteFamilia(id).getId())
                                    .append(":\n\n0- Voltar");

                            String nomeDaFamiliaAtt = JOptionPane.showInputDialog(m);

                            if (!"".equals(nomeDaFamiliaAtt)) {
                                convidadoFamiliaDAO.atualizaNomeConviteFamilia(id, nomeDaFamiliaAtt);
                                JOptionPane.showMessageDialog(null, "Nome da família atualizado com sucesso!\n" + convidadoFamiliaDAO.retornaConviteFamilia(id));
                            } else {
                                JOptionPane.showMessageDialog(null, "Atualização do nome da família não realizada.");
                            }

                            s2 = conviteIndividualDAO.verConvidados();
                            if ("".equals(s2)) {
                                s2 = "Não há convidados em nenhum dos Convites Família. Impossível realizar edições.\n\n" + convidadoFamiliaDAO.verConvitesFamilia();
                                JOptionPane.showMessageDialog(null, s2);
                            } else if (!convidadoFamiliaDAO.retornaConviteFamilia(id).getVetorConvidadosIndividuais()) {
                                JOptionPane.showMessageDialog(null, "Não há convidados neste Convite Família para serem atualizados.\n\n" + convidadoFamiliaDAO.verConviteFamilia(id));
                            } else {
                                int continuarAtt = 0;
                                while (continuarAtt != -1) {
                                    m = new StringBuilder("Deseja continuar para atualizar os convidados deste Convite Família?\n\n" + convidadoFamiliaDAO.retornaConviteFamilia(id));
                                    int continuar = JOptionPane.showConfirmDialog(null, m, "Confirmar Atualização", JOptionPane.YES_NO_OPTION);

                                    if (continuar == JOptionPane.YES_OPTION) {
                                        int conviteID = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID do convite individual da pessoa a ser atualizada deste Convite Família:\n\n" + convidadoFamiliaDAO.retornaConviteFamilia(id) + "\n\n0- Voltar"));

                                        if (conviteID == 0) {
                                            JOptionPane.showMessageDialog(null, "Edição de convidados não sucedida...");
                                        } else {
                                            int veredito = JOptionPane.showConfirmDialog(null, "Deseja mesmo editar a pessoa abaixo?\n\n" + conviteIndividualDAO.verConvite(conviteID), "Confirmar Edição", JOptionPane.YES_NO_OPTION);

                                            if (veredito == JOptionPane.YES_OPTION) {
                                                String nomeAtt = JOptionPane.showInputDialog("Informe o nome a ser atualizado: ");
                                                String parentescoAtt = JOptionPane.showInputDialog("\nInforme o parentesco a ser atualizado: ");
                                                String telefoneAtt = JOptionPane.showInputDialog("\nInforme o telefone a ser atualizado: ");
                                                String nascimentoAtt = JOptionPane.showInputDialog("\nInforme a data de nascimento a ser atualizada: ");

                                                if (!"".equals(nomeAtt) || !"".equals(parentescoAtt) || !"".equals(telefoneAtt) || !"".equals(nascimentoAtt)) {
                                                    convidadoFamiliaDAO.atualizaPessoasConviteFamilia(id, conviteID, nomeAtt, telefoneAtt, nascimentoAtt, parentescoAtt);
                                                    JOptionPane.showMessageDialog(null, "Convidado atualizado com sucesso!\n\n" + conviteIndividualDAO.verConvite(conviteID));
                                                } else {
                                                    JOptionPane.showMessageDialog(null, "Nenhum dado enviado: atualizações não foram realizadas...");
                                                }
                                            } else {
                                                JOptionPane.showMessageDialog(null, "Edição do convite de " + conviteIndividualDAO.verConvite(conviteID).getPessoa().getNome() + " não sucedida...");
                                            }
                                        }
                                    } else {
                                        JOptionPane.showMessageDialog(null, "Edição de convidados não continuada...");
                                        continuarAtt = -1;
                                    }
                                }
                                JOptionPane.showMessageDialog(null, "Edições finalizadas!\n\n" + convidadoFamiliaDAO.retornaConviteFamilia(id));
                            }
                        }
                    }
                    break;

                case 6:
                    //Mostrar Convites Família
                    s = convidadoFamiliaDAO.verConvitesFamilia();
                    if ("".equals(s)) {
                        s += "Ainda não há Convites Família feitos.";
                    }
                    JOptionPane.showMessageDialog(null, s);
                    break;

                case 7:
                    //Excluir Convites Família
                    s = convidadoFamiliaDAO.verConvitesFamilia();

                    if ("".equals(s)) {
                        JOptionPane.showMessageDialog(null, "Ainda não há Convites Família gerados.");
                    } else {
                        m = new StringBuilder("Insira o ID do Convite Família que deseja excluir:\n\n").append(s).append("0- Voltar");
                        int id = Integer.parseInt(JOptionPane.showInputDialog(m));

                        if (id == 0) {
                        } else {
                            int veredito = JOptionPane.showConfirmDialog(null, "Deseja mesmo desconvidar a família do convite abaixo?\n\n" + convidadoFamiliaDAO.verConviteFamilia(id), "Confirmar Exclusão", JOptionPane.YES_NO_OPTION);

                            if (veredito == JOptionPane.YES_OPTION) {
                                JOptionPane.showMessageDialog(null, "Convite da família abaixo desfeito com sucesso!\n\n" + convidadoFamiliaDAO.verConviteFamilia(id));
                                convidadoFamiliaDAO.desfazerConviteFamilia(id);
                            } else {
                                JOptionPane.showMessageDialog(null, "Exclusão não continuada...");
                            }
                        }
                    }
                    break;

                case 0:
                    //Voltar
                    menuFamiliaOpc = -1;
                    break;

                default:
                    menuFamiliaOpc = -1;
                    break;
            }
        }
    }

    public void controllerVerConvitesFamilia(ConvidadoFamiliaDAO convidadoFamiliaDAO) {
        String s = convidadoFamiliaDAO.verConvitesFamilia();
        if ("".equals(s)) {
            s += "Ainda não há Convites Família feitos.";
        }
        JOptionPane.showMessageDialog(null, s);
    }
}