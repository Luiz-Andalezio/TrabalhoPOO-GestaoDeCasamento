package model;

public class PresentesDAO {

    Presentes[] presentes = new Presentes[10];

    public PresentesDAO() {

        Presentes presente1 = new Presentes();
        presente1.setId(1);
        presente1.setNome("Geladeira");
        presente1.setTipo("Eletrodoméstico");
        presente1.setValor(1500);

        Presentes presente2 = new Presentes();
        presente2.setId(2);
        presente2.setNome("Máquina de Lavar");
        presente2.setTipo("Eletrodoméstico");
        presente2.setValor(1200);

        Presentes presente3 = new Presentes();
        presente3.setId(3);
        presente3.setNome("Micro-ondas");
        presente3.setTipo("Eletrodoméstico");
        presente3.setValor(600);

        Presentes presente4 = new Presentes();
        presente4.setId(4);
        presente4.setNome("Conjunto de Panelas");
        presente4.setTipo("Cozinha");
        presente4.setValor(300);

        Presentes presente5 = new Presentes();
        presente5.setId(5);
        presente5.setNome("Jogo de Cama");
        presente5.setTipo("Roupa de Cama");
        presente5.setValor(200);

        Presentes presente6 = new Presentes();
        presente6.setId(6);
        presente6.setNome("TV 42 polegadas");
        presente6.setTipo("Eletrônico");
        presente6.setValor(1800);

        Presentes presente7 = new Presentes();
        presente7.setId(7);
        presente7.setNome("Aparelho de Jantar");
        presente7.setTipo("Mesa");
        presente7.setValor(400);

        Presentes presente8 = new Presentes();
        presente8.setId(8);
        presente8.setNome("Cafeteira");
        presente8.setTipo("Eletrodoméstico");
        presente8.setValor(250);

        Presentes presente9 = new Presentes();
        presente9.setId(9);
        presente9.setNome("Ventilador");
        presente9.setTipo("Eletrodoméstico");
        presente9.setValor(150);

        Presentes presente10 = new Presentes();
        presente10.setId(10);
        presente10.setNome("Torradeira");
        presente10.setTipo("Eletrodoméstico");
        presente10.setValor(100);

        presentes[0] = presente1;
        presentes[1] = presente2;
        presentes[2] = presente3;
        presentes[3] = presente4;
        presentes[4] = presente5;/* 
        presentes[5] = presente6;
        presentes[6] = presente7;
        presentes[7] = presente8;
        presentes[8] = presente9;
        presentes[9] = presente10;*/
    }

    public String lerPesentesConvidado(/*Usuario usuarioLogado*/) {
        String m = "";
        for (int i = 0; i < presentes.length; i++)
            if (presentes[i] != null) {
                m += presentes[i].toString(/*usuarioLogado*/) + "\n";
            }
        return m;
    }

    public String lerPesentesAdmin(Usuario usuarioLogado) {
        String m = "";
        for (int i = 0; i < presentes.length; i++)
            if (presentes[i] != null) {
                m += presentes[i].toString(usuarioLogado) + "\n";
            }
        return m;
    }
}
