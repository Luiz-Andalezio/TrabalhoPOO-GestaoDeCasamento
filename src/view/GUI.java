package view;

import javax.swing.JOptionPane;
import model.Evento;
import model.PresentesDAO;
import model.Usuario;
import model.UsuarioDAO;

public class GUI {

    //TELA DE BOAS VINDAS
    public int menuBoasVindas(Evento evento) {
        int opc;

        StringBuilder m = new StringBuilder("");

        m.append("---- Boas Vindas ao Software de Casamento de ").append(evento.getPessoaNoivo().getNome()).append(" e ").append(evento.getPessoaNoiva().getNome()).append(" ----");
        m.append("\n\n1- Logar.");
        m.append("\n2- Entrar como convidado.");
        m.append("\n\n0 - Sair.");

        opc = Integer.parseInt(JOptionPane.showInputDialog(m));

        return opc;
    }    

    //LOGINS
    public Usuario login(UsuarioDAO usuariodao) {
        Usuario usuario = null;
        String loginAtt, senhaAtt;

        while (usuario==null) {
            loginAtt = JOptionPane.showInputDialog("Informe seu login: ");
            senhaAtt = JOptionPane.showInputDialog("Informe sua senha: ");
            
            usuario = usuariodao.retornaUsuario(loginAtt, senhaAtt);

            if (usuario == null) {
                JOptionPane.showMessageDialog(null, "Login ou senha inválidos. Tente novamente.");
            }
        }
        return usuario;
    }

    //MENUS
    public int menuNoivos(Usuario usuarioLogado) {
        int opc;

        StringBuilder m = new StringBuilder();

        m.append(headerAdmin(usuarioLogado));

        m.append("----- Menu dos Noivos -----");
        m.append("\n\n1- Crud de famílias.");
        m.append("\n2- Crud de convites individuais.");
        m.append("\n3- Visualizar presentes recebidos.");
        m.append("\n4- Pagamentos.");
        m.append("\n5- Incrementar dias.");
        m.append("\n6- Relatórios.");
        m.append("\n\n0- Voltar.");

        opc = Integer.parseInt(JOptionPane.showInputDialog(m));

        return opc;
    }

    public int menuAdmin(Usuario usuarioLogado) {
        int opc;

        StringBuilder m = new StringBuilder();

        m.append(headerAdmin(usuarioLogado));

        m.append("----- Menu do Administrador -----");
        m.append("\n\n1- Crud de famílias.");
        m.append("\n2- Crud de convites individuais.");
        m.append("\n3- Crud de usuários.");
        m.append("\n4- Visualizar presentes entregues aos noivos.");
        m.append("\n5- Visualizar pagamentos.");
        m.append("\n6- Incrementar dias.");
        m.append("\n7- Relatórios.");
        m.append("\n\n0- Voltar.");

        opc = Integer.parseInt(JOptionPane.showInputDialog(m));

        return opc;
    }

    public int menuCerimonial(Usuario usuarioLogado) {
        int opc;

        StringBuilder m = new StringBuilder();

        m.append(headerAdmin(usuarioLogado));

        m.append("----- Menu do Cerimonial -----");
        m.append("\n\n1- Ver convites.");
        m.append("\n2- Visualizar presentes entregues aos noivos.");
        m.append("\n3- Visualizar pagamentos.");
        m.append("\n4- Incrementar dias.");
        m.append("\n5- Relatórios.");
        m.append("\n\n0- Voltar.");

        opc = Integer.parseInt(JOptionPane.showInputDialog(m));

        return opc;
    }

    public int menuConvidado() {
        int opc;

        StringBuilder m = new StringBuilder();
        
        m.append("----- Menu de Convidados -----");
        m.append("\n\n1- Enviar presente.");
        m.append("\n2- Enviar mensagem.");
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

        m.append("----- Menu de Usuarios -----");
        m.append("\n\n1- Cadastrar usuarios.");
        m.append("\n2- Editar usuarios.");
        m.append("\n3- Exibir usuarios.");
        m.append("\n4- Desfazer usuarios.");
        m.append("\n\n0- Voltar.");

        opc = Integer.parseInt(JOptionPane.showInputDialog(m));

        return opc;
    }

    public int crudPresentesConvidado(PresentesDAO presentesdao) {
        int opc;

        StringBuilder m = new StringBuilder();

        m.append("\nDê um presente para os pombinhos!");
        m.append("\n\n\tPRESENTES: ");
        m.append(presentesdao.lerPesentesConvidado());
        m.append("\n\n0- Voltar.");
        opc = Integer.parseInt(JOptionPane.showInputDialog(m));

        return opc;
    }

    public int crudPresentesAdmin(PresentesDAO presentesdao, Usuario usuarioLogado) {
        int opc;

        StringBuilder m = new StringBuilder();

        m.append(headerAdmin(usuarioLogado));

        m.append("\nDê um presente para os pombinhos!");
        m.append("\n\n\tPRESENTES: ");
        m.append(presentesdao.lerPesentesAdmin(usuarioLogado));
        m.append("\n\n0- Voltar.");
        opc = Integer.parseInt(JOptionPane.showInputDialog(m));

        return opc;
    }
    
    public int crudPagamento(Usuario usuarioLogado) {
        int opc;

        StringBuilder m = new StringBuilder();

        m.append(headerAdmin(usuarioLogado));

        m.append("---- Pagamentos ----");
        m.append("\n1- Visualisar pagamentos.");

        opc = Integer.parseInt(JOptionPane.showInputDialog(m));

        return opc;
    }

    public StringBuilder headerAdmin(Usuario usuarioLogado) {
        //Aqui aparecerá o dia do calendário.
        StringBuilder m = new StringBuilder("--------------------------------------------------------");
        m.append("\nUsuário: ").append(usuarioLogado.getPessoa().getNome());
        m.append("\nTipo: ").append(usuarioLogado.getTipo());
        m.append("\n--------------------------------------------------------\n\n");

        return m;
    }
}
