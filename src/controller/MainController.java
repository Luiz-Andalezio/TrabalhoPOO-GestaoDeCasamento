package controller;

import java.time.LocalDate;
import model.CalendarioDAO;
import model.ConvidadoFamilia;
import model.ConvidadoFamiliaDAO;
import model.ConvidadoIndividual;
import model.ConvidadoIndividualDAO;
import model.Evento;
import model.EventoDAO;
import model.Mensagens;
import model.MensagensDAO;
import model.Pessoa;
import model.PessoaDAO;
import model.PresentesDAO;
import model.Usuario;
import model.UsuarioDAO;
import view.GUI;

public class MainController {

    //controller
    ControllerCalendario cc = new ControllerCalendario();
    ControllerConviteFamilia ccf = new ControllerConviteFamilia();
    ControllerConvidadoIndividual cci = new ControllerConvidadoIndividual();
    ControllerMensagens cm = new ControllerMensagens();
    ControllerUsuario cu = new ControllerUsuario();

    //model
    Pessoa pessoa = new Pessoa();
    PessoaDAO pessoadao = new PessoaDAO();
    UsuarioDAO usuariodao = new UsuarioDAO(pessoadao);
    Usuario usuarioLogado = null;
    EventoDAO eventodao = new EventoDAO(pessoadao);
    Evento evento = eventodao.retornaEvento();
    Mensagens mensagens = new Mensagens();
    MensagensDAO mensagensdao = new MensagensDAO();
    PresentesDAO presentesdao = new PresentesDAO();
    ConvidadoIndividual conviteindividual = new ConvidadoIndividual();
    ConvidadoIndividualDAO conviteindividualdao = new ConvidadoIndividualDAO(pessoadao);    
    ConvidadoFamilia convitefamilia = new ConvidadoFamilia();
    ConvidadoFamiliaDAO convitefamiliadao = new ConvidadoFamiliaDAO(conviteindividualdao, evento);

    //view
    GUI gui = new GUI();

    //Calendário
    LocalDate calendario = LocalDate.now();
    CalendarioDAO calendarioDAO = new CalendarioDAO();

    public MainController() {
        int mainOpc = 0;

        while (mainOpc != -1) {

            mainOpc = gui.menuBoasVindas(evento);

            switch (mainOpc) {
                case 1:
                    usuarioLogado = gui.login(usuariodao);

                    int opc = 0;
                    while (opc != -1) {
                        if (usuarioLogado.getTipo().equals("Noivo") || usuarioLogado.getTipo().equals("Noiva")) {
                            opc = gui.menuNoivos(usuarioLogado);
                            switch (opc) {
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
                                    //cvp = Controller Ver Presentes
                                    //cvp.controllerVerPagamentos(gui, usuarioLogado);
                                    break;

                                case 5:
                                    //cvp = Controller Pagamentos
                                    //cvp.controllerVerPagamentos(gui, usuarioLogado);
                                    break;

                                case 6:
                                    //cc = Controller Calendario
                                    cc.controllerCrudIncrementaData(gui, usuarioLogado, calendario, calendarioDAO);
                                    break;

                                case 7:
                                    //cr = Controller Relatorios
                                    //cr.controllerRelatorios(gui, usuarioLogado)

                                case 0:
                                    //Voltar
                                    opc = -1;
                                    break;

                                default:
                                    opc = 0;
                                    break;
                            }
                        }
                        if (usuarioLogado.getTipo().equals("Admin")) {
                            opc = gui.menuAdmin(usuarioLogado);
                            switch (opc) {
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
                                    cu.controllerCrudUsuarios(gui, usuarioLogado, usuariodao, pessoadao, pessoa);
                                    break;

                                case 4:
                                    //cm = Controller Mensagens
                                    cm.controllerCrudMensagens(gui, usuarioLogado, usuariodao, pessoadao, pessoa, evento, mensagens, mensagensdao);
                                    break;

                                case 5:
                                    //cvp = Controller Ver Presentes
                                    //cvp.controllerVerPagamentos(gui, usuarioLogado);
                                    break;

                                case 6:
                                    //cvp = Controller Ver Pagamentos
                                    //cvp.controllerVerPagamentos(gui, usuarioLogado);
                                    break;

                                case 7:
                                    //cc = Controller Calendario
                                    cc.controllerCrudIncrementaData(gui, usuarioLogado, calendario, calendarioDAO);
                                    break;

                                case 8:
                                    //cr = Controller Relatorios
                                    //cr.controllerRelatorios(gui, usuarioLogado)

                                case 0:
                                    //Voltar
                                    opc = -1;
                                    break;

                                default:
                                    opc = 0;
                                    break;
                            }
                        }
                        if (usuarioLogado.getTipo().equals("Cerimonial")) {
                            opc = gui.menuCerimonial(usuarioLogado);
                            switch (opc) {
                                case 1:
                                    //Ver Convites Família
                                    convitefamiliadao.verConvitesFamilia();
                                    break;

                                case 2:
                                    //cm = Controller Mensagens
                                    mensagensdao.enviarMensagem(evento, mensagens, mensagensdao);
                                    break;

                                case 3:                                    
                                    //cp = Controller Presentes
                                    //cp.controllerDarPresente(gui, usuarioLogado, presentesdao);
                                    break;

                                case 4:
                                    //cr = Controller Relatorios
                                    //cr.controllerRelatorios(gui, usuarioLogado)
                                    break;

                                case 0:
                                    //Voltar
                                    opc = -1;
                                    break;

                                default:
                                    opc = 0;
                                    break;
                            }
                        }
                    }
                    break;

                case 2:
                    convitefamilia = gui.loginConviteFamilia(convitefamiliadao);
                    opc = 0;
                    while (opc != -1) {                        
                        opc = gui.menuConvidado(convitefamilia);
                        //opc = gui.gui.menuConvidado();
                        switch (opc) {
                            case 1:
                                //cm = Controller Mensagens
                                mensagensdao.enviarMensagem(evento, mensagens, mensagensdao);
                                break;

                            case 2:                            
                                //cp = Controller Presentes
                                //cp.controllerDarPresente(gui, usuarioLogado, presentesdao);
                                break;

                            case 3:
                                //ccf = Controller Convidado Familia
                                //ccf.controllerConfirmarFamiliares(gui, usuarioLogado);
                                break;

                            case 0:
                                //Voltar
                                opc = -1;
                                break;

                            default:
                                opc = 0;
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
