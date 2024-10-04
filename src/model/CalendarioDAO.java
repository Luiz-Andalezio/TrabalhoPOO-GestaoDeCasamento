package model;

import java.time.LocalDate;

public class CalendarioDAO {

    public CalendarioDAO() {

    }

    public LocalDate adicionaDia(LocalDate calendario, int dias) {
        calendario = calendario.plusDays(dias);
        return calendario;
    }

    public LocalDate adicionaMeses(LocalDate calendario, int meses) {
        calendario = calendario.plusMonths(meses);
        return calendario;
    }

    public LocalDate adicionaAnos(LocalDate calendario, int anos) {
        calendario = calendario.plusYears(anos);
        return calendario;
    }
}
