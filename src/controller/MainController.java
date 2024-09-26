package controller;

import model.ConvidadoFamilia;
import model.ConvidadoFamiliaDAO;
import model.ConvidadoIndividual;
import model.ConvidadoIndividualDAO;
import model.Evento;
import model.EventoDAO;
import model.Pessoa;
import model.PessoaDAO;
import model.PresentesDAO;
import model.Usuario;
import model.UsuarioDAO;
import view.GUI;

public class MainController {

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
    ConvidadoIndividual conviteindividual = new ConvidadoIndividual();
    ConvidadoIndividualDAO conviteindividualdao = new ConvidadoIndividualDAO();
    ConvidadoFamilia convitefamilia = new ConvidadoFamilia();
    ConvidadoFamiliaDAO convitefamiliadao = new ConvidadoFamiliaDAO();

    //gui
    GUI gui = new GUI();

    public MainController() {
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
                                    ccf.controllerCrudFamilia(gui, usuarioLogado, pessoadao, pessoa, evento, conviteindividual, conviteindividualdao, convitefamilia, convitefamiliadao);
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
                        }
                        if (usuarioLogado.getTipo().equals("Admin")) {
                            adminOpc = gui.menuAdmin(usuarioLogado);
                            switch (adminOpc) {
                                case 1:
                                    //ccf = Controller Convite Familia
                                    ccf.controllerCrudFamilia(gui, usuarioLogado, pessoadao, pessoa, evento, conviteindividual, conviteindividualdao, convitefamilia, convitefamiliadao);
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

                                case 0:
                                    //Voltar
                                    adminOpc = -1;
                                    break;

                                default:
                                    adminOpc = 0;
                                    break;
                            }
                        }
                        if (usuarioLogado.getTipo().equals("Cerimonial")) {
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

                                case 0:
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
                    mainOpc = -1;
                    break;
            }
        }
    }

    public static void main(String[] args) {
        new MainController();
    }
}
