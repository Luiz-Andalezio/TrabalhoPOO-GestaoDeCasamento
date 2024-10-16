package controller;

import java.time.LocalDateTime;
import model.CalendarioDAO;
import model.ConvidadoFamilia;
import model.ConvidadoFamiliaDAO;
import model.ConvidadoIndividual;
import model.ConvidadoIndividualDAO;
import model.Evento;
import model.EventoDAO;
import model.Fornecedor;
import model.FornecedorDAO;
import model.Mensagens;
import model.MensagensDAO;
import model.Pessoa;
import model.PessoaDAO;
import model.Presentes;
import model.PresentesDAO;
import model.Usuario;
import model.UsuarioDAO;
import view.GUI;

public class MainController {

    //Calendário
    LocalDateTime calendario = LocalDateTime.of(2024, 10, 20, 04, 20, 07);
    CalendarioDAO calendarioDAO = new CalendarioDAO();

    //controller
    ControllerCalendario cc = new ControllerCalendario();
    ControllerConviteFamilia ccf = new ControllerConviteFamilia();
    ControllerConvidadoIndividual cci = new ControllerConvidadoIndividual();
    ControllerPesentes cp = new ControllerPesentes();
    ControllerMensagens cm = new ControllerMensagens();
    ControllerUsuario cu = new ControllerUsuario();
    ControllerFornecedor cf = new ControllerFornecedor();
    ControllerRelatorios cr = new ControllerRelatorios();

    //model
    Pessoa pessoa = new Pessoa();
    PessoaDAO pessoadao = new PessoaDAO(calendario);
    UsuarioDAO usuariodao = new UsuarioDAO(pessoadao, calendario);
    Usuario usuarioLogado = null;
    EventoDAO eventodao = new EventoDAO(pessoadao, calendario);
    Evento evento = eventodao.retornaEvento();
    Fornecedor fornecedor = new Fornecedor();
    FornecedorDAO fornecedordao = new FornecedorDAO(calendario);
    Mensagens mensagens = new Mensagens();
    MensagensDAO mensagensdao = new MensagensDAO(calendario);
    Presentes presentes = new Presentes();
    PresentesDAO presentesdao = new PresentesDAO(calendario);
    ConvidadoIndividual conviteindividual = new ConvidadoIndividual();
    ConvidadoIndividualDAO conviteindividualdao = new ConvidadoIndividualDAO(pessoadao, calendario);    
    ConvidadoFamilia convitefamilia = new ConvidadoFamilia();
    ConvidadoFamiliaDAO convitefamiliadao = new ConvidadoFamiliaDAO(conviteindividualdao, evento, calendario);

    //view
    GUI gui = new GUI();

    //Main
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
                            opc = gui.menuNoivos(usuarioLogado, calendario);
                            switch (opc) {
                                case 1:
                                    //ccf = Controller Convite Familia
                                    ccf.controllerCrudFamilia(gui, usuarioLogado, pessoadao, pessoa, evento, conviteindividual, conviteindividualdao, convitefamilia, convitefamiliadao, calendario);
                                    break;

                                case 2:
                                    //cci = Controller Convidado Individual
                                    cci.controllerCrudConvidado(gui, usuarioLogado, pessoadao, pessoa, conviteindividualdao, calendario);
                                    break;

                                case 3:
                                    //cm = Controller Mensagens
                                    cm.controllerVerMensagens(mensagensdao);
                                    break;

                                case 4:
                                    //cp = Controller Presentes
                                    cp.controllerVerPresentes(gui, usuarioLogado, presentesdao, calendario);
                                    break;

                                case 5:
                                    //cf = Controller Fornecedor
                                    cf.controllerVerPagamentos(fornecedordao);
                                    break;

                                case 6:
                                    //cc = Controller Calendario
                                    this.calendario = cc.controllerCrudIncrementaData(gui, usuarioLogado, calendarioDAO, calendario);
                                    break;

                                case 7:
                                    //cr = Controller Relatorios
                                    cr.controllerRelatorios(gui, usuarioLogado, fornecedordao, mensagensdao, conviteindividualdao, convitefamiliadao, calendario);
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
                        if (usuarioLogado.getTipo().equals("Admin")) {
                            opc = gui.menuAdmin(usuarioLogado, calendario);
                            switch (opc) {
                                case 1:
                                    //ccf = Controller Convite Familia
                                    ccf.controllerCrudFamilia(gui, usuarioLogado, pessoadao, pessoa, evento, conviteindividual, conviteindividualdao, convitefamilia, convitefamiliadao, calendario);
                                    break;

                                case 2:
                                    //cci = Controller Convidado Individual
                                    cci.controllerCrudConvidado(gui, usuarioLogado, pessoadao, pessoa, conviteindividualdao, calendario);
                                    break;

                                case 3:
                                    //cu = Controller Usuarios
                                    cu.controllerCrudUsuarios(gui, usuarioLogado, usuariodao, pessoadao, pessoa, calendario);
                                    break;

                                case 4:
                                    //cm = Controller Mensagens
                                    cm.controllerCrudMensagens(gui, usuarioLogado, usuariodao, pessoadao, pessoa, evento, mensagens, mensagensdao, calendario);
                                    break;

                                case 5:
                                    //cp = Controller Presentes
                                    cp.controllerCrudPresentes(gui, usuarioLogado, presentes, presentesdao, calendario);
                                    break;

                                case 6:
                                    //cf = Controller Fornecedores
                                    cf.controllerCrudFornecedores(gui, usuarioLogado, fornecedor, fornecedordao, calendario);
                                    break;

                                case 7:
                                    //cc = Controller Calendario
                                    this.calendario = cc.controllerCrudIncrementaData(gui, usuarioLogado, calendarioDAO, calendario);
                                    break;

                                case 8:
                                    //cr = Controller Relatorios
                                    cr.controllerRelatorios(gui, usuarioLogado, fornecedordao, mensagensdao, conviteindividualdao, convitefamiliadao, calendario);
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
                        if (usuarioLogado.getTipo().equals("Cerimonial")) {
                            opc = gui.menuCerimonial(usuarioLogado, calendario);
                            switch (opc) {
                                case 1:
                                    //Ver Convites Família
                                    ccf.controllerVerConvitesFamilia(convitefamiliadao);
                                    break;

                                case 2:
                                    //cm = Controller Mensagens
                                    cm.controllerEnviarMensagens(evento, mensagens, mensagensdao, calendario);
                                    break;

                                case 3:                                    
                                    //cp = Controller Presentes
                                    cp.controllerDarPresentes(gui, presentesdao, calendario);
                                    break;

                                case 4:
                                    //cr = Controller Relatorios
                                    cr.controllerRelatorios(gui, usuarioLogado, fornecedordao, mensagensdao, conviteindividualdao, convitefamiliadao, calendario);
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
                    opc = 0;
                    while (opc != -1) {                        
                        opc = gui.menuConvidado();
                        switch (opc) {
                            case 1:
                                //cm = Controller Mensagens
                                cm.controllerEnviarMensagens(evento, mensagens, mensagensdao, calendario);
                                break;

                            case 2:                            
                                //cp = Controller Presentes
                                cp.controllerDarPresentes(gui, presentesdao, calendario);                                    
                                break;

                            case 3:
                                //ccf = Controller Convite Familia
                                ccf.controllerConfirmarFamiliares(gui, conviteindividual, conviteindividualdao, convitefamilia, convitefamiliadao, calendario);
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
