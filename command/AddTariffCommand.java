package command;

import menu.Menu;
import model.*;
import java.util.Scanner;

public class AddTariffCommand implements Command {

    private final Menu menu;
    private final Scanner scanner;

    public AddTariffCommand(Menu menu, Scanner scanner) {
        this.menu = menu;
        this.scanner = scanner;
    }

    @Override
    public String description() {
        return "Додати новий тариф";
    }

    @Override
    public int execute(String[] params) {
        // беремо поточну компанію з меню (після load тут буде компанія з файлу)
        MobileCompany company = menu.getCompany();

        System.out.println("Тип тарифу (business/family/student): ");
        String type = scanner.nextLine().trim().toLowerCase();

        System.out.print("Назва: ");
        String name = scanner.nextLine().trim();

        System.out.print("Місячна плата: ");
        double fee = Double.parseDouble(scanner.nextLine().trim());

        System.out.print("Хвилини: ");
        int minutes = Integer.parseInt(scanner.nextLine().trim());

        System.out.print("Мегабайти: ");
        int mb = Integer.parseInt(scanner.nextLine().trim());

        System.out.print("SMS: ");
        int sms = Integer.parseInt(scanner.nextLine().trim());

        System.out.print("Кількість клієнтів на тарифі: ");
        int clients = Integer.parseInt(scanner.nextLine().trim());

        Tariff tariff;
        switch (type) {
            case "business":
                System.out.print("Знижка (0..1): ");
                double discount = Double.parseDouble(scanner.nextLine().trim());
                tariff = new BusinessTariff(name, fee, minutes, mb, sms, clients, discount);
                break;
            case "family":
                System.out.print("Додаткових номерів: ");
                int extra = Integer.parseInt(scanner.nextLine().trim());
                tariff = new FamilyTariff(name, fee, minutes, mb, sms, clients, extra);
                break;
            case "student":
            default:
                System.out.print("Нічних хвилин: ");
                int night = Integer.parseInt(scanner.nextLine().trim());
                tariff = new StudentTariff(name, fee, minutes, mb, sms, clients, night);
                break;
        }

        company.addTariff(tariff);
        System.out.println("Тариф додано: " + tariff);
        return 0;
    }
}
