package view;

import javax.swing.JOptionPane;
import model.Evento;
import model.PresentesDAO;
import model.Usuario;
import model.UsuarioDAO;

public class GUI {

    PresentesDAO presentesdao = new PresentesDAO();
    UsuarioDAO usuarioDAO;
    Usuario usuarioLogado = new Usuario();

    //public int menuBoasVindas(){
    //    int opc = -1;
    //
    //    String m = "";
    //
    //    m += "------ Boas Vindas ao Casamento de " + eventoDAO.getPessoaNoivo().getNome() + " e " + eventoDAO.getPessoaNoiva().getNome() + "------";
    //    m += "\n\n1- Entrar como Administrador.";
    //    m += "\n2- Entrar como membro de Familia.";
    //    m += "0- Sair.";
    //    return opc;
    //}

    public int menuBoasVindas(Evento evento) {
        int opc;

        StringBuilder m = new StringBuilder("");

        m.append("---- Boas Vindas ao Casamento de ").append(evento.getPessoaNoivo().getNome()).append(" e ").append(evento.getPessoaNoiva().getNome()).append(" ----");
        m.append("\n\n1- Entrar como Administrador.");
        m.append("\n2- Entrar como membro de Familia.");
        m.append("\n\n0 - Sair.");

        opc = Integer.parseInt(JOptionPane.showInputDialog(m));

        return opc;
    }    

    public Usuario login() {
        Usuario usuario = null;
        String login, senha;
        StringBuilder m;

        while (usuario == null) {
            m = new StringBuilder("Informe seu login: ");
            login = JOptionPane.showInputDialog(m);

            m = new StringBuilder("Informe sua senha: ");
            senha = JOptionPane.showInputDialog(m);

            if (senha == null || login == null) {
                return null;
            }
            
            usuario = usuarioDAO.retornaUsuario(login, senha);

            if (usuario == null) {
                JOptionPane.showMessageDialog(null, "Login ou senha inválidos. Tente novamente.");
            }
        }
        return usuario;
    }

    /*
        public Usuario login(){
        Usuario usuario;
        StringBuilder m = new StringBuilder();
        String login;
        String senha;
        m.append("Informe seu login: ");
        login = JOptionPane.showInputDialog(m);
        m = new StringBuilder("Informe sua senha");
        senha = JOptionPane.showInputDialog(m);

        usuario = usuarioDAO.retornaUsuario(login, senha);

        return usuario;
    }*/

    public int menuConvidado() {
        int opc;

        StringBuilder m = new StringBuilder();

        
        m.append("----- Menu de Convidados -----");
        m.append("\n\n1- Enviar presente.");
        m.append("\n2- Enviar mensagem.");
        m.append("\n3- Informar presença da Família.");
        m.append("\n\n0 - Sair.");

        opc = Integer.parseInt(JOptionPane.showInputDialog(m));

        return opc;
    }

    public int menuAdmin() {
        int opc;

        StringBuilder m = new StringBuilder();

        m.append("----- Menu de Administrador -----");
        m.append("\n\n1- Crud Famílias.");
        m.append("\n2- Crud Convidados.");
        m.append("\n3- Visualizar presentes recebidos.");
        m.append("\n4- Pagamentos.");
        m.append("\n5- Incrementar dias.");
        m.append("\n6- Voltar.");
        m.append("\n\n0- Sair.");

        opc = Integer.parseInt(JOptionPane.showInputDialog(m));

        return opc;
    }

    public int crudConvFamilia() {
        int opc;

        StringBuilder m = new StringBuilder();

        m.append("1- Convidar Famílias.");
        m.append("\n2- Editar Famílias.");
        m.append("\n3- Exibir Famílias.");
        m.append("\n4- Excluir Famlílias.");
        m.append("\n5- Voltar");
        m.append("\n\n0- Sair.");

        opc = Integer.parseInt(JOptionPane.showInputDialog(m));

        return opc;
    }

    public int crudConvIndividual() {
        int opc;

        StringBuilder m = new StringBuilder();

        m.append("1- Convidar Pessoas.");
        m.append("\n2- Editar Pessoas.");
        m.append("\n3- Exibir Pessoas.");
        m.append("\n4- Excluir Pessoas.");
        m.append("\n5- Voltar.");
        m.append("\n\n0- Sair.");

        opc = Integer.parseInt(JOptionPane.showInputDialog(m));

        return opc;
    }

    public int crudPresentesConvidado() {
        int opc;

        StringBuilder m = new StringBuilder();

        m.append("\nDê um presente para os pombinhos!");
        m.append("\n\n\tPRESENTES: ");
        m.append(presentesdao.lerPesentesConvidado());
        m.append("\n\n0- Voltar.");
        opc = Integer.parseInt(JOptionPane.showInputDialog(m));

        return opc;
    }

    public int crudPresentesAdmin() {
        int opc;

        StringBuilder m = new StringBuilder();

        m.append("\nDê um presente para os pombinhos!");
        m.append("\n\n\tPRESENTES: ");
        m.append(presentesdao.lerPesentesAdmin(usuarioLogado));
        m.append("\n\n0- Voltar.");
        opc = Integer.parseInt(JOptionPane.showInputDialog(m));

        return opc;
    }
    
    public int crudPagamento() {
        int opc;

        StringBuilder m = new StringBuilder();

        m.append("---- Pagamentos ----");
        m.append("\n1- Visualisar pagamentos.");

        opc = Integer.parseInt(JOptionPane.showInputDialog(m));

        return opc;
    }

    public StringBuilder headerAdmin(Usuario usuario) {
        //Aqui aparecerá o dia do calendário.
        StringBuilder m = new StringBuilder("-----------------");
        m.append("\nUsuario: " + usuario.getPessoa().getNome());
        m.append("\n " + usuario.getTipo());
        m.append("\n--------------");

        return m;
    }
}
