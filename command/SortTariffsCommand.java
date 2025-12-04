package command;

import model.Tariff;
import service.MobileCompanyService;

import java.util.Scanner;

/**
 * Команда для сортування тарифів мобільної компанії.
 * Дозволяє відсортувати тарифи за місячною платою або кількістю клієнтів і виводить результат.
 */
public class SortTariffsCommand implements Command {

    private final MobileCompanyService service;

    private final Scanner scanner;

    /**
     * Створює команду сортування тарифів.
     *
     * @param service сервіс мобільної компанії для роботи з тарифами
     * @param scanner джерело введення даних (наприклад, System.in)
     */
    public SortTariffsCommand(MobileCompanyService service, Scanner scanner) {
        this.service = service;
        this.scanner = scanner;
    }

    @Override
    public String description() {
        return "Відсортувати тарифи";
    }

    /**
     * Виконує сортування тарифів за вибраним критерієм.
     * Якщо користувач вводить "clients", сортування відбувається за кількістю клієнтів, інакше — за місячною платою.
     *
     * @return код завершення команди (0 у разі успішного виконання)
     */
    @Override
    public int execute() {
        System.out.println("Сортувати за (fee/clients): ");
        String by = scanner.nextLine().trim().toLowerCase();

        if (by.equals("clients")) {
            service.sortTariffsByClients();
        } else {
            service.sortTariffsByMonthlyFee();
        }

        System.out.println("Відсортований список тарифів:");
        for (Tariff t : service.getTariffs()) {
            System.out.println(t);
        }
        return 0;
    }
}
