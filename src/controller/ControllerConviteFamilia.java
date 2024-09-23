package controller;

import model.ConvidadoFamiliaDAO;
import model.Pessoa;
import model.PessoaDAO;
import model.Usuario;
import view.GUI;

public class ControllerConviteFamilia {

    public void controllerCrudFamilia(GUI gui, Usuario usuarioLogado, PessoaDAO pessoadao, Pessoa pessoa, ConvidadoFamiliaDAO convitefamilia) {
        StringBuilder m;
        int menuFamiliaOpc = 0;

        while (menuFamiliaOpc != -1) {
            menuFamiliaOpc = gui.crudConvFamilia(usuarioLogado);
            switch (menuFamiliaOpc) {
                case 1:
                    break;
                case 5:
                    menuFamiliaOpc = -1;
                    break;
                case 0:
                    menuFamiliaOpc = -1;
                    break;
                default:
                    menuFamiliaOpc = 0;
                    break;
            }
        }
    }
}
