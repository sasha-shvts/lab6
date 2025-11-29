package command;

import menu.Menu;
import model.MobileCompany;
import model.Tariff;

import java.util.Scanner;

public class EditTariffCommand implements Command {

    private final Menu menu;
    private final Scanner scanner;

    public EditTariffCommand(Menu menu, Scanner scanner) {
        this.menu = menu;
        this.scanner = scanner;
    }

    @Override
    public String description() {
        return "Редагувати тариф (місячна плата)";
    }

    @Override
    public int execute(String[] params) {
        MobileCompany company = menu.getCompany();

        System.out.print("Назва тарифу для редагування: ");
        String name = scanner.nextLine().trim();

        for (Tariff t : company.getTariffs()) {
            if (t.getName().equalsIgnoreCase(name)) {
                System.out.print("Нова місячна плата: ");
                double fee = Double.parseDouble(scanner.nextLine().trim());
                t.setMonthlyFee(fee);
                System.out.println("Оновлено: " + t);
                return 0;
            }
        }
        System.out.println("Тариф не знайдено.");
        return 0;
    }
}
