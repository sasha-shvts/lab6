package command;

import menu.Menu;
import model.MobileCompany;
import model.Tariff;

import java.util.Scanner;

public class SortTariffsCommand implements Command {

    private final Menu menu;
    private final Scanner scanner;

    public SortTariffsCommand(Menu menu, Scanner scanner) {
        this.menu = menu;
        this.scanner = scanner;
    }

    @Override
    public String description() {
        return "Відсортувати тарифи";
    }

    @Override
    public int execute(String[] params) {
        MobileCompany company = menu.getCompany();

        System.out.println("Сортувати за (fee/clients): ");
        String by = scanner.nextLine().trim().toLowerCase();

        if (by.equals("clients")) {
            company.sortTariffsByClients();
        } else {
            company.sortTariffsByMonthlyFee();
        }

        System.out.println("Відсортований список тарифів:");
        for (Tariff t : company.getTariffs()) {
            System.out.println(t);
        }
        return 0;
    }
}
