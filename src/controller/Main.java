package controller;

import model.ConvidadoFamiliaDAO;
import model.ConvidadoIndividualDAO;
import model.Evento;
import model.EventoDAO;
import model.Pessoa;
import model.PessoaDAO;
import model.PresentesDAO;
import model.Usuario;
import model.UsuarioDAO;
import view.GUI;

public class Main {
    //controller
    ControllerConviteFamilia ccf = new ControllerConviteFamilia();
    ControllerConvidadoIndividual cci = new ControllerConvidadoIndividual();

    //model
    Pessoa pessoa = new Pessoa();
    PessoaDAO pessoadao = new PessoaDAO();
    UsuarioDAO usuariodao = new UsuarioDAO(pessoadao);
    Usuario usuarioLogado = null;
    EventoDAO eventodao = new EventoDAO(pessoadao);
    Evento evento = eventodao.retornaEvento();
    PresentesDAO presentesdao = new PresentesDAO();
    ConvidadoIndividualDAO conviteindividualdao = new ConvidadoIndividualDAO();
    ConvidadoFamiliaDAO convitefamilia = new ConvidadoFamiliaDAO();

    //gui
    GUI gui = new GUI();

    public void main(String[] args) {

        int mainOpc = 0;

        while (mainOpc != -1) {

            mainOpc = gui.menuBoasVindas(evento);

            switch (mainOpc) {
                case 1:
                    usuarioLogado = gui.login(usuariodao);

                    int adminOpc = 0;
                    while (adminOpc != -1) {
                        if (usuarioLogado.getTipo().equals("Noivo") || usuarioLogado.getTipo().equals("Noiva")) {
                            adminOpc = gui.menuNoivos(usuarioLogado);
                            switch (adminOpc) {
                                case 1:
                                    //ccf = Controller Convite Familia
                                    ccf.controllerCrudFamilia(gui, usuarioLogado, pessoadao, convitefamilia);
                                    break;

                                case 2:
                                    //cci = Controller Convidado Individual
                                    cci.controllerCrudConvidado(gui, usuarioLogado, pessoadao, pessoa, conviteindividualdao);
                                    break;

                                case 3:
                                    //cm = Controller Mensagens
                                    //cm.controllerVerMensagens(gui, usuarioLogado);
                                    break;

                                case 4:
                                    //cvp = Controller Pagamentos
                                    //cvp.controllerVerPagamentos(gui, usuarioLogado);
                                    break;

                                case 5:
                                    //cc = Controller Calendario
                                    //cc.controllerIncrementaData(gui, usuarioLogado);
                                    break;

                                case 6:
                                //cr = Controller Relatorios
                                //cr.controllerRelatorios(gui, usuarioLogado)

                                case 7:
                                    //Voltar
                                    adminOpc = -1;
                                    break;

                                default:
                                    adminOpc = 0;
                                    break;
                            }

                        } if (usuarioLogado.getTipo().equals("Admin")) {
                            adminOpc = gui.menuAdmin(usuarioLogado);
                            switch (adminOpc) {
                                case 1:
                                    //ccf = Controller Convite Familia
                                    ccf.controllerCrudFamilia(gui, usuarioLogado, pessoadao, convitefamilia);
                                    break;

                                case 2:
                                    //cci = Controller Convidado Individual
                                    cci.controllerCrudConvidado(gui, usuarioLogado, pessoadao, pessoa, conviteindividualdao);
                                    break;

                                case 3:
                                    //cu = Controller Usuarios
                                    //cu.controllerCrudUsuarios(gui, usuarioLogado, usuariodao);
                                    break;

                                case 4:
                                    //cm = Controller Mensagens
                                    //cm.controllerVerMensagens(gui, usuarioLogado);
                                    break;

                                case 5:
                                    //cvp = Controller Ver Pagamentos
                                    //cvp.controllerVerPagamentos(gui, usuarioLogado);
                                    break;

                                case 6:
                                    //cc = Controller Calendario
                                    //cc.controllerIncrementaData(gui, usuarioLogado);
                                    break;

                                case 7:
                                    //cr = Controller Relatorios
                                    //cr.controllerRelatorios(gui, usuarioLogado)

                                case 8:
                                    //Voltar
                                    adminOpc = -1;
                                    break;

                                default:
                                    adminOpc = 0;
                                    break;
                            }
                        } if (usuarioLogado.getTipo().equals("Cerimonial")) {
                            adminOpc = gui.menuCerimonial(usuarioLogado);
                            switch (adminOpc) {
                                case 1:
                                    //Ver convidados
                                    //ccf = Controller Convite Familia
                                    //convitesfamilias.verConvidados();
                                    break;

                                case 2:
                                    //cvp = Controller Ver Pagamentos
                                    //cvp.controllerVerPagamentos(gui, usuarioLogado);
                                    break;

                                case 3:
                                    //cvp = Controller Ver Pagamentos
                                    //cvp.controllerVerPagamentos(gui, usuarioLogado);
                                    break;

                                case 4:
                                    //cc = Controller Calendario
                                    //cc.controllerIncrementaData(gui, usuarioLogado);
                                    break;

                                case 5:
                                    //cr = Controller Relatorios
                                    //cr.controllerRelatorios(gui, usuarioLogado)
                                    break;

                                case 6:
                                    //Voltar
                                    adminOpc = -1;
                                    break;

                                default:
                                    adminOpc = 0;
                                    break;
                            }
                        }
                    }
                    break;

                case 2:
                    int convidadoOpc = gui.menuConvidado();
                    //int convidadoOpc = gui.loginConvidado();
                    while (convidadoOpc != -1) {
                        //convidadoOpc = gui.gui.menuConvidado();
                        switch (convidadoOpc) {
                            case 1:
                                //cp = Controller Presentes
                                //cp.controllerDarPresente(gui, usuarioLogado, presentesdao); 
                                gui.crudPresentesConvidado(presentesdao);
                                break;

                            case 2:
                                //cm = Controller Mensagens
                                //cm.controllerEnviarMensagem(gui, usuarioLogado);
                                break;

                            case 3:
                                //ccf = Controller Convidado Familia
                                //ccf.controllerConfirmarFamiliares(gui, usuarioLogado);
                                break;

                            case 0:
                                //Voltar
                                convidadoOpc = -1;
                                break;

                            default:
                                convidadoOpc = 0;
                                break;
                        }
                    }
                    break;

                case 0:
                    mainOpc = -1;
                    break;

                default:
                    mainOpc = 0;
                    break;
            }
        }
    }
    /*
        //Testes

        PessoaDAO pessoadao1 = new PessoaDAO("João", "991650733", "19/04/2004");
        Pessoa p1 = pessoadao1.retornaPessoa(3);
        JOptionPane.showMessageDialog(null, p1);

        PessoaDAO pessoadao2 = new PessoaDAO("Mariaaa", "991241367", "20/02/2001");
        Pessoa p2 = pessoadao2.retornaPessoa(4);
        JOptionPane.showMessageDialog(null, p2);

        /////////

        PessoaDAO pessoadao1 = new PessoaDAO(1, "João", "19/04/2004", "991650733");
        Pessoa p1 = pessoadao1.retornaPessoa();

        UsuarioDAO usuariodao1 = new UsuarioDAO(p1, "noivo", "coxinha", "123");
        Usuario u1 = usuariodao1.retornaUsuario();

        PessoaDAO pessoadao2 = new PessoaDAO(2, "Maria", "29/06/2005", "991279145");
        Pessoa p2 = pessoadao1.retornaPessoa();

        UsuarioDAO usuariodao2 = new UsuarioDAO(p2, "noiva", "balinha", "123");
        Usuario u2 = usuariodao2.retornaUsuario();

        //Testes com JOptionPane
        PessoaDAO pessoadao0 = new PessoaDAO(0, null, null, null);
        Pessoa p0 = pessoadao0.retornaPessoa();

        JOptionPane.showConfirmDialog(null, "Preparado para criar uma pessoa com usuario?");
        p0.setId(Integer.parseInt(JOptionPane.showInputDialog("Insira um id: ")));
        p0.setNome(JOptionPane.showInputDialog("Insira um nome: "));
        p0.setTelefone(JOptionPane.showInputDialog("Insiga um telefone: "));
        p0.setNascimento(JOptionPane.showInputDialog("Insina um nascimento: "));

        JOptionPane.showConfirmDialog(null, p0 + " \nEstes sao os dados desejados?");
        JOptionPane.showMessageDialog(null, "Dados confirmados!\n\nPessoa:\n" + p0);

        JOptionPane.showConfirmDialog(null, "Preparado para criar um usuario para esta pessoa?");

        UsuarioDAO usuariodao0 = new UsuarioDAO(p0, null, null, null);
        Usuario u0 = usuariodao0.retornaUsuario();

        u0.setId(Integer.parseInt(JOptionPane.showInputDialog("Insira um id: ")));
        u0.setLogin(JOptionPane.showInputDialog("Insira um login: "));
        u0.setSenha(JOptionPane.showInputDialog("Insira uma senha: "));

        JOptionPane.showConfirmDialog(null, u0 + "\nEstes são os dados finais do " + p0.getNome() + " e seu usuario de id: " + u0.getId());
        JOptionPane.showMessageDialog(null, "Usuario criado com sucesso!\n\nDados:\n" + u0);
     */
}
