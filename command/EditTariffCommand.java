package command;

import model.Tariff;
import service.MobileCompanyService;

import java.util.Scanner;

/**
 * Команда для редагування існуючого тарифу.
 * Дозволяє змінити місячну плату для тарифу, знайденого за назвою.
 */
public class EditTariffCommand implements Command {

    private final MobileCompanyService service;

    private final Scanner scanner;

    /**
     * Створює команду редагування тарифу.
     *
     * @param service сервіс мобільної компанії для роботи з тарифами
     * @param scanner джерело введення даних (наприклад, System.in)
     */
    public EditTariffCommand(MobileCompanyService service, Scanner scanner) {
        this.service = service;
        this.scanner = scanner;
    }

    @Override
    public String description() {
        return "Редагувати тариф (місячна плата)";
    }

    /**
     * Виконує команду редагування: шукає тариф за назвою і оновлює його місячну плату.
     * Якщо тариф не знайдено, виводить відповідне повідомлення.
     *
     * @return код завершення команди (0 у разі успіху чи відсутності змін)
     */
    @Override
    public int execute() {
        System.out.print("Назва тарифу для редагування: ");
        String name = scanner.nextLine().trim();

        for (Tariff t : service.getTariffs()) {
            if (t.getName().equalsIgnoreCase(name)) {
                System.out.print("Нова місячна плата: ");
                double fee = Double.parseDouble(scanner.nextLine().trim());
                t.setMonthlyFee(fee);
                System.out.println("Оновлено: " + t);
                return 0;
            }
        }

        System.out.println("Тариф не знайдено.");
        return 0;
    }
}
