package command;

import service.MobileCompanyService;

import java.io.IOException;
import java.util.Scanner;

/**
 * Команда для збереження даних мобільної компанії у файл.
 * Запитує у користувача ім'я файлу та делегує операцію збереження сервісу.
 */
public class SaveCommand implements Command {

    private final MobileCompanyService service;

    private final Scanner scanner;

    /**
     * Створює команду збереження даних у файл.
     *
     * @param service сервіс мобільної компанії для виконання операції збереження
     * @param scanner джерело введення даних (наприклад, System.in)
     */
    public SaveCommand(MobileCompanyService service, Scanner scanner) {
        this.service = service;
        this.scanner = scanner;
    }

    @Override
    public String description() {
        return "Зберегти дані у файл";
    }

    /**
     * Виконує збереження даних у вказаний користувачем файл.
     * У разі успіху виводить підтвердження, у разі помилки показує повідомлення з текстом винятку.
     *
     * @return код завершення команди (0 у разі успішного виконання або обробленої помилки)
     */
    @Override
    public int execute() {
        System.out.print("Введіть ім'я файлу (наприклад, data.dat): ");
        String file = scanner.nextLine().trim();

        try {
            service.saveToFile(file);
            System.out.println("Дані збережено у файл " + file);
        } catch (IOException e) {
            System.out.println("Помилка збереження: " + e.getMessage());
        }
        return 0;
    }
}
