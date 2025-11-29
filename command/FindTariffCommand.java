package command;

import menu.Menu;
import model.MobileCompany;
import model.Tariff;

import java.util.List;
import java.util.Scanner;

public class FindTariffCommand implements Command {

    private final Menu menu;
    private final Scanner scanner;

    public FindTariffCommand(Menu menu, Scanner scanner) {
        this.menu = menu;
        this.scanner = scanner;
    }

    @Override
    public String description() {
        return "Пошук тарифу за діапазоном абонплати";
    }

    @Override
    public int execute(String[] params) {
        MobileCompany company = menu.getCompany();

        System.out.print("Мінімальна абонплата: ");
        double from = Double.parseDouble(scanner.nextLine().trim());

        System.out.print("Максимальна абонплата: ");
        double to = Double.parseDouble(scanner.nextLine().trim());

        List<Tariff> result = company.findTariffsByMonthlyFeeRange(from, to);
        if (result.isEmpty()) {
            System.out.println("Тарифів у цьому діапазоні не знайдено.");
        } else {
            System.out.println("Знайдені тарифи:");
            result.forEach(System.out::println);
        }
        return 0;
    }
}
