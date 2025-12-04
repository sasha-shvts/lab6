package command;

import model.*;
import service.MobileCompanyService;

import java.util.Scanner;

/**
 * Команда для додавання нового тарифу через консольний інтерфейс.
 * Зчитує всі необхідні параметри тарифу, створює відповідний об'єкт і додає його в сервіс.
 */
public class AddTariffCommand implements Command {

    private final MobileCompanyService service;

    private final Scanner scanner;

    /**
     * Створює нову команду додавання тарифу.
     *
     * @param service сервіс мобільного оператора для роботи з тарифами
     * @param scanner джерело введення даних (зазвичай System.in)
     */
    public AddTariffCommand(MobileCompanyService service, Scanner scanner) {
        this.service = service;
        this.scanner = scanner;
    }

    @Override
    public String description() {
        return "Додати новий тариф";
    }

    /**
     * Виконує команду додавання нового тарифу.
     * Послідовно запитує користувача про тип тарифу та його параметри, створює відповідний тариф і передає його в сервіс.
     *
     * @return код завершення команди (0 означає успішне виконання)
     */
    @Override
    public int execute() {
        System.out.println("Тип тарифу (business/family/student): ");
        String type = scanner.nextLine().trim().toLowerCase(); // нормалізація типу введення

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

        service.addTariff(tariff);
        System.out.println("Тариф додано: " + tariff);
        return 0;
    }
}
