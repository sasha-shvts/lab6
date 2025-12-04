package command;

import service.MobileCompanyService;

import java.io.IOException;
import java.util.Scanner;

/**
 * Команда для завантаження даних мобільної компанії з файлу.
 * Викликає сервіс для читання даних і виводить коротку інформацію про результат.
 */
public class LoadCommand implements Command {

    private final MobileCompanyService service;

    private final Scanner scanner;

    /**
     * Створює команду завантаження даних з файлу.
     *
     * @param service сервіс мобільної компанії, що містить логіку завантаження
     * @param scanner джерело введення даних (наприклад, System.in)
     */
    public LoadCommand(MobileCompanyService service, Scanner scanner) {
        this.service = service;
        this.scanner = scanner;
    }

    @Override
    public String description() {
        return "Завантажити дані з файлу";
    }

    /**
     * Виконує завантаження даних з указаного користувачем файлу.
     * У разі успіху виводить кількість тарифів, у разі помилки показує повідомлення з текстом винятку.
     *
     * @return код завершення команди (0 у разі успішного виконання або обробленої помилки)
     */
    @Override
    public int execute() {
        System.out.print("Введіть ім'я файлу (наприклад, data.dat): ");
        String file = scanner.nextLine().trim();

        try {
            service.loadFromFile(file);
            System.out.println("Дані завантажено з файлу " + file);
            System.out.println("Тарифів у компанії: " + service.getTariffs().size());
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Помилка завантаження: " + e.getMessage());
        }
        return 0;
    }
}
