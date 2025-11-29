package command;

import menu.Menu;
import model.MobileCompany;

import java.io.IOException;
import java.util.Scanner;

public class SaveCommand implements Command {

    private final Menu menu;
    private final Scanner scanner;

    public SaveCommand(Menu menu, Scanner scanner) {
        this.menu = menu;
        this.scanner = scanner;
    }

    @Override
    public String description() {
        return "Зберегти дані у файл";
    }

    @Override
    public int execute(String[] params) {
        MobileCompany company = menu.getCompany();

        System.out.print("Введіть ім'я файлу (наприклад, data.dat): ");
        String file = scanner.nextLine().trim();
        try {
            company.saveToFile(file);
            System.out.println("Дані збережено у файл " + file);
        } catch (IOException e) {
            System.out.println("Помилка збереження: " + e.getMessage());
        }
        return 0;
    }
}
