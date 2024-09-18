package controller;

import model.Evento;
import model.EventoDAO;
import model.PessoaDAO;
import model.Usuario;
import view.GUI;

public class Main {

    public static void main(String[] args) {

        //cci = Controller Convidado Individual!
        ControllerConvidadoIndividual cci = new ControllerConvidadoIndividual();
        PessoaDAO pessoadao = new PessoaDAO();
        EventoDAO eventodao = new EventoDAO(pessoadao);
        Evento evento = eventodao.retornaEvento();
        GUI gui = new GUI();
        Usuario usuarioLogado = null;

        int mainOpc = 0;

        while (mainOpc != -1) {

            mainOpc = gui.menuBoasVindas(evento);

            switch (mainOpc) {
                case 1:
                    /*usuarioLogado = gui.login();

                    if (usuarioLogado == null) {
                        break;
                    }*/

                    mainOpc = gui.menuAdmin();

                    while (mainOpc != -1) {
                        switch (mainOpc) {
                            case 1:
                                //ccf = Controller Confidado Familia
                                //ccf.controllerCrudFamilia();
                                break;

                            case 2:
                                //cci = Controller Convidado Individual
                                cci.controllerCrudConvidado();
                                break;

                            case 3:
                                //cm = Controller Mensagens
                                //cm.controllerVerMensagens();
                                break;

                            case 4:
                                //cp = Controller Pagamentos
                                //cp.controllerVerPagamentos();
                                break;

                            case 5:
                                //cc = Controller Calendario
                                //cc.controllerIncrementaData();
                                break;

                            case 6:
                                //Voltar
                                mainOpc = -1;
                                break;

                            default:
                                mainOpc = 0;
                                break;
                        }
                    }
                    break;

                /*case 1:
                    usuarioLogado = gui.login();
                    gui.menuAdmin();
                    break;*/
                case 2:
                    mainOpc = gui.menuConvidado();
                    switch (mainOpc) {
                        case 1:
                            //cp = Controller Presentes
                            //cp.controllerDarPresente(); 
                            gui.crudPresentesConvidado();
                            break;

                        case 2:
                            //cm = Controller Mensagens
                            //cm.controllerEnviarMensagem();
                            break;

                        case 3:
                            //ccf = Controller Convidado Familia
                            //ccf.controllerConfirmarFamiliares();
                            break;

                        case 0:
                            //Voltar
                            mainOpc = -1;
                            break;

                        default:
                            mainOpc = 0;
                            break;
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

        /*
        //Testes
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
}
