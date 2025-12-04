package command;

import model.Tariff;
import service.MobileCompanyService;

import java.util.List;
import java.util.Scanner;

/**
 * Команда для пошуку тарифів за діапазоном місячної абонентської плати.
 * Зчитує межі діапазону з консольного введення та виводить список знайдених тарифів.
 */
public class FindTariffCommand implements Command {

    private final MobileCompanyService service;

    private final Scanner scanner;

    /**
     * Створює команду пошуку тарифів за діапазоном абонплати.
     *
     * @param service сервіс мобільної компанії для роботи з тарифами
     * @param scanner джерело введення даних (наприклад, System.in)
     */
    public FindTariffCommand(MobileCompanyService service, Scanner scanner) {
        this.service = service;
        this.scanner = scanner;
    }

    @Override
    public String description() {
        return "Пошук тарифу за діапазоном абонплати";
    }

    /**
     * Виконує пошук тарифів у заданому діапазоні місячної абонплати.
     * У разі знаходження виводить тарифи у консоль, інакше повідомляє про відсутність результатів.
     *
     * @return код завершення команди (0 у разі успішного виконання)
     */
    @Override
    public int execute() {
        System.out.print("Мінімальна абонплата: ");
        double from = Double.parseDouble(scanner.nextLine().trim());

        System.out.print("Максимальна абонплата: ");
        double to = Double.parseDouble(scanner.nextLine().trim());

        List<Tariff> result = service.findTariffsByMonthlyFeeRange(from, to);
        if (result.isEmpty()) {
            System.out.println("Тарифів у цьому діапазоні не знайдено.");
        } else {
            System.out.println("Знайдені тарифи:");
            result.forEach(System.out::println);
        }
        return 0;
    }
}
