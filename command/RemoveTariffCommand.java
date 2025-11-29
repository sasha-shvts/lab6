package command;

import menu.Menu;
import model.MobileCompany;

import java.util.Scanner;

public class RemoveTariffCommand implements Command {

    private final Menu menu;
    private final Scanner scanner;

    public RemoveTariffCommand(Menu menu, Scanner scanner) {
        this.menu = menu;
        this.scanner = scanner;
    }

    @Override
    public String description() {
        return "Видалити тариф";
    }

    @Override
    public int execute(String[] params) {
        MobileCompany company = menu.getCompany();

        System.out.print("Введіть назву тарифу для видалення: ");
        String name = scanner.nextLine().trim();
        boolean removed = company.removeTariff(name);
        if (removed) {
            System.out.println("Тариф \"" + name + "\" видалено.");
        } else {
            System.out.println("Тариф не знайдено.");
        }
        return 0;
    }
}
