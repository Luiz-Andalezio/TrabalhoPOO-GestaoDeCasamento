package controller;

import java.time.LocalDateTime;
import javax.swing.JOptionPane;
import model.CalendarioDAO;
import model.Usuario;
import view.GUI;

public class ControllerCalendario {

    public LocalDateTime controllerCrudIncrementaData(GUI gui, Usuario usuarioLogado, CalendarioDAO calendarioDAO, LocalDateTime calendario) {
        int menuCalendarioOpc = 0;

        while (menuCalendarioOpc != -1) {
            menuCalendarioOpc = gui.crudData(usuarioLogado, calendario);
            switch (menuCalendarioOpc) {
                case 1:
                    int dias = Integer.parseInt(JOptionPane.showInputDialog("Insira o número de dias que deseja incrementar: \n\n-0 Voltar."));
                    if (0 == dias) {

                    } else {
                        calendario = calendarioDAO.adicionaDia(calendario, dias);
                    }
                    break;

                case 2:
                    int meses = Integer.parseInt(JOptionPane.showInputDialog("Insira o número de meses que deseja incrementar: \n\n-0 Voltar."));
                    if (0 == meses) {

                    } else {
                        calendario = calendarioDAO.adicionaMeses(calendario, meses);
                    }
                    break;

                case 3:
                    int anos = Integer.parseInt(JOptionPane.showInputDialog("Insira o número de anos que deseja incrementar: \n\n-0 Voltar."));
                    if (0 == anos) {

                    } else {
                        calendario = calendarioDAO.adicionaAnos(calendario, anos);
                    }
                    break;

                case 0:
                    menuCalendarioOpc = -1;
                    break;

                default:
                    menuCalendarioOpc = -1;
                    break;
            }
        }
        return calendario;
    }
}
