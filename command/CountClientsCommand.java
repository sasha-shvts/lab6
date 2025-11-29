package command;

import menu.Menu;
import model.MobileCompany;

public class CountClientsCommand implements Command {

    private final Menu menu;

    public CountClientsCommand(Menu menu) {
        this.menu = menu;
    }

    @Override
    public String description() {
        return "Підрахувати клієнтів";
    }

    @Override
    public int execute(String[] params) {
        MobileCompany company = menu.getCompany();
        int total = company.getTotalClients();
        System.out.println("Загальна кількість клієнтів: " + total);
        return 0;
    }
}
