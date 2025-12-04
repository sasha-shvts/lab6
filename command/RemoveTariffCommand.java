package command;

import service.MobileCompanyService;

import java.util.Scanner;

/**
 * Команда для видалення тарифу з мобільної компанії.
 * Запитує назву тарифу та намагається видалити його через сервіс.
 */
public class RemoveTariffCommand implements Command {

    private final MobileCompanyService service;

    private final Scanner scanner;

    /**
     * Створює команду видалення тарифу.
     *
     * @param service сервіс мобільної компанії для операцій з тарифами
     * @param scanner джерело введення даних (наприклад, System.in)
     */
    public RemoveTariffCommand(MobileCompanyService service, Scanner scanner) {
        this.service = service;
        this.scanner = scanner;
    }


    @Override
    public String description() {
        return "Видалити тариф";
    }

    /**
     * Виконує видалення тарифу за введеною користувачем назвою.
     * Виводить повідомлення про успіх або про те, що тариф не знайдено.
     *
     * @return код завершення команди (0 у разі успішного виконання)
     */
    @Override
    public int execute() {
        System.out.print("Введіть назву тарифу для видалення: ");
        String name = scanner.nextLine().trim();

        boolean removed = service.removeTariff(name);
        if (removed) {
            System.out.println("Тариф \"" + name + "\" видалено.");
        } else {
            System.out.println("Тариф не знайдено.");
        }
        return 0;
    }
}
