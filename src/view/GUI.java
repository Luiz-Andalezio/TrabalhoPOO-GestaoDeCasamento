package view;

import java.time.LocalDate;
import javax.swing.JOptionPane;
import model.ConvidadoFamilia;
import model.ConvidadoFamiliaDAO;
import model.Evento;
import model.PresentesDAO;
import model.Usuario;
import model.UsuarioDAO;

public class GUI {

    //TELA DE BOAS VINDAS
    public int menuBoasVindas(Evento evento) {
        int opc;

        StringBuilder m = new StringBuilder("");

        m.append(evento)
        .append("\n\n1- Entrar como Administrador.")
        .append("\n2- Entrar como Convidado.");
        //m.append("\n3- Informações.");
        m.append("\n\n0 - Sair.");

        opc = Integer.parseInt(JOptionPane.showInputDialog(m));

        return opc;
    }    

    //LOGINS
    public Usuario login(UsuarioDAO usuariodao) {
        Usuario usuario = null;
        String loginAtt, senhaAtt;

        while (usuario == null) {
            loginAtt = JOptionPane.showInputDialog("Informe seu login: ");
            senhaAtt = JOptionPane.showInputDialog("Informe sua senha: ");
            
            usuario = usuariodao.retornaUsuario(loginAtt, senhaAtt);

            if (usuario == null) {
                JOptionPane.showMessageDialog(null, "Login ou senha inválidos. Tente novamente.");
            }
        }
        return usuario;
    }

    public ConvidadoFamilia loginConviteFamilia(ConvidadoFamiliaDAO convitefamiliadao) {
        ConvidadoFamilia convitefamilia = null;
        String acesso;

        while (convitefamilia == null) {
            acesso = JOptionPane.showInputDialog("Informe o acesso do seu Convite Família: ");
            
            convitefamilia = convitefamiliadao.retornaAcessoConviteFamilia(acesso);

            if (convitefamilia == null) {
                JOptionPane.showMessageDialog(null, "Acesso inválido. Tente novamente.");
            }
        }
        return convitefamilia;
    }

    //MENUS
    public int menuNoivos(Usuario usuarioLogado) {
        int opc;

        StringBuilder m = new StringBuilder();

        m.append(headerAdmin(usuarioLogado));

        m.append("----- Menu dos Noivos -----");
        m.append("\n\n1- Crud de famílias.");
        m.append("\n2- Crud de convites individuais.");
        m.append("\n3- Visualizar mensagens recebidas.");  
        m.append("\n4- Visualizar presentes.");
        m.append("\n5- Pagamentos.");
        m.append("\n6- Incrementar dias.");
        m.append("\n7- Relatórios.");
        m.append("\n\n0- Voltar.");

        opc = Integer.parseInt(JOptionPane.showInputDialog(m));

        return opc;
    }

    public int menuAdmin(Usuario usuarioLogado) {
        int opc;

        StringBuilder m = new StringBuilder();

        m.append(headerAdmin(usuarioLogado));

        m.append("----- Menu do Administrador -----");
        m.append("\n\n1- CRUD de famílias.");
        m.append("\n2- CRUD convites individuais.");
        m.append("\n3- CRUD usuários.");        
        m.append("\n4- CRUD mensagens.");
        m.append("\n5- CRUD presentes.");
        m.append("\n6- Visualizar pagamentos.");
        m.append("\n7- Incrementar dias.");
        m.append("\n8- Relatórios.");
        m.append("\n\n0- Voltar.");

        opc = Integer.parseInt(JOptionPane.showInputDialog(m));

        return opc;
    }

    public int menuCerimonial(Usuario usuarioLogado) {
        int opc;

        StringBuilder m = new StringBuilder();

        m.append(headerAdmin(usuarioLogado));

        m.append("----- Menu do Cerimonial -----");
        m.append("\n\n1- Ver Convites Família.");
        m.append("\n2- Enviar mensagem para os noivos.");
        m.append("\n3- Comprar presentes para os noivos.");
        m.append("\n4- Relatórios.");
        m.append("\n\n0- Voltar.");

        opc = Integer.parseInt(JOptionPane.showInputDialog(m));

        return opc;
    }

    public int menuConvidado() {
        int opc;

        StringBuilder m = new StringBuilder();
        
        m.append("----- Menu de Convidados -----");
        m.append("\n\n1- Enviar mensagem para os noivos.");
        m.append("\n2- Comprar presentes para os noivos.");
        m.append("\n3- Informar presença da família.");
        m.append("\n\n0 - Voltar.");

        opc = Integer.parseInt(JOptionPane.showInputDialog(m));

        return opc;
    }

    //CRUDS
    public int crudConvFamilia(Usuario usuarioLogado) {
        int opc;

        StringBuilder m = new StringBuilder();

        m.append("----- Menu de Convites Famílias -----");
        m.append("\n\n1- Gerar Convite Família.");        
        m.append("\n2- Gerar novos codigos de acesso para Convites Família.");
        m.append("\n3- Adicionar pessoas em Convites Família.");        
        m.append("\n4- Excluir pessoas em Convites Família.");
        m.append("\n5- Editar Convites Família.");
        m.append("\n6- Exibir Convites Família.");
        m.append("\n7- Desfazer Convites Famlília.");
        m.append("\n\n0- Voltar.");

        opc = Integer.parseInt(JOptionPane.showInputDialog(m));

        return opc;
    }

    public int crudConvIndividual(Usuario usuarioLogado) {
        int opc;

        StringBuilder m = new StringBuilder();
        
        m.append(headerAdmin(usuarioLogado));

        m.append("----- Menu de Convidados Individuais -----");
        m.append("\n\n1- Gerar convites.");
        m.append("\n2- Editar convites individuais.");
        m.append("\n3- Exibir convites individuais.");
        m.append("\n4- Desfazer convites.");
        m.append("\n\n0- Voltar.");

        opc = Integer.parseInt(JOptionPane.showInputDialog(m));

        return opc;
    }

    public int crudUsuario(Usuario usuarioLogado) {
        int opc;

        StringBuilder m = new StringBuilder();
        
        m.append(headerAdmin(usuarioLogado));

        m.append("----- CRUD Usuários -----");
        m.append("\n\n1- Cadastrar usuários.");
        m.append("\n2- Editar usuários.");
        m.append("\n3- Exibir usuários.");
        m.append("\n4- Desfazer usuários.");
        m.append("\n\n0- Voltar.");

        opc = Integer.parseInt(JOptionPane.showInputDialog(m));

        return opc;
    }

    public int crudMensagem(Usuario usuarioLogado, Evento evento) {
        int opc;

        StringBuilder m = new StringBuilder();

        m.append(headerAdmin(usuarioLogado));

        m.append("----- CRUD Mensagens -----");
        m.append("\n\n1- Exibir mensagens enviadas aos noivos.");
        m.append("\n2- Excluir mensagens enviadas aos noivos.");
        m.append("\n\n0- Voltar.");
        opc = Integer.parseInt(JOptionPane.showInputDialog(m));

        return opc;
    }

    /*
    public int crudMensagem(Usuario usuarioLogado, Evento evento) {
        int opc;

        StringBuilder m = new StringBuilder();

        m.append("----- Menu de Mensagens -----");
        m.append("\n\n1- Editar mensagens.");
        m.append("\n2- Exibir mensagens.");
        m.append("\n3- Excluir mensagens.");
        m.append("\n\n0- Voltar.");
        opc = Integer.parseInt(JOptionPane.showInputDialog(m));

        return opc;
    }
    */

    public int crudPresentesNoivos(Usuario usuarioLogado) {
        int opc;

        StringBuilder m = new StringBuilder();

        m.append(headerAdmin(usuarioLogado));

        m.append("----- Presentes -----");
        m.append("\n\n1- Ver presentes disponíveis.");
        m.append("\n2- Ver presentes comprados.");  
        m.append("\n\n0- Voltar.");
        opc = Integer.parseInt(JOptionPane.showInputDialog(m));

        return opc;
    }

    public int crudPresentesAdmin(Usuario usuarioLogado) {
        int opc;

        StringBuilder m = new StringBuilder();

        m.append(headerAdmin(usuarioLogado));

        m.append("----- CRUD Presentes -----");
        m.append("\n\n1- Registrar presente.");
        m.append("\n2- Editar presentes.");
        m.append("\n3- Exibir presentes disponíveis.");        
        m.append("\n4- Exibir presentes comprados aos noivos.");
        m.append("\n5- Excluir presentes.");     
        m.append("\n\n0- Voltar.");
        opc = Integer.parseInt(JOptionPane.showInputDialog(m));

        return opc;
    }

    public int crudFornecedorAdmin(Usuario usuarioLogado) {
        int opc;

        StringBuilder m = new StringBuilder();

        m.append(headerAdmin(usuarioLogado));

        m.append("----- CRUD Fornecedores -----");
        m.append("\n\n1- Registrar fornecedor.");
        m.append("\n2- Editar fornecedores.");
        m.append("\n3- Exibir fornecedores.");  
        m.append("\n4- Excluir fornecedores.");     
        m.append("\n\n0- Voltar.");
        opc = Integer.parseInt(JOptionPane.showInputDialog(m));

        return opc;
    }

    public int crudPresentesConvidado(PresentesDAO presentesdao) {
        int opc;

        StringBuilder m = new StringBuilder();

        m.append("\nDigite o código do presente à venda que deseja comprar aos pombinhos!");
        m.append("\n\n\tPRESENTES: \n");
        m.append(presentesdao.verPresentesConvidado());
        m.append("\n\n0- Voltar.");
        opc = Integer.parseInt(JOptionPane.showInputDialog(m));

        return opc;
    }    

    public int enviaMensagem(ConvidadoFamilia convitefamilia, Evento evento) {
        int opc;

        StringBuilder m = new StringBuilder();

        m.append("----- Menu de Mensagens -----");
        m.append("\n\n1- Envie uma mensagem para ")
            .append(evento.getPessoaNoivo().getNome())
            .append(" e ")
            .append(evento.getPessoaNoiva().getNome());
        m.append("\n\n0- Voltar.");
        opc = Integer.parseInt(JOptionPane.showInputDialog(m));

        return opc;
    }

    public int crudData(Usuario usuarioLogado, LocalDate calendario) {
        int opc;

        StringBuilder m = new StringBuilder();

        m.append(headerAdmin(usuarioLogado));

        m.append(calendarioFormatado(calendario));

        m.append("\n\n----- Menu de Calendario -----");
        m.append("\n\n1- Adicionar dias ");
        m.append("\n2- Adicionar meses.");
        m.append("\n3- Adicionar anos");
        m.append("\n\n0- Voltar.");
        opc = Integer.parseInt(JOptionPane.showInputDialog(m));

        return opc;
    }

    public StringBuilder headerAdmin(Usuario usuarioLogado) {
        //Aqui aparecerá o dia do calendário.
        StringBuilder m = new StringBuilder("-------------------------------------------------------------------");
        m.append("\nUsuário: ").append(usuarioLogado.getPessoa().getNome());
        m.append("\nTipo: ").append(usuarioLogado.getTipo());
        m.append("\n-------------------------------------------------------------------\n\n");

        return m;
    }

    public StringBuilder headerConvidado(ConvidadoFamilia convitefamilia) {
        //Aqui aparecerá o dia do calendário.
        StringBuilder m = new StringBuilder("-------------------------------------------------------------------");
        m.append("\nConvite Família dos: ").append(convitefamilia.getNomeDaFamilia());
        m.append("\n-------------------------------------------------------------------\n\n");

        return m;
    }

    public String calendarioFormatado(LocalDate calendario){        
        String alteraDia = "";
        if (calendario.getDayOfMonth() < 10){
            alteraDia += "0";
        }
        alteraDia += calendario.getDayOfMonth() + "/";
        if (calendario.getMonthValue() < 10){
            alteraDia += "0";
        }
        alteraDia += calendario.getMonthValue() + "/" + calendario.getYear();
        return alteraDia;
    }
}
