package command;

import menu.Menu;
import model.MobileCompany;

import java.io.IOException;
import java.util.Scanner;

public class LoadCommand implements Command {

    private final Menu menu;
    private final Scanner scanner;

    public LoadCommand(Menu menu, Scanner scanner) {
        this.menu = menu;
        this.scanner = scanner;
    }

    @Override
    public String description() {
        return "Завантажити дані з файлу";
    }

    @Override
    public int execute(String[] params) {
        System.out.print("Введіть ім'я файлу (наприклад, data.dat): ");
        String file = scanner.nextLine().trim();
        try {
            MobileCompany loaded = MobileCompany.loadFromFile(file);
            menu.setCompany(loaded);
            System.out.println("Дані завантажено з файлу " + file);
            System.out.println("Тарифів у компанії: " + loaded.getTariffs().size());
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Помилка завантаження: " + e.getMessage());
        }
        return 0;
    }
}
